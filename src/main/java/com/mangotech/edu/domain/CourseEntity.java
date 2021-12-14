package com.mangotech.edu.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "edu_course")
@Getter
@Setter
public class CourseEntity extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    private FacilityEntity facility;

    @Column(name = "name")
    private String name;

    @Column(name = "open_day")
    private Long openDay;

    @Column(name = "close_day")
    private Long closeDay;

    @Column(name = "deposit", columnDefinition = "DECIMAL(12,2)")
    /* Tiền cọc tối thiểu */
    private Double deposit;

    @Column(name = "tuition", columnDefinition = "DECIMAL(12,2)")
    /* Học phí */
    private Double tuition;
}
