package com.bsuir.masasha.hostel.controller;

import com.bsuir.masasha.hostel.domain.Role;
import com.bsuir.masasha.hostel.domain.User;
import com.bsuir.masasha.hostel.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

//    @PostMapping
//    public String userSave(@RequestParam String username, @RequestParam Map<String, String> form,
//            @RequestParam("userId") User user) {
//        user.setUsername(username);
//
//        Set<String> roles = Arrays.stream(Role.values())
//                .map(Role::name)
//                .collect(Collectors.toSet());
//
//        user.getRoles().clear();
//
//        form.entrySet().stream()
//                .filter(e -> roles.contains(e.getKey()))
//                .forEach(e -> user.getRoles().add(Role.valueOf(e.getKey())));
//
//        userRepository.save(user);
//
//        return "redirect:/user";
//    }
}
