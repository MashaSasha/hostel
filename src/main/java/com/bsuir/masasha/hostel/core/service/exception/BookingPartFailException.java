package com.bsuir.masasha.hostel.core.service.exception;

public class BookingPartFailException extends Exception {

    public BookingPartFailException() {
        super();
    }

    public BookingPartFailException(String message) {
        super(message);
    }

    public BookingPartFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingPartFailException(Throwable cause) {
        super(cause);
    }
}
