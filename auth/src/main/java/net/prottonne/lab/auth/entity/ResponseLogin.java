/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.entity;

/**
 *
 * @author Jose Osuna
 */
public class ResponseLogin {

    private String token;

    public ResponseLogin() {
        super();
    }

    public ResponseLogin(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ResponseLogin{" + "token=" + token + '}';
    }

}
