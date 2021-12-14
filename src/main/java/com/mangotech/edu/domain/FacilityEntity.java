package com.mangotech.edu.domain;
import javax.persistence.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "edu_facility")
public class FacilityEntity extends BaseEntity<Long> {

    @Column(name = "name", length = 155)
    private String name;

    @Column(name = "note", length = 1505)
    private String note;

    @ManyToMany(mappedBy = "facilities")
    private List<UserEntity> users;

    @OneToOne(mappedBy = "facility")
    private BankAccountEntity bankAccount;
    @OneToMany(mappedBy = "facility")
    private List<CourseEntity> courses = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private AreaEntity area;

    @OneToMany(mappedBy = "facility")
    private List<TeacherEntity> teacherEntities;

}
