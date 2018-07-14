package ru.artur.darkknight.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.artur.darkknight.model.items.Equipment;
import ru.artur.darkknight.model.items.armor.Armor;
import ru.artur.darkknight.model.items.weapon.Weapon;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Primary
public class EquipmentDaoHibernate implements EquipmentDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Equipment equipment) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(equipment);
    }

    @Override
    public Equipment getEquipmentById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Equipment equipment = (Equipment) currentSession.createQuery("from Equipment e where e.id=:id")
                .setParameter("id", id)
                .uniqueResult();
        if (equipment != null)
            return equipment;
        return null;
    }

    @Override
    public Set<Equipment> getAllEquipments() {
        Session currentSession = sessionFactory.getCurrentSession();
        List<Equipment> equipments = currentSession.createQuery("from Equipment").list();
        return new HashSet<>(equipments);
    }

    @Override
    public Set<Weapon> getAllWeapons() {
        Session currentSession = sessionFactory.getCurrentSession();
        List from_weapon = currentSession.createQuery("from Weapon").list();
        return new HashSet<>(from_weapon);
    }

    @Override
    public Set<Armor> getAllArmors() {
        Session currentSession = sessionFactory.getCurrentSession();
        List from_armor = currentSession.createQuery("from Armor").list();
        return new HashSet<>(from_armor);
    }
}
