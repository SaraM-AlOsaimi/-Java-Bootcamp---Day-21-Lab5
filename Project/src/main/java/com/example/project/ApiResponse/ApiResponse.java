package com.example.project.ApiResponse;

import com.example.project.Model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class ApiResponse {

    String message;
    private ArrayList<Project> projects;


    public ApiResponse(String message) {
        this.message = message;
    }
}
