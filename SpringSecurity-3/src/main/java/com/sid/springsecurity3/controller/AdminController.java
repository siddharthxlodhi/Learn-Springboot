package com.sid.springsecurity3.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class AdminController {
    @PreAuthorize("hasAuthority('admin:read')")
    @GetMapping
    public String AdminGet() {
        return "Admin Get";
    }

    @PreAuthorize("hasAuthority('admin:update')")
    @PutMapping
    public String AdminUpdate() {
        return "update Admin";
    }

    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping
    public String AdminDelete() {
        return "Delete Admin";
    }

    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping
    public String AdminPost() {
        return "Post Admin";
    }


}
