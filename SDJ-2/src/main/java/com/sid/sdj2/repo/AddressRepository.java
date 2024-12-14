package com.sid.sdj2.repo;

import com.sid.sdj2.Entity.Address;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AddressRepository extends CrudRepository<Address, Integer> {
@Query(
        "UPDATE Address a SET a.colony=:colony WHERE a.id=:id"
)
    void updateAddressById(Integer id);

}
