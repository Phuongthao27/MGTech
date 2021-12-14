package com.mangotech.edu.service.bankaccount.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BankAccountDto {
    private Long id;
    private String bankName;
    private String bankAccountNumber;
    private String name;
    private String type;
    private String facilityName;
    private BigDecimal money;
    private String note;
}
