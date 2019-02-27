package com.bsuir.masasha.hostel.service.impl;

import com.bsuir.masasha.hostel.domain.Bonus;
import com.bsuir.masasha.hostel.domain.Hotel;
import com.bsuir.masasha.hostel.domain.RoomType;
import com.bsuir.masasha.hostel.repo.HotelRepository;
import com.bsuir.masasha.hostel.repo.RoomTypeRepository;
import com.bsuir.masasha.hostel.service.EditService;
import com.bsuir.masasha.hostel.util.ImageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.Optional;

public class EditServiceImpl implements EditService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void editHotel(Hotel updatedHotel) {
        Hotel hotelToSave = Optional.ofNullable(updatedHotel.getId())
                .flatMap(id -> hotelRepository.findById(id))
                .map(hotel -> {
                    hotel.setAddress(updatedHotel.getAddress());
                    hotel.setDescription(updatedHotel.getDescription());
                    hotel.setHotelName(updatedHotel.getHotelName());
                    hotel.setDaysCountToDiscount(updatedHotel.getDaysCountToDiscount());
                    hotel.setRoomCountToDiscount(updatedHotel.getRoomCountToDiscount());
                    hotel.setDiscountOnDays(updatedHotel.getDiscountOnDays());
                    hotel.setDiscountOnRoomCount(updatedHotel.getDiscountOnRoomCount());
                    return hotel;
                })
                .orElse(updatedHotel);

        hotelRepository.save(hotelToSave);
    }

    @Override
    public void deleteHotelImg(String image, Long id) {
        Hotel hotel = hotelRepository.findById(id).get();
        hotel.remove(image);



        hotelRepository.save(hotel);
    }

    @Override
    public Hotel findHotel() {
        Hotel hotel;
        Iterator<Hotel> hotels = hotelRepository.findAll().iterator();
        if (hotels.hasNext()) {
            hotel = hotels.next();
        } else {
            hotel = new Hotel();
            hotelRepository.save(hotel);
        }
        return hotel;
    }

    @Override
    public void addRoomType(String roomTypeTitle, Double roomTypeCost) {
        Hotel hotel = findHotel();

        RoomType roomType = new RoomType(roomTypeTitle, roomTypeCost);
        hotel.addRoomType(roomType);
        hotelRepository.save(hotel);
    }

    @Override
    public void editRoomType(RoomType roomType) {
        Hotel hotel = findHotel();

        RoomType roomTypeToChange = hotel.popRoomTypeToUpdate(roomType);

        roomType.setBonuses(roomTypeToChange.getBonuses());

        hotel.addRoomType(roomType);

        hotelRepository.save(hotel);
    }

    @Override
    public boolean addImageToHotelSlider(MultipartFile image) {
        Hotel hotelToSave = findHotel();
        String imgPath = ImageUtil.upload(image, uploadPath);

        if (StringUtils.isEmpty(imgPath)) {
            return false;
        }
        hotelToSave.addImage(imgPath);
        hotelRepository.save(hotelToSave);
        return true;
    }

    @Override
    public boolean addBonus(Bonus bonus, Long roomTypeId) {
        return roomTypeRepository.findById(roomTypeId)
                .map(roomType -> {
                    roomType.addAddition(bonus);
                    roomTypeRepository.save(roomType);
                    return true;
                }).orElse(false);
    }
}
