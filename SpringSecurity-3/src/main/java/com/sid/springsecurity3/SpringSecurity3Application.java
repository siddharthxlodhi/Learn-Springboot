package com.sid.springsecurity3;

import com.sid.springsecurity3.model.MyUser;
import com.sid.springsecurity3.repository.MyUserRepository;
import com.sid.springsecurity3.validator.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.sid.springsecurity3.Roles.ADMIN;


@SpringBootApplication
public class SpringSecurity3Application implements CommandLineRunner {
    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectValidator<MyUser> objectValidator;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        MyUser myUser = new MyUser();
//        myUser.setUsername("Sid");
//        myUser.setPassword(passwordEncoder.encode("Sid@12"));
//        myUser.setRoles(ADMIN);
//        myUserRepository.save(myUser);
    }
}
