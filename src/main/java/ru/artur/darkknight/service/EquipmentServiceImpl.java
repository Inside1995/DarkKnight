package ru.artur.darkknight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.artur.darkknight.dao.EquipmentDao;
import ru.artur.darkknight.model.items.Equipment;
import ru.artur.darkknight.model.items.armor.Armor;
import ru.artur.darkknight.model.items.weapon.Weapon;

import java.util.Set;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao dao;

    @Transactional
    @Override
    public void save(Equipment equipment) {
        dao.save(equipment);
    }

    @Transactional
    @Override
    public Equipment getEquipmentById(Long id) {
        return dao.getEquipmentById(id);
    }

    @Transactional
    @Override
    public Set<Equipment> getAllEquipments() {
        return dao.getAllEquipments();
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Weapon> getAllWeapons() {
        return dao.getAllWeapons();
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Armor> getAllArmors() {
        return dao.getAllArmors();
    }
}
