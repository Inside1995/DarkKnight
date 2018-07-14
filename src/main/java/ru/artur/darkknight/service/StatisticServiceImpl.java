package ru.artur.darkknight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.artur.darkknight.dao.StatisticDao;
import ru.artur.darkknight.model.Statistic;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private StatisticDao dao;

    @Transactional
    @Override
    public void createStatistic(Statistic statistic) {
        dao.createStatistic(statistic);
    }

    @Transactional
    @Override
    public void update(Statistic statistic) {
        dao.update(statistic);
    }
}
