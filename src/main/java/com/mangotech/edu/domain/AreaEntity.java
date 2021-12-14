package com.mangotech.edu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ad_area")
@Getter
@Setter
@NoArgsConstructor
public class AreaEntity extends BaseEntity<Integer> {

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "description", length = 1505)
    private String description;
}
