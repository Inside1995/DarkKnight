package ru.artur.darkknight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.artur.darkknight.model.Char;
import ru.artur.darkknight.model.User;
import ru.artur.darkknight.model.enums.DuelMoveType;
import ru.artur.darkknight.model.enums.DuelResult;
import ru.artur.darkknight.service.DuelService;
import ru.artur.darkknight.service.CharService;
import ru.artur.darkknight.service.StatisticService;
import ru.artur.darkknight.service.UserService;
import ru.artur.darkknight.utils.HelpService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


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
    private CharService charService;
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
        Char myChar = user.getaChar();
        List<Char> allChars = charService.getAllKnights();
        model.addAttribute("myChar", myChar);
        model.addAttribute("allHeroes", allChars);
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
        Char opponent = charService.getKnightById(id);
        opponent.setHealth(100);
        charService.update(opponent);
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        List<Char> allHeroes = charService.getAllKnights();
        model.addAttribute("opponent", opponent);
        model.addAttribute("myChar", myChar);
        model.addAttribute("allHeroes", allHeroes);
        return "versus";
    }

    @RequestMapping(value = "/start_fighting/**")
    public String startFighting(@RequestParam("opponent_id") Long id, Principal principal, Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        Char opponent = charService.getKnightById(id);
        boolean game = true;
        List<String> history = new ArrayList<>();
        while (game) {
            DuelMoveType type = DuelMoveType.values()[(int) (Math.random() * 2)];
            String myAction = duelService.findAction(type);
            int random = (int) (Math.random() * 2);
            String opponentAction = duelService.findAction(random == 1 ? DuelMoveType.ATTACK : DuelMoveType.DEFENCE);

            int myDamage = HelpService.getDamage(myChar, opponent);
            int opponentDamage = HelpService.getDamage(opponent, myChar);

            myAction = String.format(myAction, "<b>" + myChar.getName() + "</b>", "<b>" + opponent.getName() + "</b>", myDamage);
            opponentAction = String.format(opponentAction, "<b>" + opponent.getName() + "</b>", "<b>" + myChar.getName() + "</b>", opponentDamage);
            history.add(myAction);
            history.add(opponentAction);
            myChar.setHealth(myChar.getHealth() - opponentDamage);
            opponent.setHealth(opponent.getHealth() - myDamage);

            if (opponent.getHealth() <= 0 || myChar.getHealth() <= 0) {
                boolean iWin = opponent.getHealth() < myChar.getHealth();
                if (iWin) {
                    myChar.updateStatistic(DuelResult.WIN);
                    opponent.updateStatistic(DuelResult.LOSE);
                } else {
                    myChar.updateStatistic(DuelResult.LOSE);
                    opponent.updateStatistic(DuelResult.WIN);
                }
                statisticService.update(myChar.getStatistic());
                statisticService.update(opponent.getStatistic());
                game = false;
                model.addAttribute("iWin", iWin);
                if (myChar.getHealth() < 0)
                    myChar.setHealth(0);
                if (opponent.getHealth() < 0)
                    opponent.setHealth(0);
            }
        }
        charService.update(myChar);
        charService.update(opponent);
        model.addAttribute("history", history);
        model.addAttribute("myChar", myChar);
        return "duel_history";
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
        Char myChar = user.getaChar();
        Char opponentChar = userService.findByUsername(opponentName).getaChar();
        String myAction = duelService.findAction(type);
        int random = 2;
        String opponentAction = duelService.findAction(random == 1 ? DuelMoveType.ATTACK : DuelMoveType.DEFENCE);

        int myDamage = HelpService.getDamage(myChar, opponentChar);
        int opponentDamage = HelpService.getDamage(opponentChar, myChar);

        myAction = String.format(myAction, myChar.getName(), opponentChar.getName(), myDamage);
        opponentAction = String.format(opponentAction, opponentChar.getName(), myChar.getName(), opponentDamage);
        myChar.setHealth(myChar.getHealth() - opponentDamage);
        opponentChar.setHealth(opponentChar.getHealth() - myDamage);

        if (opponentChar.getHealth() <= 0 || myChar.getHealth() <= 0) {
            boolean iWin = opponentChar.getHealth() < myChar.getHealth();
            if (iWin) {
                myChar.updateStatistic(DuelResult.WIN);
                opponentChar.updateStatistic(DuelResult.LOSE);
            } else {
                myChar.updateStatistic(DuelResult.LOSE);
                opponentChar.updateStatistic(DuelResult.WIN);
            }
            statisticService.update(myChar.getStatistic());
            statisticService.update(opponentChar.getStatistic());
        }
        charService.update(myChar);
        charService.update(opponentChar);
        return myAction + ". " + opponentAction + "\n" + myChar.getHealth() + ":" + opponentChar.getHealth();
    }
}