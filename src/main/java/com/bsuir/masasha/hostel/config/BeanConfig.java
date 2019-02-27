package com.bsuir.masasha.hostel.config;

import com.bsuir.masasha.hostel.core.service.HotelEditService;
import com.bsuir.masasha.hostel.core.service.impl.HotelEditServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public HotelEditService getEditService() {
        return new HotelEditServiceImpl();
    }
}
