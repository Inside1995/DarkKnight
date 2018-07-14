package ru.artur.darkknight.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.artur.darkknight.model.Condition;

@Repository
public class ConditionDaoHibernateImpl implements ConditionDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createCondition(Condition condition) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(condition);
    }

    @Override
    public void update(Condition condition) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(condition);
    }
}
