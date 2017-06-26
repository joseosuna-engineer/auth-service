/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.prottonne.lab.common.util.exception.ErrorMessage;
import net.prottonne.lab.common.util.exception.RespondMessage;
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
    public RespondMessage globalHander(HttpServletRequest request,
            HttpServletResponse response, Exception ex) {
        logger.error("{}", ex);
        return new RespondMessage(
                "Handler " + ErrorMessage.CANNOT_BE_PROCESSED.getValue()
        );
    }

}
