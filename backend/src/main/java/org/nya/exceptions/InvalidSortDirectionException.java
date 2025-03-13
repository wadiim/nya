package org.nya.exceptions;

public class InvalidSortDirectionException extends Exception {

    public InvalidSortDirectionException() {}

    public InvalidSortDirectionException(String message) {
        super(message);
    }

    public InvalidSortDirectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSortDirectionException(Throwable cause) {
        super(cause);
    }
}
