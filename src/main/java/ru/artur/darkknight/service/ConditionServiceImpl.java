package ru.artur.darkknight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.artur.darkknight.dao.ConditionDao;
import ru.artur.darkknight.model.Condition;

@Service
public class ConditionServiceImpl implements ConditionService {
    @Autowired
    private ConditionDao dao;

    @Override
    @Transactional
    public void createCondition(Condition condition) {
        dao.createCondition(condition);
    }

    @Override
    @Transactional
    public void update(Condition condition) {
        dao.update(condition);
    }
}
