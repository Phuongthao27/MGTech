package com.mangotech.edu.service.teacher.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TeacherDto {
    private Long id;
    private FacilityDto facility;
    private String nameTeacher;
    private String gender;
    private String national;
    private Long dateOfBirth;
    private Long expectedDate;
    private Long dayWillCome;
    private Long expectedDateToGo;
    private String classId;
    private String statusTeacher;
    private String note;

}
