package ru.artur.darkknight.service;

import ru.artur.darkknight.model.enums.DuelMoveType;

public interface DuelService {
    String findAction(DuelMoveType type);
}
