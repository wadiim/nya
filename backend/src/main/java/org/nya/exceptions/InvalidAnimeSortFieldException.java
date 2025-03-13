package org.nya.exceptions;

public class InvalidAnimeSortFieldException extends Exception {

    public InvalidAnimeSortFieldException() {}

    public InvalidAnimeSortFieldException(String message) {
        super(message);
    }

    public InvalidAnimeSortFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAnimeSortFieldException(Throwable cause) {
        super(cause);
    }
}
