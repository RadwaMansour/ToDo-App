package com.example.TodoService.TodoService.exception;

public class ItemNotFoundException extends Exception{
    public ItemNotFoundException(String message)
    {
        super(message);
    }
}
