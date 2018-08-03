package ru.artur.darkknight.dao;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.artur.darkknight.model.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Repository
public class UserHibernateRepository implements UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("from User u where u.username=:username")
                .setParameter("username", username)
                .uniqueResult();
        return user;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }
}
