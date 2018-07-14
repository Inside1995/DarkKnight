package ru.artur.darkknight.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.artur.darkknight.model.Work;

@Repository
public class WorkDaoHibernateImpl implements WorkDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createWork(Work work) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(work);
    }

    @Override
    public void update(Work work) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(work);
    }
}
