package com.mangotech.edu.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "edu_bank_account")
@Getter
@Setter
public class BankAccountEntity extends BaseEntity<Long> {

    @Column(name = "name", length = 255, nullable = false)
    @NotNull(message = "Tên tài khoản không được trống")
    private String name;

    @Column(name = "bank_name", nullable = false)
    @NotNull(message = "Bạn sử dụng tài khoản ngân hàng nào?")
    private String bankName;

    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    @Column(name = "type", nullable = false)
    @NotNull(message = "Loại tài khoản không được trống")
    private Short type;

    @OneToOne
    @JoinColumn(name = "facility_id")
    private FacilityEntity facility;

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "note", length = 1000)
    private String note;

}
