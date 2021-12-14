package com.mangotech.edu.service.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDetailDto {
    private Long id;
    private CustomerFacilityDto facility;
    private Long callDate;
    private CustomerUserDto markPerson;
    private Short stayReturnId;
    private Long appointmentDate;
    private CustomerCourseDto course;
    private Double deposit;
    private CustomerUserDto depositHolder;
    private Double tuition;
    private CustomerUserDto tuitionHolder1;
    private CustomerUserDto tuitionHolder2;
    private CustomerUserDto tuitionHolder3;
    private Long appointmentDeposit;
    private Short actionStatusId;
    private Short customerStatusId;
    private CustomerDto customer;
}
