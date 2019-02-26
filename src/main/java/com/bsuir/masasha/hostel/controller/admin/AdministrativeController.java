package com.bsuir.masasha.hostel.controller.admin;

import com.bsuir.masasha.hostel.domain.Hotel;
import com.bsuir.masasha.hostel.domain.RoomType;
import com.bsuir.masasha.hostel.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.bsuir.masasha.hostel.SharedConstants.HOTEL_ATR;
import static com.bsuir.masasha.hostel.SharedConstants.REDIRECT;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasAuthority('ADMIN')")
public class AdministrativeController {

}
