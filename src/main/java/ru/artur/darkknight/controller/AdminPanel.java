package ru.artur.darkknight.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.artur.darkknight.model.items.Equipment;
import ru.artur.darkknight.model.items.EquipmentFactory;
import ru.artur.darkknight.model.items.EquipmentType;
import ru.artur.darkknight.service.EquipmentService;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;


/**
 * Controller, which is only available to the administrator. Here he can add new weapons and armor to the game.
 *
 * @author Grudtsin Artue
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/")
public class AdminPanel {
    @Autowired
    private EquipmentService service;

    /**
     * The admin panel.
     *
     * @return the name of the view page
     */
    @RequestMapping(value = "add_equipment", method = RequestMethod.GET)
    public String addEquipment() {
        return "add_equipment";
    }

    /**
     * This method help to add new equipments to the game.
     *
     * @param name the name of the new equipment
     * @param price the price of the new equipment
     * @param type the type of the new equipment
     * @param avatar the avatar of the new equipment
     * @param defence the defence of the new equipment
     * @param attack the attack of the new equipment
     * @return the name of the view page
     */
    @RequestMapping(value = "add_equipment/**", method = RequestMethod.POST)
    public String addEquipment(@RequestParam("name") String name,
                               @RequestParam("price") BigDecimal price,
                               @RequestParam("type") String type,
                               @RequestParam("avatar")MultipartFile avatar,
                               @RequestParam("defence") int defence,
                               @RequestParam("attack") int attack) {
        EquipmentType equipmentType = EquipmentType.getEquipmentType(type);
        byte[] fileContent = null;
        if (avatar != null) {
            InputStream inputStream = null;
            try {
                inputStream = avatar.getInputStream();
                if (inputStream != null)
                    fileContent = IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Equipment equipment = EquipmentFactory.getEquipment(name, price, equipmentType, fileContent, defence, attack);
        service.save(equipment);
        return "redirect:/admin/add_equipment";
    }
}
