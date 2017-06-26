/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.controller;

import net.prottonne.lab.common.util.exception.ErrorConstant;
import net.prottonne.lab.common.util.exception.ErrorMessage;
import net.prottonne.lab.common.util.exception.RespondMessage;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Osuna
 */
@RestController
public class IndexErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return ErrorConstant.ERROR_PATH;
    }

    @RequestMapping(value = ErrorConstant.ERROR_PATH)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    RespondMessage error() {

        return new RespondMessage(
                ErrorMessage.CANNOT_BE_PROCESSED.getValue()
        );
    }

}
