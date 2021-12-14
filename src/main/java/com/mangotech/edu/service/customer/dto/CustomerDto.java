package com.mangotech.edu.service.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private Long id;
    private String customerCode;
    private String name;
    private String phone;
    private String faceBook;
    private String email;
    private Short genderId;
    private String note;
}
