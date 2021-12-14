package com.mangotech.edu.service.teacher.param;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TeacherParam {
    private Long idFacility;
    private String nameTeacher;
    private Short gender;
    private Short national;
    private Long dateOfBirth;
    private Long expectedDate;
    private Long dayWillCome;
    private Long expectedDateToGo;
    private Short classId;
    private Short status;
    private String note;
}
