package com.bsuir.masasha.hostel.controller;

import com.bsuir.masasha.hostel.domain.User;
import com.bsuir.masasha.hostel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "login";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if (!userService.addUser(user)) {
            model.addAttribute("message", "Такая почта уже зарегестрирована в системе");
            return "login";
        }

        return "redirect:/";
    }
}
