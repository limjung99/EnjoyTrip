package com.enjoytrip.exception;

public class UserInvalidException extends Exception{
    @Override
    public String getMessage() {
        return "Post wirter and user who want to change this post mismathc";
    }
}
