package com.bsuir.masasha.hostel.core.service.impl;

import com.bsuir.masasha.hostel.core.domain.PromoCode;
import com.bsuir.masasha.hostel.core.domain.dto.StatisticLineDTO;
import com.bsuir.masasha.hostel.core.repo.PromoCodeRepository;
import com.bsuir.masasha.hostel.core.repo.RoomTypeRepository;
import com.bsuir.masasha.hostel.core.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;


    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<StatisticLineDTO> calculatePromoLines(Integer year) {
        List<StatisticLineDTO> lines = new ArrayList<>();
        String yearString = year + "-01-01";

        List<PromoCode> promoCodes = promoCodeRepository.getPromoNames();
        promoCodes.forEach(promoCode -> {
            StatisticLineDTO line = new StatisticLineDTO();
            line.setCollectionAlias(promoCode.getCode());
            line.setSeriesType("line");
            List<Double> dots = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                Integer count = jdbcTemplate.queryForObject(
                        "SELECT count(*) FROM reservation WHERE promo_code_id = ? " +
                                "and start_date between ? + interval " + i + " month and ? + interval " + (i + 1) + " month;",
                        new Object[]{promoCode.getId(), yearString, yearString},
                        Integer.class
                );
                dots.add((double) count);
            }
            line.setData(dots);
            lines.add(line);
        });

        return lines;
    }

    @Override
    public List<StatisticLineDTO> calculateProfitLines(Integer year, boolean isAverage) {
        List<StatisticLineDTO> lines = new ArrayList<>();
        String yearString = year + "-01-01";

        roomTypeRepository.findAll().forEach(roomType -> {
            StatisticLineDTO line = new StatisticLineDTO();
            line.setCollectionAlias(roomType.getTitle());
            line.setSeriesType("line");
            List<Double> dots = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                Double sum = jdbcTemplate.queryForObject(
                        "SELECT sum(r.cost) FROM reservation r WHERE start_date between ? + interval " + i +" month and ? + interval " + (i + 1) +" month;",
                        new Object[]{yearString, yearString},
                        Double.class
                );
                if (sum == null) {
                    sum = 0.;
                }
                if (isAverage) {
                    Integer count = jdbcTemplate.queryForObject(
                            "SELECT count(r.cost) FROM reservation r WHERE start_date between ? + interval ? month and ? + interval ? month;",
                            new Object[]{yearString, i, yearString, i + 1},
                            Integer.class
                    );
                    if (count == 0) {
                        dots.add(0.);
                    } else {
                        dots.add(sum / count);
                    }
                } else {
                    dots.add(sum);
                }
            }
            line.setData(dots);
            lines.add(line);
        });

        return lines;
    }
}
