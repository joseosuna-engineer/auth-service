/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.controller;

import net.prottonne.lab.auth.entity.RequestLogin;
import net.prottonne.lab.auth.entity.ResponseLogin;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseLogin auth(@RequestBody RequestLogin requestLogin) {
        if ("user@email.net".equals(requestLogin.getEmail())
                && "1234".equals(requestLogin.getPassword())) {
            return new ResponseLogin("QpwL5tke4Pnpja7X");
        }

        throw new RuntimeException("access denied");
    }
}
