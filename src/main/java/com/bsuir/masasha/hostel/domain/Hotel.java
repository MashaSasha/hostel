package com.bsuir.masasha.hostel.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
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

    public void popImage(String image) {
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

    public void updateRoomType(RoomType roomType) {
        RoomType roomTypeToChange = roomTypes.get(roomTypes.indexOf(roomType));

    }


    public Hotel() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(List<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public Double getDiscountOnRoomCount() {
        return discountOnRoomCount;
    }

    public void setDiscountOnRoomCount(Double discountOnRoomCount) {
        this.discountOnRoomCount = discountOnRoomCount;
    }

    public Integer getRoomCountToDiscount() {
        return roomCountToDiscount;
    }

    public void setRoomCountToDiscount(Integer roomCountToDiscount) {
        this.roomCountToDiscount = roomCountToDiscount;
    }

    public Double getDiscountOnDays() {
        return discountOnDays;
    }

    public void setDiscountOnDays(Double discountOnDays) {
        this.discountOnDays = discountOnDays;
    }

    public Integer getDaysCountToDiscount() {
        return daysCountToDiscount;
    }

    public void setDaysCountToDiscount(Integer daysCountToDiscount) {
        this.daysCountToDiscount = daysCountToDiscount;
    }


}
