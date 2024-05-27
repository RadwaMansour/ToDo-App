package com.example.TodoService.TodoService.controller;

import com.example.TodoService.TodoService.exception.EmptyDataException;
import com.example.TodoService.TodoService.exception.ItemNotFoundException;
import com.example.TodoService.TodoService.exception.response.ItemErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ItemExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ItemErrorResponse> ProductNotFound(ItemNotFoundException exception){

        ItemErrorResponse errorResponse=new ItemErrorResponse();
        errorResponse.setCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        return  new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler
    public ResponseEntity<ItemErrorResponse> emptyData(EmptyDataException exception){

        ItemErrorResponse errorResponse=new ItemErrorResponse();
        errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        return  new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }



}


