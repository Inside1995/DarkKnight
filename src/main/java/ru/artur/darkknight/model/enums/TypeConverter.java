package ru.artur.darkknight.model.enums;

import javax.persistence.AttributeConverter;

public class TypeConverter implements AttributeConverter<CharType, String> {
    @Override
    public String convertToDatabaseColumn(CharType type) {
        return CharType.getStringByType(type);
    }

    @Override
    public CharType convertToEntityAttribute(String s) {
        if (s != null)
            return CharType.valueOf(s.toUpperCase());
        return null;
    }
}
