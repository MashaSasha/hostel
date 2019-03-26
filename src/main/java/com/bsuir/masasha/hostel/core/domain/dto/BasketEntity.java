package com.bsuir.masasha.hostel.core.domain.dto;

import com.bsuir.masasha.hostel.core.domain.PromoCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BasketEntity implements Serializable {

    private List<BasketEntityDTO> entities;
    private PromoCode promocode;

    public void addEntity(BasketEntityDTO basketEntityDTO) {
        if (entities == null) {
            entities = new ArrayList<>();
        }
        entities.add(basketEntityDTO);
    }

    public BasketEntity() {
    }

}
