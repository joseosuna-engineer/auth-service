/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.controller;

import net.prottonne.lab.auth.iservice.IJwt;
import net.prottonne.lab.auth.entity.RequestLogin;
import net.prottonne.lab.auth.entity.ResponseLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Osuna
 */
@RestController
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IJwt jwtService;

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseLogin auth(@RequestBody RequestLogin requestLogin) {
        logger.info("{}", requestLogin);
        return jwtService.auth(requestLogin);
    }
}
