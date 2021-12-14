package com.mangotech.edu.service.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerCourseDto {

    private Long id;
    private String name;
    private Double tuition;
    private Double deposit;
}
