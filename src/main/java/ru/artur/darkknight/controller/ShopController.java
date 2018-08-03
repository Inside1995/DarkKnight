package ru.artur.darkknight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.artur.darkknight.exception.GoldNotEnoughException;
import ru.artur.darkknight.model.Char;
import ru.artur.darkknight.model.User;
import ru.artur.darkknight.model.items.Equipment;
import ru.artur.darkknight.model.items.armor.Armor;
import ru.artur.darkknight.model.items.weapon.Weapon;
import ru.artur.darkknight.service.EquipmentService;
import ru.artur.darkknight.service.CharService;
import ru.artur.darkknight.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;


/**
 * The controller, which is responsible for all manipulations in the store. Such as viewing and buying things {@link Equipment}.
 *
 * @author Grudtsin Artur
 * @version 1.0
 */
@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private CharService charService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;

    /**
     * Intercepts the request and sends the user to the main page of the store, where the user has to select the category of the desired product.
     *
     * @param principal Object for getting authorized user.
     * @param model     The object needed to add parameters to the response.
     * @return the name of the view page.
     */
    @RequestMapping
    public String shopPage(Principal principal,
                           Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        List<Char> allHeroes = charService.getAllKnights();
        Set<Equipment> allEquipments = equipmentService.getAllEquipments();
        model.addAttribute("myChar", myChar);
        model.addAttribute("allItems", allEquipments);
        model.addAttribute("allHeroes", allHeroes);
        return "shop";
    }

    @RequestMapping("/buy_page")
    public String buyPage(Principal principal,
                          Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        model.addAttribute("myChar", myChar);
        return "buy_page";
    }

    @RequestMapping("/sell_page")
    public String sellPage(Principal principal,
                           Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        model.addAttribute("myChar", myChar);
        return "sell_page";
    }

    /**
     * The method enlists the purchased item if it has enough money and takes the necessary amount from it
     *
     * @param principal Object for getting authorized user.
     * @param itemID    The id of the object need to buy.
     * @param model     The object needed to add parameters to the response.
     * @return the name of the view page.
     */
    @RequestMapping("/buy/**")
    public String buyItem(Principal principal,
                          @RequestParam("item_id") Long itemID,
                          Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        Equipment equipmentById = equipmentService.getEquipmentById(itemID);
        try {
            myChar.buy(equipmentById);
            charService.update(myChar);
        } catch (GoldNotEnoughException e) {
            model.addAttribute("exception", e.getMessage());
        }
        String whereToGo = (equipmentById instanceof Armor) ? "/getArmors" : "/getWeapons";
        return "redirect:/shop" + whereToGo;
    }

    /**
     * The method gives the user a complete list of all available weapons that are in the game.
     *
     * @param principal Object for getting authorized user.
     * @param model     The object needed to add parameters to the response.
     * @return the name of the view page.
     */
    @RequestMapping("/getWeapons/**")
    public String getWeapons(Principal principal,
                             @RequestParam(value = "exception", required = false) String exception,
                             Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        List<Char> allHeroes = charService.getAllKnights();
        Set<Weapon> allWeapons = equipmentService.getAllWeapons();
        model.addAttribute("weaponItems", allWeapons);
        model.addAttribute("myChar", myChar);
        model.addAttribute("allHeroes", allHeroes);
        if (exception != null && !exception.equals(""))
            model.addAttribute("exception", exception);
        return "shop_item_list";
    }

    /**
     * The method gives the user a complete list of all available armors that are in the game.
     *
     * @param principal Object for getting authorized user.
     * @param model     The object needed to add parameters to the response.
     * @return the name of the view page.
     */
    @RequestMapping("/getArmors/**")
    public String getArmors(Principal principal,
                            @RequestParam(value = "exception", required = false) String exception,
                            Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        List<Char> allHeroes = charService.getAllKnights();
        Set<Armor> allArmors = equipmentService.getAllArmors();
        model.addAttribute("armorItems", allArmors);
        model.addAttribute("myChar", myChar);
        model.addAttribute("allHeroes", allHeroes);
        if (exception != null && !exception.equals(""))
            model.addAttribute("exception", exception);
        return "shop_item_list";
    }
}
