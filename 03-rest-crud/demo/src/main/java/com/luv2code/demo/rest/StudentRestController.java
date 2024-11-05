package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    @PostConstruct
    public void loadDate(){
        students = new ArrayList<>();

        students.add(new Student("Nick", "Jonas"));
        students.add(new Student("Justin", "Bieber"));
        students.add(new Student("Joe", "Locke"));
    }
    @GetMapping("/students")
    public List<Student> getStudents() {
        // Behind the scene, use Jackson to map to JSON
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {

        if(studentId >= students.size() || studentId < 0){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return students.get(studentId);
    }

}






