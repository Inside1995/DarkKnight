package ru.artur.darkknight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.artur.darkknight.dao.WorkDao;
import ru.artur.darkknight.model.Work;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkDao dao;

    @Transactional
    @Override
    public void createWork(Work work) {
        dao.createWork(work);
    }

    @Transactional
    @Override
    public void update(Work work) {
        dao.update(work);
    }
}
