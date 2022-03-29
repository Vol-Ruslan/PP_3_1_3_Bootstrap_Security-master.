package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> allRoles();

    void add(Role film);

    void delete(Role film);

    void edit(Role film);

    Role getById(long id);

    List<Role> findByName(List<String> name);
}
