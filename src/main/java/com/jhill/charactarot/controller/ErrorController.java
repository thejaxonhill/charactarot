package com.jhill.charactarot.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = { IOException.class })
    protected ResponseEntity<Object> handleIOException(IOException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
