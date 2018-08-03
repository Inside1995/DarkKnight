package ru.artur.darkknight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.artur.darkknight.model.Char;
import ru.artur.darkknight.model.Condition;
import ru.artur.darkknight.model.Statistic;
import ru.artur.darkknight.model.User;
import ru.artur.darkknight.model.items.Equipment;
import ru.artur.darkknight.service.*;
import ru.artur.darkknight.utils.FileDownloadUtil;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

/**
 * The main controller that handles all the requests for the main page
 * Responsible for displaying all information about the user, and also responsible for the actions on the main page.
 * Such as: equip a thing and remove a thing. <br/>
 *
 * In its composition has three services, which in turn work with dao objects to access the database: <br/>
 * CharService All manipulations on the knight's domain {@link Char} <br/>
 * EquipmentService All manipulations on the equipment's domain {@link Equipment} <br/>
 * StatisticService All manipulations on the knight's statistic's domain {@link Statistic} <br/>
 * ConditionService All manipulations on the knight's condition's domain {@link Condition} <br/>
 *
 * @author Grudtsin Artur
 * @version 1.0
 */
@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private CharService charService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private ConditionService conditionService;
    @Autowired
    private UserService userService;


    /**
     * Intercepts the request for the main page.
     * Completely initializes the character.
     * Adds condition to the character, if necessary.
     *
     * @param model It is necessary to attach all the necessary data to the model in order to display them on the pages
     * @return the name of the page
     */
    @RequestMapping
    public String main(Model model, Principal principal) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        boolean conditionWasCreate = false;
        myChar.setHealth(100);
        //Если герой только что был создан и у него еще нет статистики и кондиции
        if (myChar.getStatistic() == null) {
            Statistic statistic = myChar.initializeStatisticForChar();
            statisticService.createStatistic(statistic);
            charService.update(myChar);
        }
        if (myChar.getCondition() == null)
            conditionWasCreate = true;

        Condition condition = myChar.initializeOrUpdateConditionForChar();
        if (conditionWasCreate)
            conditionService.createCondition(condition);
        else
            conditionService.update(condition);

        charService.update(myChar);

        List<Char> allHeroes = charService.getAllKnights();

        model.addAttribute("myChar", myChar);
        model.addAttribute("allHeroes", allHeroes);
        return "main";
    }

    /**
     * A helper method that helps to upload an avatar for equipment {@link Equipment}
     *
     * @param id needed to load the equipment, that need an avatar
     * @return the avatar of the equipment returned in bytes
     */
    @ResponseBody
    @RequestMapping("/get_armor_avatar/**")
    public byte[] getArmorAvatar(@RequestParam("id") Long id,
                                 @RequestParam("type") String type) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        if (equipment == null) {
            byte[] bytes = FileDownloadUtil.downloadNoneAvatar(type);
            return bytes;
        }
        return equipment.getAvatar();
    }

    /**
     * Auxiliary method, which gives the command to the hero to equip some thing
     *
     * @param id the id of the Equipment need to euip {@link Equipment}
     * @param model It is necessary to attach all the necessary data to the model in order to display them on the pages
     * @return the name of the main page
     */
    @RequestMapping(value = "/equip/**", method = RequestMethod.POST)
    public String equip(@RequestParam("id") Long id,
                        Principal principal,
                        Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        Equipment equipment = equipmentService.getEquipmentById(id);
        myChar.equip(equipment);
        charService.update(myChar);
        model.addAttribute("myKnight", myChar);
        return "redirect:/";
    }

    /**
     * Auxiliary method, which gives the command to the hero to unequip some thing <br/>
     *
     * @param id of the equipment needed to take off the equip {@link Equipment} <br/>
     * @param model It is necessary to attach all the necessary data to the model in order to display them on the pages <br/>
     * @return the name of the main page <br/>
     */
    @RequestMapping(value = "/unequip")
    public String unEquip(@RequestParam("id") Long id,
                          Principal principal,
                          Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        Equipment equipment = equipmentService.getEquipmentById(id);
        if (equipment != null) {
            myChar.unEquip(equipment);
            charService.update(myChar);
        }
        model.addAttribute("myKnight", myChar);
        return "redirect:/";
    }

    @RequestMapping("/sell/**")
    public String sell(@RequestParam("id") Long id,
                       Principal principal) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        Equipment equipmentById = equipmentService.getEquipmentById(id);
        myChar.sell(equipmentById);
        charService.update(myChar);
        return "redirect:/shop/sell_page";
    }

    @RequestMapping(value = "/statistic")
    public String statistic(Model model,
                            Principal principal) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        List<Char> allKnights = charService.getAllKnights();
        Collections.sort(allKnights, (o1, o2) -> o2.getStatistic().getWinPercentage().compareTo(o1.getStatistic().getWinPercentage()));
        model.addAttribute("allHeroes", allKnights);
        model.addAttribute("myChar", myChar);
        return "statistic";
    }
}