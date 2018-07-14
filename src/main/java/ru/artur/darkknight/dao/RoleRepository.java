package ru.artur.darkknight.dao;

import ru.artur.darkknight.model.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> findAll();
    List<Role> findRoleUser();
}
