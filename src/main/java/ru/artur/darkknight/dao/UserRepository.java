package ru.artur.darkknight.dao;

import ru.artur.darkknight.model.User;

import java.util.List;

public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
    void update(User user);
}
