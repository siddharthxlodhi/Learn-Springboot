package com.sid.springsecurity3.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {


    @GetMapping("/greet")
    public String test() {
        return "HEllO";
    }





}
