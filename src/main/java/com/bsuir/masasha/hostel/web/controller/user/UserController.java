package com.bsuir.masasha.hostel.web.controller.user;

import com.bsuir.masasha.hostel.core.domain.Role;
import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.repo.UserRepository;
import com.bsuir.masasha.hostel.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.bsuir.masasha.hostel.web.WebConstants.REDIRECT;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

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

    @PostMapping("edit")
    public String userEditForm(@RequestParam("image") MultipartFile image, User user, Model model) {
        userService.updateUser(image, user);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return REDIRECT + "profile";
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
