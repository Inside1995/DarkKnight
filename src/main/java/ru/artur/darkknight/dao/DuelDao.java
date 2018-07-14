package ru.artur.darkknight.dao;

import ru.artur.darkknight.model.enums.DuelMoveType;

public interface DuelDao {
    String findAction(DuelMoveType type);
}
