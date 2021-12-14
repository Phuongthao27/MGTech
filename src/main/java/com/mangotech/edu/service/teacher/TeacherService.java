package com.mangotech.edu.service.teacher;

import com.mangotech.edu.domain.TeacherEntity;
import com.mangotech.edu.service.facility.dto.FacilityDto;
import com.mangotech.edu.service.teacher.dto.TeacherDto;
import com.mangotech.edu.service.teacher.input.TeacherInput;
import com.mangotech.edu.service.teacher.param.TeacherParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {
    ResponseEntity<Page<TeacherDto>> search(TeacherParam param, Pageable pageable);

    ResponseEntity<TeacherDto> createTeacher(TeacherInput inputTeacher);

    ResponseEntity<List<TeacherDto>> deleteTeacher(Long[] ids);

    ResponseEntity<TeacherDto> updateTeacher(Long idTeacher, TeacherInput teacherInput);

    ResponseEntity<Page<TeacherDto>> searchTeacherFacility(TeacherParam param, Pageable pageable);
}
