package com.bsuir.masasha.hostel.core.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class StatisticLineDTO implements Serializable {

    private String seriesType;
    private String collectionAlias;
    private List<Double> data;
}
