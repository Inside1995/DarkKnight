package ru.artur.darkknight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.artur.darkknight.dao.KnightDao;
import ru.artur.darkknight.model.Knight;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class KnightServiceImpl implements KnightService {
    @Autowired
    private KnightDao dao;

    public void setDao(KnightDao dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    @Override
    public Knight getKnightById(Long id) {
        return dao.getKnightById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Knight> getAllKnights() {
        List<Knight> allKnights = dao.getAllKnights();
        Collections.sort(allKnights, Collections.reverseOrder());
        return allKnights;
    }

    @Transactional
    @Override
    public void save(Knight knight) {
        dao.save(knight);
    }

    @Transactional
    @Override
    public void update(Knight knight) {
        dao.update(knight);
    }

    @Transactional
    @Override
    public void remove(Knight knight) {
        dao.remove(knight);
    }
}
