package ru.artur.darkknight.model.items;

import ru.artur.darkknight.model.items.armor.Armor;
import ru.artur.darkknight.model.items.weapon.Weapon;

import java.math.BigDecimal;

public abstract class EquipmentFactory {
    public static Equipment getEquipment(String name, BigDecimal price,
                                         EquipmentType equipmentType, byte[] avatar,
                                         int defense, int attack) {
        switch (equipmentType) {
            case MAIN_ARMOR:
            case HELM:
            case RING:
            case BOOTS:
            case JEWELLERY:
                return new Armor(1l, name, price, equipmentType, avatar, defense);
            case WEAPON:
                return new Weapon(name, price, equipmentType, avatar, attack);
            default:
                return null;
        }
    }
}
