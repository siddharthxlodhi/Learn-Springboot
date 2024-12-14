package com.sid.sdj2.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_table")
@NamedQuery(
        name = "Student.findByMobile",
        query = "SELECT s FROM Student s WHERE s.contact.mobile = :mob"
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "Student's name")
    @Column(name = "student_name",
            nullable = false)
    private String name;

    @Column(unique = true,
            nullable = false)
    private String rollNo;

    //    @JsonIgnore->It will ignore the laptop while getting the student in json response
//    --> it will manage the infinite recusrion due to fetching recursively(@JsonManagedReference)
    @JsonManagedReference
    @OneToOne(mappedBy = "student",fetch = FetchType.EAGER)
    @Cascade({CascadeType.ALL})
    private Laptop laptop;


    @OneToMany(mappedBy = "studentOf",fetch = FetchType.EAGER)
    @Cascade({CascadeType.ALL})
    @JsonManagedReference
    private List<Address> addressList;

    @Embedded   //It will not have a seprate table ,but used in the same table
   private Contact contact;

}
