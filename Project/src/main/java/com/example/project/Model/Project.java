package com.example.project.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Project {
    // project Class : ID , title , description , status, companyName

    private int id;
    private String title;
    private String description;
    private String status;
    private String companyName;

}
