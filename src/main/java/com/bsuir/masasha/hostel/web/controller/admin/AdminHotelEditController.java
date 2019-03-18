package com.bsuir.masasha.hostel.web.controller.admin;

import com.bsuir.masasha.hostel.core.domain.Bonus;
import com.bsuir.masasha.hostel.core.domain.Hotel;
import com.bsuir.masasha.hostel.core.domain.PromoCode;
import com.bsuir.masasha.hostel.core.domain.RoomType;
import com.bsuir.masasha.hostel.core.service.HotelEditService;
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

import static com.bsuir.masasha.hostel.web.WebConstants.*;

@Controller
@RequestMapping("/admin/hotel")
public class AdminHotelEditController {

    @Autowired
    private HotelEditService hotelEditService;

    //    @ModelAttribute("message") final String message
    @GetMapping("/editor")
    public String editSettings(HttpServletRequest request, Model model) {
        Hotel hotel = hotelEditService.findHotel();

        // достать сообщение из сессии и положить в модель
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
        hotelEditService.editHotel(hotel);

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }

    @PostMapping("/delete/img")
    public String deleteHotelImg(@RequestParam String image, @RequestParam Long id) {
        hotelEditService.deleteHotelImg(image, id);

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }

    @PostMapping("/add/roomType")
    public String addRoomType(@RequestParam String roomTypeTitle, @RequestParam Integer roomTypeCost) {
        hotelEditService.addRoomType(roomTypeTitle, roomTypeCost);

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }

    @PostMapping("/add/image")
    public String addImage(@RequestParam("image") MultipartFile image, HttpServletRequest request) {
        if (!hotelEditService.addImageToHotelSlider(image)) {
            String message = "Ошибка при попытке сохранить картинку";
            request.getSession(true).setAttribute("message", message);
        }

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }

    @PostMapping("/add/promocode")
    public String addPromoCode(PromoCode promoCode, HttpServletRequest request) {
        hotelEditService.addPromoCode(promoCode);

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }

    @PostMapping("/edit/roomType")
    public String editRoomType(@RequestParam("image") MultipartFile image, RoomType roomType) {
        hotelEditService.editRoomType(roomType, image);

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }

    @PostMapping("/add/bonus")
    public String addBonus(@RequestParam Long roomTypeId, Bonus bonus, HttpServletRequest request) {
        if (!hotelEditService.addBonus(bonus, roomTypeId)) {
            String message = "Ошибка при попытке добавить бонус";
            request.getSession(true).setAttribute("message", message);
        }

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }

    @PostMapping("/add/room")
    public String addRoom(@RequestParam Integer roomNumber, @RequestParam Long roomTypeId, HttpServletRequest request) {
        boolean isSuccess = hotelEditService.addRoomToRoomType(roomNumber, roomTypeId);
        if (!isSuccess) {
            String message = "Такой номер комнаты уже занят";
            request.getSession(true).setAttribute("message", message);
        }

        return REDIRECT + HOTEL_EDITOR_MAPPING;
    }
}
