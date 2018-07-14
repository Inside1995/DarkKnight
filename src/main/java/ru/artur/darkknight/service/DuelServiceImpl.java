package ru.artur.darkknight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.artur.darkknight.dao.DuelDao;
import ru.artur.darkknight.model.enums.DuelMoveType;

@Service
public class DuelServiceImpl implements DuelService {
    @Autowired
    private DuelDao dao;

    @Override
    public String findAction(DuelMoveType type) {
        return dao.findAction(type);
    }
}
