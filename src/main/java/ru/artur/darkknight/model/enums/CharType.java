package ru.artur.darkknight.model.enums;

public enum CharType {
    ZOMBIE("zombie"),
    HUMAN("human");

    private String type;

    CharType(String type) {
        this.type = type;
    }

    public static String getStringByType(CharType type) {
        return type.type;
    }
}
