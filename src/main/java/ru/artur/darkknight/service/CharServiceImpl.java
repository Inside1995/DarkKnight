package ru.artur.darkknight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.artur.darkknight.dao.CharDao;
import ru.artur.darkknight.model.Char;

import java.util.Collections;
import java.util.List;

@Service
public class CharServiceImpl implements CharService {
    @Autowired
    private CharDao dao;

    public void setDao(CharDao dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    @Override
    public Char getKnightById(Long id) {
        return dao.getKnightById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Char> getAllKnights() {
        List<Char> allChars = dao.getAllKnights();
        Collections.sort(allChars, Collections.reverseOrder());
        return allChars;
    }

    @Transactional
    @Override
    public void save(Char aChar) {
        dao.save(aChar);
    }

    @Transactional
    @Override
    public void update(Char aChar) {
        dao.update(aChar);
    }

    @Transactional
    @Override
    public void remove(Char aChar) {
        dao.remove(aChar);
    }
}
