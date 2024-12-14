package com.sid.sdj2.specification;

import com.sid.sdj2.Entity.Contact;
import com.sid.sdj2.Entity.Student;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigInteger;

public class StudentSpecification {


    public static Specification<Student> hasName(String name) {
        return (root, query, builder) -> {
            return builder.equal(root.get("name"), name);
        };
    }

    public static Specification<Student> rollNoLike(String rollNo) {
        return (root, query, builder) -> {
            return builder.like(root.get("rollNo"), "%" + rollNo + "%");
        };
    }
    public static Specification<Student> hasMobile(BigInteger mobile) {
        return (Root<Student> root, CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder ) -> {
            return criteriaBuilder.equal(root.join("contact").get("mobile"),mobile);

        };
    }


}
