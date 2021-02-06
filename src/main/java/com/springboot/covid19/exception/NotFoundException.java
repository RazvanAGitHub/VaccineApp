package com.springboot.covid19.exception;

public class NotFoundException extends RuntimeException {

    //	private static final long serialVersionUID = 1234567;

    public NotFoundException(String message) {
        super(message);
    }
}
