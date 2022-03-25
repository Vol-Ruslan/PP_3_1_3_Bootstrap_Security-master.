package ru.kata.spring.boot_security.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private final UserServiceImpl userService;


    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(userService.getById(id));
        return "redirect:/admin";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getById(id));
        return "edit";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "show";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.edit(user);
        return "redirect:/admin";
    }

    @PostMapping()
    public String creat(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";

    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }


    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "index";
    }
}
