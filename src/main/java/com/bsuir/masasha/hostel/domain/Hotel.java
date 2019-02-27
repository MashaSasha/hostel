package com.bsuir.masasha.hostel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;
    private String description;
    private String hotelName;

    private Double discountOnRoomCount;
    private Integer roomCountToDiscount;
    private Double discountOnDays;
    private Integer daysCountToDiscount;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "img_id"))
    private Set<String> images;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private List<RoomType> roomTypes;

    public void addImage(String imgPath) {
        images.add(imgPath);
    }

    public void remove(String image) {
        images.remove(image);
    }

    public void addRoomType(RoomType roomType) {
        if (!roomTypes.contains(roomType)) {
            roomTypes.add(roomType);
        }
    }

    public RoomType popRoomTypeToUpdate(RoomType roomType) {
        int index = roomTypes.indexOf(roomType);
        if (index != -1) {
            return roomTypes.remove(index);
        }
        return null;
    }

    public Hotel() {
    }

}
