package com.bsuir.masasha.hostel.core.service;

import com.bsuir.masasha.hostel.core.domain.dto.StatisticLineDTO;

import java.util.List;

public interface StatisticService {
    List<StatisticLineDTO> calculatePromoLines(Integer year);

    List<StatisticLineDTO> calculateProfitLines(Integer year, boolean b);
}
