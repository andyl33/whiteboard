package com.winkball.whiteboard.domain;

/**
 * Indicates that a requested transition is not supported
 */
public class UnknownColumnException extends RuntimeException {

    public UnknownColumnException(String s) {
        super(s);
    }
}
