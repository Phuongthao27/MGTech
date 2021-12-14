package com.mangotech.edu.service.bankaccount.param;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BankAccountParam {
    private String name;
    private String bankName;
    private String bankAccountNumber;
    private Short type;
    private String facility;
    private BigDecimal money;
    private String note;

    public BankAccountParam(BankAccountParam param) {
    }
}
