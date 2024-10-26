package com.sid.learnspring.controllers;

import com.sid.learnspring.Entity.Student;
import com.sid.learnspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Student> getStudentbyId(@PathVariable int id) {
        return studentService.findByID(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudentbyID(@RequestBody Student student, @PathVariable int id) {
        return studentService.updateStudent(student, id);

    }

    @GetMapping("/rollno/{rollno}")
    public ResponseEntity<Student> getStudentbyrollno(@PathVariable int rollno) {
        return studentService.findByrollno(rollno);
    }


}
