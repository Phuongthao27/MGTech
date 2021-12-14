package com.mangotech.edu.service.customer.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CustomerInput {
    @NotBlank(message = "Tên khách hàng không được để trống!")
    private String name;

    @NotBlank(message = "Số điện thoại không được để trống!")
    @Pattern(regexp = "^[0-9]{10,11}", message = "Số điện thoại không hợp lệ, Số điện thoại phải có 10 hoặc 11 số")
    private String phone;

    private String faceBook;

    private String customerCode;

    @NotNull(message = "Giới tính không được để trống!")
    private Short genderId;

    private String note;

    private String email;
}
