package ru.artur.darkknight.service;

import ru.artur.darkknight.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    void update(User user);
}