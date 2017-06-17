/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.constant;

/**
 *
 * @author Jose Osuna
 */
public enum EnviromentConstant {
    JWT_SECRET("WWW_1232");

    private final String value;

    private EnviromentConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
