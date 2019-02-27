package com.bsuir.masasha.hostel.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    private static final String EXCEPTION = "exception";

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(Exception e, Model model) {
        model.addAttribute(EXCEPTION, e);
        return EXCEPTION;
    }
}
