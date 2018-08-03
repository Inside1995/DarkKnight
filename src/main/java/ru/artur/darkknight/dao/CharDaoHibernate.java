package ru.artur.darkknight.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.artur.darkknight.model.Char;

import java.util.List;

@Repository
@Primary
public class CharDaoHibernate implements CharDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Char getKnightById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Char aChar = (Char) currentSession.createQuery("from Char k where k.id=:id")
                .setParameter("id", id)
                .uniqueResult();
        if (aChar != null)
            return aChar;
        return null;
    }

    @Override
    public List<Char> getAllKnights() {
        Session currentSession = sessionFactory.getCurrentSession();
        List<Char> allChars = currentSession.createQuery("from Char").list();
        return allChars;
    }

    @Override
    public void save(Char aChar) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(aChar);
    }

    @Override
    public void update(Char aChar) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(aChar);
    }

    @Override
    public void remove(Char aChar) {

    }
}
