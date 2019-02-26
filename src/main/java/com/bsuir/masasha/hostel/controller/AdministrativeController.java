package com.bsuir.masasha.hostel.controller;

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
    private static final String ROOM_SETTINGS = "admin/roomSettings";


    @Autowired
    private EditService editService;

    @GetMapping("/roomSettings")
    public String editSettings(Model model) {
        Hotel hotel = editService.findHotel();

        model.addAttribute(HOTEL_ATR, hotel);
        return ROOM_SETTINGS;
    }

    @PostMapping("/edit/hotel")
    public String editHotelData(
            Hotel hotel
    ) {
        editService.editHotel(hotel);

        return REDIRECT + ROOM_SETTINGS;
    }

    @PostMapping("/delete/img")
    public String deleteHotelImg(@RequestParam String image, @RequestParam Long id) {
        editService.deleteHotelImg(image, id);

        return REDIRECT + ROOM_SETTINGS;
    }

    @PostMapping("/add/roomType")
    public String addRoomType(@RequestParam String roomTypeTitle, @RequestParam Double roomTypeCost, @RequestParam Integer roomsAmount) {
        editService.addRoomType(roomTypeTitle, roomTypeCost, roomsAmount);

        return REDIRECT + ROOM_SETTINGS;
    }

    @PostMapping("/add/image")
    public String addImage(@RequestParam("image") MultipartFile image) {
        editService.addImageToHotelSlider(image);

        return REDIRECT + ROOM_SETTINGS;
    }

    @PostMapping("/edit/roomType")
    public String editRoomType(RoomType roomType) {
        editService.editRoomType(roomType);

        return REDIRECT + ROOM_SETTINGS;
    }
}
