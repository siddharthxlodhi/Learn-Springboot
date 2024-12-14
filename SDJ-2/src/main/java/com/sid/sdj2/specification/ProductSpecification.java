package com.sid.sdj2.specification;

import com.sid.sdj2.Entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {


    public static Specification<Product> priceGreaterThan(double price) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get("price"), price);
        };

    }

    public static Specification<Product> priceLessThan(double price) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThan(root.get("price"), price);
        };

    }
}