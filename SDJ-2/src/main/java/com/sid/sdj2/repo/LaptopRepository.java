package com.sid.sdj2.repo;

import com.sid.sdj2.Entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
    //Derived Query(Custom Finder)
    public List<Laptop> findByCostLessThan(Integer cost);

    public List<Laptop> findByCostIn(Collection<Integer> cost);

    //Native Query
    @Modifying
    @Transactional
    @Query(
            value = "update laptop set brand=:brand,cost=:cost where student_id=:id",
            nativeQuery = true
    )
    public void updateLaptopByStudentId(@Param("brand") String brand, @Param("cost") Integer cost, @Param("id") Integer id);

}
