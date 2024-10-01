package com.enjoytrip.exception;

public class NoPostException  extends Exception {
    @Override
    public String getMessage(){
        return "No post exists";
    }
}
