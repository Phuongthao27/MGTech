package com.mangotech.edu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor

@Entity(name = "ad_department")
public class DepartmentEntity extends BaseEntity<Long> {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = false, unique = true)
    private String role;
}
