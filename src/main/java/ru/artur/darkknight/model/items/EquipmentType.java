package ru.artur.darkknight.model.items;

public enum EquipmentType {
    HELM("helm"),
    MAIN_ARMOR("main_armor"),
    BOOTS("boots"),
    JEWELLERY("jewellery"),
    RING("ring"),
    WEAPON("weapon"),
    NONE("none");

    private String name;

    EquipmentType(String name) {
        this.name = name;
    }

    public static EquipmentType getEquipmentType(String name) {
        for (EquipmentType type:
             EquipmentType.values()) {
            if (type.name.equals(name))
                return type;
        }
        return null;
    }
}
