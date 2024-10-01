package com.enjoytrip.exception;

public class TokenInvalidException extends Exception {
    @Override
    public String getMessage() {
        return "Token and request user mismatch";
    }
}
