package com.bsuir.masasha.hostel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String previewImage;
    private String title;
    private Double cost;
    private String description;
    private Integer sleepPlacesAmount;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "roomType")
    private List<Room> rooms;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "roomType_id")
    private List<Bonus> bonuses;

    public RoomType(String roomTypeTitle, Double roomTypeCost) {
        this.title = roomTypeTitle;
        this.cost = roomTypeCost;
    }

    public void addAddition(Bonus bonus) {
        bonuses.add(bonus);
    }

    public void popAddition(Bonus bonus) {
        bonuses.remove(bonus);
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
