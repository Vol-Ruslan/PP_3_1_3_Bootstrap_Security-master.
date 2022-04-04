package ru.kata.spring.boot_security.demo.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDAO userService;

    @Autowired
    public UserController(UserDAO userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String welcomeToIndex(Principal principal, ModelMap model) {

        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

}
