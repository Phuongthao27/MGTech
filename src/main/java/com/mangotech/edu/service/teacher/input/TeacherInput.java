package com.mangotech.edu.service.teacher.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class TeacherInput {
    @NotEmpty(message = "Cơ sở không được để trống")
    private Long idFacility;

    @NotEmpty(message = "Tên giáo viên không được để trống")
    @Size(min = 6, max = 20)
    private String nameTeacher;

    @NotEmpty(message = "Giới tính không được để trống")
    private Short gender;

    @NotEmpty(message = "Quốc gia không được để trống")
    private Short national;

    @NotEmpty(message = "Ngày sinh không được để trống")
    private Long dateOfBirth;

    @NotEmpty(message = "Ngày dự kiến không được để trống")
    private Long expectedDate;

    @NotEmpty(message = "Ngày đến không được để trống")
    private Long dayWillCome;

    private Long expectedDateToGo;

    @NotEmpty(message = "Lớp không được để trống")
    private Short classId;

    @NotEmpty(message = "Trạng thái không được để trống")
    private Short statusTeacher;
    private String note;

}
