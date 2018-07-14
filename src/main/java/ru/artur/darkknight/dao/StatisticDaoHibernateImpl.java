package ru.artur.darkknight.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.artur.darkknight.model.Statistic;

@Repository
public class StatisticDaoHibernateImpl implements StatisticDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createStatistic(Statistic statistic) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(statistic);
    }

    @Override
    public void update(Statistic statistic) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(statistic);
    }
}
