package ru.kata.spring.boot_security.demo.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.User;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String welcomeToIndex() {
        return "index2";
    }



    @GetMapping("/kino/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "show";
    }

    @GetMapping("/kino/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String creat(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/"; // Возможно тут будешь ошибка

    }

    @GetMapping("/kino/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) { // возможна ошибка из за
        model.addAttribute("user", userService.getById(id));
        return "edit";

    }

    @PatchMapping("/kino/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.edit(user);
        return "redirect:/";
    }

    @DeleteMapping("/kino/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(userService.getById(id));
        return "redirect:/";
    }
}
