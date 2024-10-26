package com.sid.learnspring.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "students_t")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Name should not be null")
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotNull(message = "age should not be null")
    private Integer age;
    @NotNull(message = "rollno should not be null")
    private Integer rollno;
    @NotNull(message = "gender should not be null")
    @NotEmpty(message = "gender should not be empty")
    private String gender;
    @NotNull(message = "email should not be null")
    @NotEmpty(message = "email should not be empty")
    @Email(message = "Provide valid email")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(rollno, student.rollno);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rollno);
    }
}
