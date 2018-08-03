package ru.artur.darkknight.service;

import ru.artur.darkknight.model.Char;

import java.util.List;

public interface CharService {
    Char getKnightById(Long id);
    List<Char> getAllKnights();
    void save(Char aChar);
    void update(Char aChar);
    void remove(Char aChar);
}
