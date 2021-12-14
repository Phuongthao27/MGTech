package com.mangotech.edu.service.customer.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CustomerDetailInput {

    private Long facilityId;
    @NotNull(message = "marketing không được để trống!")
    private Long markPersionId;

    private Short actionStatusId;

    private Short stayReturn;

    private Long appointmentDate;

    private Long courseId;

    private Double deposit;

    private Long depositHolderId;

    private Double tuition1;
    private Long tuitionHolderId1;
    private Double tuition2;
    private Long tuitionHolderId2;
    private Double tuition3;
    private Long tuitionHolderId3;

    private Long appointmenDeposit;

    private Short customerStatusId;

    @NotNull(message = "Ngày gọi không được để trống!")
    private Long callDate;

    private Long customerId;

    private CustomerInput customerInput;

}
