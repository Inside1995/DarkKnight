package ru.artur.darkknight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.artur.darkknight.model.Char;
import ru.artur.darkknight.model.User;
import ru.artur.darkknight.model.Work;
import ru.artur.darkknight.model.items.Equipment;
import ru.artur.darkknight.service.*;
import ru.artur.darkknight.utils.TimeUtil;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * A controller that is responsible for all the manipulations of the work.
 * In this web application in the quality of work a campaign is used, after which the character can get gold, crystals or things {@link Equipment}.
 *
 * The controller has all the necessary services for character work, in particular:
 * a service {@link CharService} for the character that updates the status of the character in the database,
 * the work service {@link WorkService} updates the work information of the character in the database after a successful work,
 * userService {@link UserService} is necessary in order to get an authorized user, so that the application always was secured.
 * equipmentService {@link EquipmentService} is used, because the user can get some equipment after the work is finish.
 *
 * @author Grudtsin Artur
 * @version 1.0
 */
@Controller
@RequestMapping("/work")
public class WorkController {
    @Autowired
    private CharService charService;
    @Autowired
    private WorkService workService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;

    /**
     * Processes requests of the type '/training' and redirects the user to the main page of the work.
     *
     * @param goldAmount information on how much the user received gold for further display on the work page.
     * @param crystalAmount information on how much the user received crystal for further display on the work page.
     * @param randomEquip information on which {@link Equipment} the user has received for further display on the work page.
     * @param principal Object for getting authorized user.
     * @param model The object needed to add parameters to the response.
     * @return the name of the view page.
     */
    @RequestMapping
    public String workPage(@RequestParam(value = "goldAmount", required = false) Integer goldAmount,
                           @RequestParam(value = "crystalAmount", required = false) Integer crystalAmount,
                           @RequestParam(value = "randomEquip", required = false) Equipment randomEquip,
                           Principal principal,
                           Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        List<Char> allHeroes = charService.getAllKnights();
        if (myChar.getWork() == null) {
            Work work = new Work();
            work.setaChar(myChar);
            work.setGoldTaked(false);
            work.setEndTime(null);
            myChar.setWork(work);
            workService.createWork(work);
            charService.update(myChar);
        }
        Date workEndTime = myChar.getWork().getEndTime();
        if (workEndTime != null) {
            Date now = new Date();
            if (workEndTime.getTime() < now.getTime()) {
                model.addAttribute("workComplete", true);
            } else {
                model.addAttribute("time", TimeUtil.getTimeBetween(now, workEndTime));
            }
        }

        model.addAttribute("goldAmount", goldAmount);
        model.addAttribute("crystalAmount", crystalAmount);
        model.addAttribute("randomEquip", randomEquip);

        model.addAttribute("myChar", myChar);
        model.addAttribute("allHeroes", allHeroes);
        return "work";
    }

    /**
     * This method starts the character's work, setting the end time and redirecting the user to the main page of the work, where the time must begin to count.
     *
     * @param id of the knight need to work
     * @return the name of the view page.
     */
    @RequestMapping(value = "/start_work/**", method = RequestMethod.POST)
    public String startWorking(@RequestParam("id") long id) {
        Char charById = charService.getKnightById(id);
        charById.startWork();
        charService.update(charById);
        workService.update(charById.getWork());
        return "redirect:/work";
    }

    /**
     * After the work is finished, the character is allowed to take the reward.
     * This method calculates the reward and enlists its to the character
     *
     * @param principal Object for getting authorized user.
     * @param model The object needed to add parameters to the response.
     * @return the name of the view page.
     */
    @RequestMapping("/get_gold/**")
    public String getGold(Principal principal,
                          Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        Random random = new Random();
        if (myChar.getWork().getEndTime().getTime() < new Date().getTime()) {
            int goldAmount = random.nextInt(100);
            myChar.addGold(goldAmount);
            int crystalAmount = random.nextInt(5);
            myChar.addCrystal(crystalAmount);
            int getEquip = random.nextInt(10);
            if (getEquip == 1) {
                Equipment randomEquip = equipmentService.getAllEquipments().iterator().next();
                myChar.putInBag(randomEquip);
                model.addAttribute("randomEquip", randomEquip);
            }
            model.addAttribute("goldAmount", goldAmount);
            model.addAttribute("crystalAmount", crystalAmount);
            charService.update(myChar);
            workService.update(myChar.getWork());
        }
        return "redirect:/work";
    }
}
