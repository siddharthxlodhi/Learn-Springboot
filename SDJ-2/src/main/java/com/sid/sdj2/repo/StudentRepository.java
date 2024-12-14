package com.sid.sdj2.repo;
import com.sid.sdj2.Entity.Contact;
import com.sid.sdj2.Entity.Student;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@Component
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
    //Derived Queries
    @Transactional
    Optional<Student> findByName(String name);


    //Native Query
//    @Modifying
//    @Transactional
//    @Query(
//            value = "update student_table set student_name=:name where id=:id",
//            nativeQuery = true
//    )
//    void updateStudentNameByID(@Param("name") String name, @Param("id") Integer id);


    //Use this to understand Transaction Propagation
    @Modifying
    @Transactional
    @Query(
            value = "update student_table set student_name=?1 where id=?2",
            nativeQuery = true
    )
    void updateStudentNameByID(String name, Integer id);



    //Named Query
@Query(name = "Student.findByMobile")
Optional<Student> findByMobile(@Param("mob") BigInteger mob);



}
