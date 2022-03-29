package ru.kata.spring.boot_security.demo.cover;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public class CoverUser {

    @Autowired
    RoleService roleService;

    private String name;
    private String password;
    private List<String> roles;

    public CoverUser() {
    }

    public CoverUser(String name, String password, List<String> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
