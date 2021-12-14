package com.mangotech.edu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "edu_teacher")
@Setter
@Getter
@NoArgsConstructor
public class TeacherEntity extends BaseEntity<Long> implements Serializable {

    @ManyToOne
    @NotNull(message = "Cơ sở không được để trống")
    @JoinColumn(name = "facility_id")
    private FacilityEntity facility;

    @NotNull(message = "Tên giáo viên không được để trống")
    @Size(min = 6, max = 32)
    @Column(name = "name_teacher")
    private String nameTeacher;

    @NotNull(message = "Giới tính không được để trống")
    @Column(name = "gender")
    private Short gender;

    @NotNull(message = "Quốc gia không được để trống")
    @Column(name = "national")
    private Short national;

    @NotNull(message = "Ngày sinh không được để trống")
    @Column(name = "date_of_birth")
    @JsonFormat
    private Long dateOfBirth;

    @Column(name = "expected_date")
    @JsonFormat
    private Long expectedDate;

    @Column(name = "day_will_come")
    @JsonFormat
    private Long dayWillCome;

    @Column(name = "expected_date_to_go")
    @JsonFormat
    private Long expectedDateToGo;

    @NotNull(message = "Lớp học không được để trống")
    @Column(name = "class_id")
    private Short classId;

    @NotNull(message = "Trạng thái không được để trống")
    @Column(name = "status_teacher")
    private Short statusTeacher;

    @Column(name = "note")
    private String note;

}
