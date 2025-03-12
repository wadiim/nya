package org.nya.exceptions;

public class AnimeNotFoundException extends Exception {

    public AnimeNotFoundException() {}

    public AnimeNotFoundException(String message) {
        super(message);
    }

    public AnimeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnimeNotFoundException(Throwable cause) {
        super(cause);
    }
}
