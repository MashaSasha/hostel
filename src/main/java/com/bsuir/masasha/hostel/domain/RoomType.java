package com.bsuir.masasha.hostel.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String previewImage;
    private String title;
    private Double cost;
    private String description;
    private Integer sleepPlacesAmount;

    private Integer roomsAmount;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "roomType_id")
    private List<Addition> additions;

    public RoomType(String roomTypeTitle, Double roomTypeCost, Integer roomsAmount) {
        this.title = roomTypeTitle;
        this.cost = roomTypeCost;
        this.roomsAmount = roomsAmount;
    }

    public void addAddition(Addition addition) {
        additions.add(addition);
    }

    public void popAddition(Addition addition) {
        additions.remove(addition);
    }

    public RoomType() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return Objects.equals(id, roomType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(String previewImage) {
        this.previewImage = previewImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSleepPlacesAmount() {
        return sleepPlacesAmount;
    }

    public void setSleepPlacesAmount(Integer sleepPlacesAmount) {
        this.sleepPlacesAmount = sleepPlacesAmount;
    }

    public List<Addition> getAdditions() {
        return additions;
    }

    public void setAdditions(List<Addition> additions) {
        this.additions = additions;
    }

    public Integer getRoomsAmount() {
        return roomsAmount;
    }

    public void setRoomsAmount(Integer roomsAmount) {
        this.roomsAmount = roomsAmount;
    }
}
