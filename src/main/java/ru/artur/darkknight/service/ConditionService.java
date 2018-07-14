package ru.artur.darkknight.service;

import ru.artur.darkknight.model.Condition;

public interface ConditionService {
    void createCondition(Condition condition);
    void update(Condition condition);
}
