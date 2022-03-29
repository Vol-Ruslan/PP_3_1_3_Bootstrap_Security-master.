package ru.kata.spring.boot_security.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.Convert.ConvertByUser;
import ru.kata.spring.boot_security.demo.cover.CoverUser;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
@RequestMapping("/admin")
public class AdminController {


    final UserService userService;
    final RoleService roleService;
    final ConvertByUser convertByUser;


    public AdminController(UserService userService, RoleService roleService, ConvertByUser convertByUser) {
        this.userService = userService;
        this.roleService = roleService;
        this.convertByUser = convertByUser;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(userService.getById(id));
        return "redirect:/admin";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", roleService.allRoles());
        return "edit";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") CoverUser user, @PathVariable("id") int id) {
        User userInToDB = convertByUser.convertByUser(user);
        userInToDB.setId(id);
        userService.edit(userInToDB);
        return "redirect:/admin";
    }

    @PostMapping()
    public String creat(@ModelAttribute("user") CoverUser user) {
        User userInToDB = convertByUser.convertByUser(user);
        userService.add(userInToDB);
        return "redirect:/admin";

    }

    @GetMapping("/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.allRoles());
        return "new";
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "index";
    }
}
