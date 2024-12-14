package com.sid.sdj2.controllers;

import com.sid.sdj2.Entity.Laptop;
import com.sid.sdj2.Entity.Student;
import com.sid.sdj2.repo.LaptopRepository;
import com.sid.sdj2.repo.StudentRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Tag(name = "Laptop APIs")
@RestController
@RequestMapping("/laptop")
public class LaptopController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LaptopRepository laptopRepository;


//    @PostMapping("/add/id/{student_id}")
//    public ResponseEntity<String> addLaptopToStudent(@RequestBody Laptop laptop, @PathVariable Integer student_id) {
//        Optional<Student> student = studentRepository.findById(student_id);
//        if (student.isPresent()) {
//            laptop.setStudent(student.get());
//            student.get().setLaptop(laptop);
//            studentRepository.save(student.get());
//        }
//        return new ResponseEntity<>("added", HttpStatus.OK);
//
//    }


    @PostMapping("/add")
    public ResponseEntity<String> addLaptopToStudent(@RequestBody Laptop laptop) {
        laptopRepository.save(laptop);
        return new ResponseEntity<>("added", HttpStatus.OK);

    }

    @GetMapping("/{cost}")
    public ResponseEntity<List<Laptop>> getLaptop(@PathVariable Integer cost) {
        List<Laptop> laptopList = laptopRepository.findByCostLessThan(cost);
        return new ResponseEntity<>(laptopList, HttpStatus.OK);
    }

    @GetMapping("/getAllByCost")
    public ResponseEntity<List<Laptop>> getLaptop() {
        Collection<Integer> cost = new ArrayList<>();
        cost.add(1400);
        cost.add(1500);
        List<Laptop> laptopList = laptopRepository.findByCostIn(cost);
        return new ResponseEntity<>(laptopList, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudentLaptop(@RequestBody Laptop laptop, @PathVariable Integer id) {
        laptopRepository.updateLaptopByStudentId(laptop.getBrand(), laptop.getCost(), id);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }
}
