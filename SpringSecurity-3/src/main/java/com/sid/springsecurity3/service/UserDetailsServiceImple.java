//package com.sid.springsecurity3.service;
//
//import com.sid.springsecurity3.model.MyUser;
//import com.sid.springsecurity3.repository.MyUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.Optional;
//
//@Component
//public class UserDetailsServiceImple implements UserDetailsService {
//
//
//    private MyUserRepository userRepository;
//
//    @Autowired
//    public void set(MyUserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<MyUser> user = userRepository.findByUsername(username);
//        if (user.isPresent()) {
//            return user.get();
//        }
//
//        throw new UsernameNotFoundException("User not found with name:" + username);
//    }
//}
