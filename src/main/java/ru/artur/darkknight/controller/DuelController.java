package ru.artur.darkknight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.artur.darkknight.model.Knight;
import ru.artur.darkknight.model.Statistic;
import ru.artur.darkknight.model.User;
import ru.artur.darkknight.model.enums.DuelMoveType;
import ru.artur.darkknight.model.enums.DuelResult;
import ru.artur.darkknight.service.DuelService;
import ru.artur.darkknight.service.KnightService;
import ru.artur.darkknight.service.StatisticService;
import ru.artur.darkknight.service.UserService;
import ru.artur.darkknight.utils.HelpService;

import java.security.Principal;
import java.util.List;
import java.util.Random;
import java.util.Set;


/**
 * The controller, which is responsible for the entire combat system.
 * Such as the choice of the opponent and the flow of the moves of users.
 *
 * @author Grudtsin Artur
 * @version 1.0
 */
@Controller
@RequestMapping("/duel")
public class DuelController {
    @Autowired
    private KnightService knightService;
    @Autowired
    private DuelService duelService;
    @Autowired
    private UserService userService;
    @Autowired
    private StatisticService statisticService;

    /**
     * The method processes the main request, which directs the user to the rivals selection page.
     *
     * @param principal Object for getting authorized user.
     * @param model The object needed to add parameters to the response.
     * @return the name of the view page.
     */
    @RequestMapping
    public String duelPage(Principal principal,
                           Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Knight myKnight = user.getKnight();
        List<Knight> allKnights = knightService.getAllKnights();
        model.addAttribute("myKnight", myKnight);
        model.addAttribute("allHeroes", allKnights);
        return "duel";
    }

    /**
     * After the character has chosen the opponent, he is transferred to the battle page,
     * where the character can make a move,
     * as well as where all the information about the characters is displayed.
     *
     * @param id of the opponent's hero
     * @param principal Object for getting authorized user.
     * @param model The object needed to add parameters to the response.
     * @return the name of the view page.
     */
    @RequestMapping("/versus/**")
    public String duelVersus(@RequestParam("opponent_id") long id,
                             Principal principal,
                             Model model) {
        Knight opponent = knightService.getKnightById(id);
        opponent.setHealth(100);
        knightService.update(opponent);
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Knight myKnight = user.getKnight();
        List<Knight> allHeroes = knightService.getAllKnights();
        model.addAttribute("opponent", opponent);
        model.addAttribute("myKnight", myKnight);
        model.addAttribute("allHeroes", allHeroes);
        return "versus";
    }

    /**
     * This method is responsible for each move of the character, which can be more attacking or defensive.
     * The method calculates the damage that each character does and sends the result back to the client.
     *
     * @param param the type of the move {@link DuelMoveType}
     * @param opponentName the name of the opponent
     * @param principal Object for getting authorized user.
     * @return the name of the view page.
     */
    @RequestMapping(value = "/doMove/**", method = RequestMethod.POST, produces = {"text/html; charset=UTF-8"})
    @ResponseBody
    public String doMove(@RequestParam("param") String param,
                         @RequestParam("opponentName") String opponentName,
                         Principal principal) {
        DuelMoveType type = DuelMoveType.getTypeByString(param);
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Knight myKnight = user.getKnight();
        Knight opponentKnight = userService.findByUsername(opponentName).getKnight();
        String myAction = duelService.findAction(type);
        int random = 2;
        String opponentAction = duelService.findAction(random == 1 ? DuelMoveType.ATTACK : DuelMoveType.DEFENCE);

        int myDamage = HelpService.getDamage(myKnight, opponentKnight);
        int opponentDamage = HelpService.getDamage(opponentKnight, myKnight);

        myAction = String.format(myAction, myKnight.getName(), opponentKnight.getName(), myDamage);
        opponentAction = String.format(opponentAction, opponentKnight.getName(), myKnight.getName(), opponentDamage);
        myKnight.setHealth(myKnight.getHealth() - opponentDamage);
        opponentKnight.setHealth(opponentKnight.getHealth() - myDamage);

        if (opponentKnight.getHealth() <= 0 || myKnight.getHealth() <= 0) {
            boolean iWin = opponentKnight.getHealth() < myKnight.getHealth();
            if (iWin) {
                myKnight.updateStatistic(DuelResult.WIN);
                opponentKnight.updateStatistic(DuelResult.LOSE);
            } else {
                myKnight.updateStatistic(DuelResult.LOSE);
                opponentKnight.updateStatistic(DuelResult.WIN);
            }
            statisticService.update(myKnight.getStatistic());
            statisticService.update(opponentKnight.getStatistic());
        }
        knightService.update(myKnight);
        knightService.update(opponentKnight);
        return myAction + ". " + opponentAction + "\n" + myKnight.getHealth() + ":" + opponentKnight.getHealth();
    }
}