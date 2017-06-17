/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.controller;

/**
 *
 * @author Jose Osuna
 */
public enum ErrorMessage {
    CANNOT_BE_PROCESSED("cannot be processed"),
    ACCESS_DENIED("access denied");

    private final String value;

    private ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
