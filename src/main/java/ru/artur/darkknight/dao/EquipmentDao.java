package ru.artur.darkknight.dao;

import ru.artur.darkknight.model.items.Equipment;
import ru.artur.darkknight.model.items.armor.Armor;
import ru.artur.darkknight.model.items.weapon.Weapon;

import java.util.Set;

public interface EquipmentDao {
    void save(Equipment equipment);
    Equipment getEquipmentById(Long id);
    Set<Equipment> getAllEquipments();
    Set<Weapon> getAllWeapons();
    Set<Armor> getAllArmors();
}
