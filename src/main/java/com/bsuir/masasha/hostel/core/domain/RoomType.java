package com.bsuir.masasha.hostel.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class RoomType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String previewImage;
    private String title;
    private Integer cost;
    private String description;
    private Integer sleepPlacesAmount;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "roomType_id")
    private List<Room> rooms;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "roomType_id")
    private List<Bonus> bonuses;

    public RoomType(String roomTypeTitle, Integer roomTypeCost) {
        this.title = roomTypeTitle;
        this.cost = roomTypeCost;
    }

    public void addBonus(Bonus bonus) {
        bonuses.add(bonus);
    }

    public void addRoom(Room room) {
        rooms.add(room);
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

}
