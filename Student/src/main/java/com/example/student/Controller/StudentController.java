package com.example.student.Controller;

import com.example.student.ApiResponse.ApiResponse;
import com.example.student.Model.Student;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api/v1/student")
@RestController
public class StudentController {

    ArrayList<Student> students = new ArrayList<>();

    // CRUD

    //• Create a new student (ID, name, age, degree, GPA)
    // every end point need three things : method , mapping , path
    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student) {
        students.add(student);
        return new ApiResponse("Student added");
    }

    //• Display all students.
    @GetMapping("/get-student")
    public ArrayList<Student> getStudents() {
        return students;
    }

    //• Update a student
    @PutMapping("/update/{index}")
    public ApiResponse updateStudent(@PathVariable int index , @RequestBody Student student) {
        if (index < 0 || index >= students.size()) {
            return new ApiResponse("Student not found");
        } students.set(index, student);
        return new ApiResponse("Student updated");
    }

    //• Delete a student
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteStudent(@PathVariable int index) {
        students.remove(index);
        return new ApiResponse("Student deleted");
    }


    //• Based on GPA, classify students into honors categories.
    @PutMapping("/honor/{index}")
    public ApiResponse honorsCategories(@PathVariable int index){
        Student student = students.get(index);
         double GPA = student.getGPA();
        if(GPA >= 4.75){
           return new ApiResponse("First class Honor");
        }else if(GPA >= 4.25){
            return new ApiResponse("Second class Honor");
        } else if(GPA >= 3.75){
            return new ApiResponse("Third class Honor");
        } else
            return new ApiResponse("No Honor Class");
        }


        public double averageGPA(){
        double averageGPA = 0;
        for(Student s : students){
            averageGPA = averageGPA + s.getGPA();
        }  return averageGPA/students.size();
        }

    //• Display a group of students whose GPA is greater than the average GPA.
    @GetMapping("/display")
    public ArrayList<Student> displayAveGPAStudents(){
        ArrayList<Student> studentss = new ArrayList<>();
        for(Student student : students){
            if(student.getGPA() > averageGPA()){
                studentss.add(student);
            }
        } return studentss;
}


}
