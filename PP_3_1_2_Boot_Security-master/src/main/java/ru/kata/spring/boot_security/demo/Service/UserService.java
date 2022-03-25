package ru.kata.spring.boot_security.demo.Service;


import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;

public interface UserService {
    List<User> allUsers();

    boolean add(User user);

    void delete(User user);

    void edit(User user);

    User getById(long id);
}
