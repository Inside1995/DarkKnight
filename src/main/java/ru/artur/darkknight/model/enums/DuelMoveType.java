package ru.artur.darkknight.model.enums;

public enum DuelMoveType {
    ATTACK("attack"),
    DEFENCE("defence");

    private String type;

    DuelMoveType(String type) {
        this.type = type;
    }

    public static DuelMoveType getTypeByString(String type) {
        for (DuelMoveType duelType:
             DuelMoveType.values()) {
            if (duelType.type.equals(type))
                return duelType;
        }
        return null;
    }
}
