/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.entity;

/**
 *
 * @author Jose Osuna
 */
public class RequestProfile {

    private String email;

    public RequestProfile() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RequestProfile{" + "email=" + email + '}';
    }

}
