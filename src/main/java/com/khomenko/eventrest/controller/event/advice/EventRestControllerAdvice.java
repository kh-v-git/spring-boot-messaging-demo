/*
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023.
 */
package com.khomenko.eventrest.controller.event.advice;

import com.khomenko.eventrest.utils.exception.ApplicationBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EventRestControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ApplicationBusinessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String applicationBusinessExceptionHandler(ApplicationBusinessException ex) {
        return ex.getMessage();
    }
}
