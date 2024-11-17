package com.example.project.Controller;

import com.example.project.ApiResponse.ApiResponse;
import com.example.project.Model.Project;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;



@RestController
@RequestMapping("api/v1/project")

public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    //• Create a new project (ID,title , description , status, companyName)
    @PostMapping("/create")
    public ApiResponse createProject(@RequestBody Project project) {
        projects.add(project);
        return new ApiResponse("Project created successfully");
    }

    //• Display all project .
    @GetMapping("/get")
    public ArrayList<Project> getProjects() {
        return projects;
    }
    //• Update a project
    @PutMapping("/update/{index}")
    public ApiResponse updateProject(@PathVariable int index, @RequestBody Project project) {
        if (index < 0 || index >= projects.size()) {
            return new ApiResponse("Project not found");
        }
        projects.set(index, project);
        return new ApiResponse("Project updated successfully");
    }
    //• Delete a project
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index) {
        if (index < 0 || index >= projects.size()) {
            return new ApiResponse("Project not found");
        }
        projects.remove(index);
        return new ApiResponse("Project deleted successfully");
    }

    //• Change the project status as done or not done
    @PutMapping("/change/status/{index}")
    public ApiResponse changeStatus(@PathVariable int index) {
        if (index < 0 || index >= projects.size()) {
            return new ApiResponse("Project not found");
        }
        Project project = projects.get(index);
        if (project.getStatus().equalsIgnoreCase("Not Done")) {
            project.setStatus("Done");
            return new ApiResponse("Project status updated to Done");
        } else if (project.getStatus().equalsIgnoreCase("Done")) {
            return new ApiResponse("Project status is already Done");
        } else {
            return new ApiResponse("Invalid status value for the project");
        }
    }
    //• Search for a project by given title
    @GetMapping("/search")
    public ApiResponse searchProject(@RequestParam String title) {
        ArrayList<Project> matchingProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getTitle().equalsIgnoreCase(title)) {
                matchingProjects.add(project);
            }
        }
        if (matchingProjects.isEmpty()) {
            return new ApiResponse("Project not found");
        } else {
            return new ApiResponse("Projects found", matchingProjects);
        }
    }

    //• Display All project for one company by companyName
    @GetMapping("/get/company/name")
    public ApiResponse displayAllProjects(@RequestParam String companyName) {
        ArrayList<Project> projectsByCompany = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equalsIgnoreCase(companyName)) {
                projectsByCompany.add(project);
            }
        }
        if (projectsByCompany.isEmpty()) {
            return new ApiResponse("No projects found for company: " + companyName);
        }
        return new ApiResponse("Projects found for company: " + companyName, projectsByCompany);
    }

}
