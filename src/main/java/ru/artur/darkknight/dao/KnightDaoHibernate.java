package ru.artur.darkknight.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.artur.darkknight.model.Knight;

import java.util.HashSet;
import java.util.List;

@Repository
@Primary
public class KnightDaoHibernate implements KnightDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Knight getKnightById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Knight knight = (Knight) currentSession.createQuery("from Knight k where k.id=:id")
                .setParameter("id", id)
                .uniqueResult();
        if (knight != null)
            return knight;
        return null;
    }

    @Override
    public List<Knight> getAllKnights() {
        Session currentSession = sessionFactory.getCurrentSession();
        List<Knight> allKnights = currentSession.createQuery("from Knight").list();
        return allKnights;
    }

    @Override
    public void save(Knight knight) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(knight);
    }

    @Override
    public void update(Knight knight) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(knight);
    }

    @Override
    public void remove(Knight knight) {

    }
}
