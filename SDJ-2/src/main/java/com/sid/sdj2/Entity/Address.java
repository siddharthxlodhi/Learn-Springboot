package com.sid.sdj2.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String colony;
    @ManyToOne
    @JoinColumn(name = "st_id")
    @JsonBackReference
    private Student studentOf;


    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", colony='" + colony + '\'' +
                '}';
    }
}
