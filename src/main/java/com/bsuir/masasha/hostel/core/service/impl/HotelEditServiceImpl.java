package com.bsuir.masasha.hostel.core.service.impl;

import com.bsuir.masasha.hostel.core.domain.Room;
import com.bsuir.masasha.hostel.core.repo.RoomRepository;
import com.bsuir.masasha.hostel.core.util.ImageUtil;
import com.bsuir.masasha.hostel.core.domain.Bonus;
import com.bsuir.masasha.hostel.core.domain.Hotel;
import com.bsuir.masasha.hostel.core.domain.RoomType;
import com.bsuir.masasha.hostel.core.repo.HotelRepository;
import com.bsuir.masasha.hostel.core.repo.RoomTypeRepository;
import com.bsuir.masasha.hostel.core.service.HotelEditService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class HotelEditServiceImpl implements HotelEditService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    RoomRepository roomRepository;

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
        Hotel hotelToSave = hotelRepository.findById(id)
                .map(hotel -> {
                    hotel.popImage(image);
                    return hotel;
                }).orElse(new Hotel());
        hotelRepository.save(hotelToSave);
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
    public void addRoomType(String roomTypeTitle, Integer roomTypeCost) {
        Hotel hotel = findHotel();

        RoomType roomType = new RoomType(roomTypeTitle, roomTypeCost);
        hotel.addRoomType(roomType);
        hotelRepository.save(hotel);
    }

    @Override
    public void editRoomType(RoomType roomType, MultipartFile image) {
        Hotel hotel = findHotel();
        RoomType roomTypeToChange = hotel.popRoomTypeToUpdate(roomType);
        if (StringUtils.isNotEmpty(image.getOriginalFilename())) {
            String imgPath = ImageUtil.upload(image, uploadPath);
            roomType.setPreviewImage(imgPath);
        }

        roomType.setBonuses(roomTypeToChange.getBonuses());
        roomType.setRooms(roomTypeToChange.getRooms());
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
                    roomType.addBonus(bonus);
                    roomTypeRepository.save(roomType);
                    return true;
                }).orElse(false);
    }

    @Override
    public boolean addRoomToRoomType(Integer roomNumber, Long roomTypeId) {
        Room room = roomRepository.findByRoomNumber(roomNumber);
        if (room == null) {
            RoomType rt = roomTypeRepository.findById(roomTypeId).get();
            rt.addRoom(new Room(roomNumber, roomTypeId));
            roomTypeRepository.save(rt);
            return true;
        }
        return false;
    }

    @Override
    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }
}
