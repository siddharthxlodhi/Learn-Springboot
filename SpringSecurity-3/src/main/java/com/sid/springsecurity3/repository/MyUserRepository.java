package com.sid.springsecurity3.repository;

import com.sid.springsecurity3.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@Component
public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

    Optional<MyUser> findByUsername(String username);

}
