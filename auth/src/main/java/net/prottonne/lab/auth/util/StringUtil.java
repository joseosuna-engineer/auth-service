/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.util;

/**
 *
 * @author Jose Osuna
 */
public class StringUtil {

    private StringUtil() {
    }

    public static String getEnv(String name) {
        return System.getenv(name);
    }
}
