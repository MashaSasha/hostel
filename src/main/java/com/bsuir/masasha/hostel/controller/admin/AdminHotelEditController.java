package com.bsuir.masasha.hostel.controller.admin;

import com.bsuir.masasha.hostel.domain.Bonus;
import com.bsuir.masasha.hostel.domain.Hotel;
import com.bsuir.masasha.hostel.domain.RoomType;
import com.bsuir.masasha.hostel.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.bsuir.masasha.hostel.util.SharedConstants.*;

@Controller
@RequestMapping("/admin/hotel")
public class AdminHotelEditController {

    @Autowired
    private EditService editService;

    //    @ModelAttribute("message") final String message
    @GetMapping("/editor")
    public String editSettings(HttpServletRequest request, Model model) {
        Hotel hotel = editService.findHotel();

        Optional.of(request.getSession(false))
                .ifPresent(s -> {
                    model.addAttribute("message", s.getAttribute("message"));
                    s.removeAttribute("message");
                });

        model.addAttribute(HOTEL_ATR, hotel);
        return HOTEL_EDITOR_TEMPLATE;
    }

    @PostMapping("/edit")
    public String editHotelData(Hotel hotel) {
        editService.editHotel(hotel);

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }

    @PostMapping("/delete/img")
    public String deleteHotelImg(@RequestParam String image, @RequestParam Long id) {
        editService.deleteHotelImg(image, id);

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }

    @PostMapping("/add/roomType")
    public String addRoomType(@RequestParam String roomTypeTitle, @RequestParam Double roomTypeCost) {
        editService.addRoomType(roomTypeTitle, roomTypeCost);

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }

    @PostMapping("/add/image")
    public String addImage(@RequestParam("image") MultipartFile image, HttpServletRequest request) {
        if (!editService.addImageToHotelSlider(image)) {
            String message = "Ошибка при попытке сохранить картинку";
            request.getSession(true).setAttribute("message", message);
        }

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }

    @PostMapping("/edit/roomType")
    public String editRoomType(RoomType roomType) {
        editService.editRoomType(roomType);

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }

    @PostMapping("/add/bonus")
    public String addBonus(@RequestParam Long roomTypeId, Bonus bonus, HttpServletRequest request) {
        if (!editService.addBonus(bonus, roomTypeId)) {
            String message = "Ошибка при попытке добавить бонус";
            request.getSession(true).setAttribute("message", message);
        }

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }
}
