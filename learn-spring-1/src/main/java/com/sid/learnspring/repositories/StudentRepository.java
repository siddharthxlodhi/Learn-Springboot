package com.sid.learnspring.repositories;

import com.sid.learnspring.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Optional<Student>  findByrollno(int rollno);
}
