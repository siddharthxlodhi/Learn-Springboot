package com.sid.sdj2.controllers;

import com.sid.sdj2.Entity.Category;
import com.sid.sdj2.repo.CategoryRepo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Tag(name = "Category APIs")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepo categoryRepo;

    @PostMapping("/save")
    public ResponseEntity<String> saveCategory(@RequestBody Category category) {
        categoryRepo.save(category);
        return new ResponseEntity<String>("CategorySaved", HttpStatus.OK);
    }


}
