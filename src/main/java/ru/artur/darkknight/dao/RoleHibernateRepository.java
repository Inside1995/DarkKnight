package ru.artur.darkknight.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.artur.darkknight.model.Role;

import java.util.List;

@Repository
public class RoleHibernateRepository implements RoleRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Role> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Role> list = session.createQuery("from Role").list();
        return list;
    }

    @Override
    public List<Role> findRoleUser() {
        List<Role> list = sessionFactory.getCurrentSession().createQuery("FROM Role R WHERE R.name=:name")
                .setParameter("name", "ROLE_USER")
                .list();
        if (list != null && list.size() > 0)
            return list;
        return null;
    }
}
