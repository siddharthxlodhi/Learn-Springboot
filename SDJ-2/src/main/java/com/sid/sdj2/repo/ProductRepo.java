package com.sid.sdj2.repo;

import com.sid.sdj2.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public interface ProductRepo extends JpaRepository<Product,String>, JpaSpecificationExecutor<Product> {




}
