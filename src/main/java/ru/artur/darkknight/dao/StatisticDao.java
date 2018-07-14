package ru.artur.darkknight.dao;

import ru.artur.darkknight.model.Statistic;

public interface StatisticDao {
    void createStatistic(Statistic statistic);
    void update(Statistic statistic);
}
