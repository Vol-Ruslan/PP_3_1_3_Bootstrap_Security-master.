package ru.kata.spring.boot_security.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    final UserService userService;
    final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id,
                         @RequestParam(value = "newRoles[]") List<String> roles) {
        user.setRoles(roleService.findByName(roles));
        user.setId(id);
        userService.edit(user);
        return "redirect:/admin";
    }

    @PostMapping("/new")
    public String creat(@ModelAttribute("user") User user, @RequestParam(value = "newRoles[]") List<String> roles) {
        user.setRoles(roleService.findByName(roles));
        userService.add(user);
        return "redirect:/admin";

    }

    @GetMapping()
    public String index(ModelMap model, Principal principal) {
        model.addAttribute("principal", userService.findByEmail(principal.getName()));
        model.addAttribute("client", userService.findByEmail(principal.getName()));
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("newUser", new User());
        return "index";
    }
}
