package com.bsuir.masasha.hostel.config;

import com.bsuir.masasha.hostel.service.EditService;
import com.bsuir.masasha.hostel.service.impl.EditServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public EditService getEditService() {
        return new EditServiceImpl();
    }
}
