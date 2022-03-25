package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {

    List<User> allUsers();

    boolean add(User film);

    void delete(User film);

    void edit(User film);

    User getById(long id);
}
