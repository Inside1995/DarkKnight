package ru.artur.darkknight.service;

import ru.artur.darkknight.model.items.Equipment;
import ru.artur.darkknight.model.items.armor.Armor;
import ru.artur.darkknight.model.items.weapon.Weapon;

import java.util.Set;

public interface EquipmentService {
    void save(Equipment equipment);
    Equipment getEquipmentById(Long id);
    Set<Equipment> getAllEquipments();
    Set<Weapon> getAllWeapons();
    Set<Armor> getAllArmors();
}
