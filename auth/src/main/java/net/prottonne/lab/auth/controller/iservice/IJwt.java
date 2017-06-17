/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.controller.iservice;

import net.prottonne.lab.auth.entity.RequestLogin;
import net.prottonne.lab.auth.entity.ResponseLogin;

/**
 *
 * @author Jose Osuna
 */
public interface IJwt {

    public ResponseLogin auth(RequestLogin requestLogin);

    public void validateJwtToken(String token);

}
