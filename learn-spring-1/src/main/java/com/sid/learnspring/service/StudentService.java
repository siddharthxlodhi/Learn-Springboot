package com.sid.learnspring.service;

import com.sid.learnspring.Entity.Student;
import com.sid.learnspring.repositories.StudentRepository;
import com.sid.learnspring.validator.BeanValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final BeanValidator<Student> studentValidator;


    public ResponseEntity<String> saveStudent(Student student) {
        studentValidator.validate(student);
        Optional<Student> studentExist = studentRepository.findByrollno(student.getRollno());
        if (studentExist.isPresent()) {
            return new ResponseEntity<>("Student already exist with given roll no:" + student.getRollno(), HttpStatus.BAD_REQUEST);

        }
        studentRepository.save(student);
        return new ResponseEntity<>("Student saved successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<Student> findByID(int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (!studentOptional.isPresent()) {
            throw new EntityNotFoundException("Student with id " + id + " not found");
        }
        return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Student> updateStudent(Student student, int id) {

        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentOptional.get().setName(student.getName());
            studentOptional.get().setAge(student.getAge());
            studentOptional.get().setEmail(student.getEmail());
            saveStudent(studentOptional.get());
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<Student> findByrollno(int rollno) {
        Optional<Student> studentOptional = studentRepository.findByrollno(rollno);
        if (!studentOptional.isPresent()) {
            throw new EntityNotFoundException("Student with rollno " + rollno + " not found");
        }
        return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
    }
}
