package com.example.expense_tracker_app.exception;

public class CategoryInUseException extends RuntimeException{
    public CategoryInUseException(String message){
        super(message);
    }
}
