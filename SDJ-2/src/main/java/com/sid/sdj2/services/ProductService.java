package com.sid.sdj2.services;

import com.sid.sdj2.Entity.Product;
import com.sid.sdj2.repo.ProductRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.DatabaseInitializationMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

//    @PostConstruct
//    public void saveProduct() {
//        List<Product> productList = IntStream.rangeClosed(1, 200)
//                .mapToObj(i -> new Product("id" + i, "Product" + i, new Random().nextInt(100), new Random().nextInt(5000)))
//                .toList();
//        productRepo.saveAll(productList);
//    }


    //SORTING DATA
    public List<Product> findProductsWithSorting(Sort.Direction directionEnum, String field) {
        List<Product> productList = productRepo.findAll(Sort.by(directionEnum, field));
        return productList;
    }

    //PAGING DATA
    public Page<Product> findProductsWithPaging(int pageSize, int offset) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize);
        Page<Product> productPage = productRepo.findAll(pageRequest);
        return productPage;

    }

    //SORTING AND PAGING DATA
    public Page<Product> findProductsWithSoringPaging(int pageSize, int offset, Sort.Direction direction, String field) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize, Sort.by(direction, field));
        Page<Product> productPage = productRepo.findAll(pageRequest);
        return productPage;

    }

}
