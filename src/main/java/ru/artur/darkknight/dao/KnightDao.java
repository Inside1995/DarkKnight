package ru.artur.darkknight.dao;

import ru.artur.darkknight.model.Knight;

import java.util.List;

public interface KnightDao {
    Knight getKnightById(Long id);
    List<Knight> getAllKnights();
    void save(Knight knight);
    void update(Knight knight);
    void remove(Knight knight);
}
