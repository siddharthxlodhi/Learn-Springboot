package com.sid.sdj2.controllers;

import com.sid.sdj2.Entity.Address;
import com.sid.sdj2.Entity.Student;
import com.sid.sdj2.repo.AddressRepository;
import com.sid.sdj2.repo.StudentRepository;
import com.sid.sdj2.services.StudentService;
import com.sid.sdj2.specification.StudentSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;

@Tag(name = "Student APIs", description = "save,delete,update,etc")
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Operation(summary = "Save Student")
    @PostMapping("/save")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @Operation(summary = "Delete Student")
    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        return studentService.delete(id);
    }

    @Operation(summary = "Update Student Address by Ad-ID")
    @PutMapping("update/{add_id}")
    private ResponseEntity<String> updateAddress(@RequestBody Address address, @PathVariable Integer add_id) {

        Optional<Address> addressOptional = addressRepository.findById(add_id);
        if (addressOptional.isPresent()) {
            Address address1 = addressOptional.get();
            address1.setColony(address.getColony());
            addressRepository.save(address1);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not updated", HttpStatus.NOT_FOUND);
    }


    //fOR LEARNING JPA DERIVED QUERY METHODS
    @GetMapping("/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        Optional<Student> student = studentService.findByName(name);
        if (student.isPresent()) {
            return new ResponseEntity<Student>(student.get(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("Student not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/{name}")
    public ResponseEntity<String> updateStudentName(@PathVariable String name, @PathVariable Integer id) {
        studentService.updateStudentNameById(name, id);

        return new ResponseEntity<>("updated", HttpStatus.OK);
    }

    @Operation(summary = "getStudentByMobileNo.")
    @GetMapping("/mob/{mobile}")
    public ResponseEntity<Student> getStudentByMobile(@PathVariable BigInteger mobile) {
        Optional<Student> student = studentRepository.findOne(StudentSpecification.hasMobile(mobile)); //By Student Specification
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "getStudentAddressListById")
    @GetMapping("/studentAddress/{id}")
    public ResponseEntity<?> getStudentAddressListById(@PathVariable Integer id) {

        return studentService.findStudentAddressListById(id);
    }


}
