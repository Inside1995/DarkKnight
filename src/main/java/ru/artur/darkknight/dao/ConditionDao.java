package ru.artur.darkknight.dao;

import ru.artur.darkknight.model.Condition;

public interface ConditionDao {
    void createCondition(Condition condition);
    void update(Condition condition);
}
