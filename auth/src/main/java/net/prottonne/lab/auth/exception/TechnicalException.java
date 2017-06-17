/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.exception;

/**
 *
 * @author Jose Osuna
 */
public class TechnicalException extends RuntimeException {

    public TechnicalException() {
        super();
    }

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalException(Throwable cause) {
        super(cause);
    }

}
