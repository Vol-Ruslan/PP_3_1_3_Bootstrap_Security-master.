package ru.kata.spring.boot_security.demo.AppRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
@Component
public class Runner implements ApplicationRunner {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public Runner(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    private void saveRole(){
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        roleService.add(roleAdmin);
        roleService.add(roleUser);
    }

    private void saveUser(){
        User user1 = new User("Vasya", "Ivanov", (byte) 22, "1@mail.ru", "1", List.of(roleService.getById(1L)));
        User user2 = new User("Petya", "Petrov", (byte) 33, "2@mail.ru", "1", List.of(roleService.getById(2L)));
        userService.add(user1);
        userService.add(user2);
    }

    @Override
    public void run(ApplicationArguments args) {
        saveRole();
        saveUser();
    }
}
