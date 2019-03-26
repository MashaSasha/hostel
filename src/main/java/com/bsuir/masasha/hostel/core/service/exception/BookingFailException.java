package com.bsuir.masasha.hostel.core.service.exception;

public class BookingFailException extends Exception {

    public BookingFailException() {
    }

    public BookingFailException(String message) {
        super(message);
    }

    public BookingFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingFailException(Throwable cause) {
        super(cause);
    }

}
