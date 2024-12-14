package com.sid.sdj2.repo;

import com.sid.sdj2.Inheritance.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResourceRepo extends JpaRepository<Resource, Integer> {


        @Query("SELECT r FROM Resource r WHERE r.id = :id")
        Resource findResourceById(@Param("id") Integer id);



}
