package com.sid.sdj2.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Contact {
    @Column(unique = true, nullable = false,name = "mob_num")
    private long mobile;
    @Column(unique = true, nullable = false,name = "mail")
    private String mail;
}
