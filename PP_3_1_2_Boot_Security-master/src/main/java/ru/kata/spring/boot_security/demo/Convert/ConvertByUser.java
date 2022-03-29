package ru.kata.spring.boot_security.demo.Convert;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.cover.CoverUser;
import ru.kata.spring.boot_security.demo.model.User;

@Component
public class ConvertByUser {

    private final RoleService roleService;

    public ConvertByUser(RoleService roleService) {
        this.roleService = roleService;
    }
    public User convertByUser (CoverUser coverUser) {

        User user = new User();

        user.setPassword(coverUser.getPassword());
        user.setName(coverUser.getName());
        user.setRoles(roleService.findByName(coverUser.getRoles()));
        return user;
    }
}
