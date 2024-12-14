package com.sid.springsecurity3.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@PreAuthorize("hasRole('USER')")
public class UserController {

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping
    public String UserGet() {
        return "Get User";
    }

    @PreAuthorize("hasAuthority('user:update')")
    @PutMapping
    public String UserUpdate() {
        return "update User";
    }

    @PreAuthorize("hasAuthority('user:delete')")
    @DeleteMapping
    public String UserDelete() {
        return "Delete User";
    }

    @PreAuthorize("hasAuthority('user:create')")
    @PostMapping
    public String UserPost() {
        return "Post User";
    }


}
