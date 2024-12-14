package com.sid.sdj2.Entity;

import com.sid.sdj2.MappedSuperEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Product extends BaseEntity {
    private Integer quantity;
    private double price;
    private
    @ManyToMany
    List<Category> categories = new ArrayList<>();

    public Product(String id, String productName, Integer quantity, double price) {
        super(id,productName);
        this.quantity = quantity;
        this.price = price;
    }
}

