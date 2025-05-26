package io.andylee.whiteboard.domain;

/**
 * Indicates that a Ticket does not support the requested state change
 */
public class InvalidStateException extends RuntimeException {

    public InvalidStateException(String s) {
        super(s);
    }
}
