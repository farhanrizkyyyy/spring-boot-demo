package com.example.demo.model;

public class ApiResponse {
    private final String message;
    private final Object data;

    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }
}
