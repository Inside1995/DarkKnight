package ru.artur.darkknight.dao;

import ru.artur.darkknight.model.Work;

public interface WorkDao {
    void createWork(Work work);
    void update(Work work);
}
