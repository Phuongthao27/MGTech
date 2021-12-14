package com.mangotech.edu.service.bankaccount.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class BankAccountInput {

    @NotNull(message = "Tên tài khoản không được trống!")
    private String name;

    @NotNull(message = "Tên ngân hàng không được trống")
    private String bankName;

    @NotNull(message = "Số tài khoản không được trống")
    private String bankAccountNumber;

    private Long facilityId;
    private BigDecimal money;
    private String note;
}
