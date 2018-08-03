package ru.artur.darkknight.dao;

import ru.artur.darkknight.model.Char;

import java.util.List;

public interface CharDao {
    Char getKnightById(Long id);
    List<Char> getAllKnights();
    void save(Char aChar);
    void update(Char aChar);
    void remove(Char aChar);
}
