package com.rakib.common.exception.classes;

public class AlredayExistException extends Exception {
    private static final long serialVersionUID = 1L;
    public AlredayExistException(String message) {
        super(message);
    }
}
