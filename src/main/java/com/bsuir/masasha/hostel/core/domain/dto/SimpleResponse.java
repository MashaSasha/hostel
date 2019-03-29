package com.bsuir.masasha.hostel.core.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResponse {

    private ResponseStatus status;
    private String message;

    public SimpleResponse(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public SimpleResponse() {
    }
}
