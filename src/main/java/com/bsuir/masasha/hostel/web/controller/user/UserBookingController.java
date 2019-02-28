package com.bsuir.masasha.hostel.web.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/booking")
public class UserBookingController {

    @PostMapping("/filter")
    public String filtering() {
        System.out.println("KISA");
        return "SUCCES";
    }
}
