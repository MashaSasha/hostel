package com.bsuir.masasha.hostel.core.service;

import com.bsuir.masasha.hostel.core.domain.Bonus;
import com.bsuir.masasha.hostel.core.domain.Hotel;
import com.bsuir.masasha.hostel.core.domain.PromoCode;
import com.bsuir.masasha.hostel.core.domain.RoomType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HotelEditService {
    void editHotel(Hotel newHotel);

    void deleteHotelImg(String image, Long id);

    Hotel findHotel();

    void addRoomType(String newRoomType, Integer roomTypeCost);

    void editRoomType(RoomType roomType, MultipartFile image);

    boolean addImageToHotelSlider(MultipartFile image);

    boolean addBonus(Bonus bonus, Long roomTypeId);

    RoomType getRoomTypeById(Long id);

    boolean addRoomToRoomType(Integer roomNumber, Long roomTypeId);

    List<RoomType> getAllRoomTypes();

    boolean addPromoCode(PromoCode promoCode);
}
