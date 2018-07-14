package ru.artur.darkknight.service;

import ru.artur.darkknight.model.Knight;

import java.util.List;

public interface KnightService {
    Knight getKnightById(Long id);
    List<Knight> getAllKnights();
    void save(Knight knight);
    void update(Knight knight);
    void remove(Knight knight);
}
