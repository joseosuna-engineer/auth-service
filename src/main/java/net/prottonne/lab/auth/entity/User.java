/*
 * Copyright 2017 Coopeuch.
 */
package net.prottonne.lab.auth.entity;

/**
 *
 * @author Jose Osuna
 */
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Boolean auth;

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", auth=" + auth + '}';
    }

}
