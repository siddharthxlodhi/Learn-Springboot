package com.sid.sdj2.dto;

import com.sid.sdj2.Entity.Address;
import lombok.Data;

import java.util.List;
@Data
public class StudentAddressDTO {

    private Integer id;
    private String name;
    private List<Address> addressList;


}
