/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.entity;

/**
 *
 * @author Jose Osuna
 */
public class RequestLogin {

    private String email;
    private String password;

    public RequestLogin() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RequestLogin{" + "email=" + email +'}';
    }

}
