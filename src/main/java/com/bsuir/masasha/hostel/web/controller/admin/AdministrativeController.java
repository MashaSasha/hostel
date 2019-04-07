package com.bsuir.masasha.hostel.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasAuthority('ADMIN')")
public class AdministrativeController {

    @GetMapping("/statistic")
    public String statistic(Model model, HttpServletRequest request) {

        return "admin/statistic";
    }



}
