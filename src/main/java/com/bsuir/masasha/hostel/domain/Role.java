package com.bsuir.masasha.hostel.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, SERVER_ADMIN, ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
