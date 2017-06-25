/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.controller;

import net.prottonne.lab.common.util.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Jose Osuna
 */
@ControllerAdvice
public class ExceptionHandlerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String globalHander(Exception ex) {
        logger.error("{}", ex);
        return ErrorMessage.CANNOT_BE_PROCESSED.getValue();
    }

}
