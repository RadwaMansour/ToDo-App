package com.example.TodoService.TodoService.exception.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemErrorResponse {
    private int code;
    private String message;
    private long timestamp;
}
