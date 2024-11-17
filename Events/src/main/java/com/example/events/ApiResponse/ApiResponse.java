package com.example.events.ApiResponse;

import com.example.events.Model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private Object data;

    // Constructor for message and data (event or list of events)
    public ApiResponse(String message) {
        this.message = message;
    }
}
