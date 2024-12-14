package com.sid.sdj2.repo;

import com.sid.sdj2.Entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CategoryRepo extends CrudRepository<Category,String> {
}
