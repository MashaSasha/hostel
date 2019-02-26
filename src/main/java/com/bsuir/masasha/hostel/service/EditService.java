package com.bsuir.masasha.hostel.service;

import com.bsuir.masasha.hostel.domain.Hotel;
import com.bsuir.masasha.hostel.domain.RoomType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface EditService {
    void editHotel(Hotel newHotel);

    void deleteHotelImg(String image, Long id);

    Hotel findHotel();

    void addRoomType(String newRoomType, Double roomTypeCost, Integer roomsAmount);

    void editRoomType(RoomType roomType);

    void addImageToHotelSlider(MultipartFile image);
}
