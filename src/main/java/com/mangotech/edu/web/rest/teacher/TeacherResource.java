package com.mangotech.edu.web.rest.teacher;

import com.mangotech.edu.domain.TeacherEntity;
import com.mangotech.edu.service.facility.dto.FacilityDto;
import com.mangotech.edu.service.teacher.TeacherService;
import com.mangotech.edu.service.teacher.dto.TeacherDto;
import com.mangotech.edu.service.teacher.input.TeacherInput;
import com.mangotech.edu.service.teacher.param.TeacherParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/teacher")
@AllArgsConstructor
public class TeacherResource {

    private final TeacherService teacherService;

    @GetMapping()
    public ResponseEntity<Page<TeacherDto>> search(TeacherParam param, Pageable pageable) {
        return teacherService.search(param, pageable);
    }

    @PostMapping()
    public ResponseEntity<TeacherDto> create(@RequestBody TeacherInput input) {
        return teacherService.createTeacher(input);
    }

    @DeleteMapping()
    public ResponseEntity<List<TeacherDto>> delete(@RequestBody Long[] ids) {
        return teacherService.deleteTeacher(ids);
    }

    @PutMapping("/{idTeacher}")
    public ResponseEntity<TeacherDto> updated(@PathVariable("idTeacher") Long idTeacher,
                                              @RequestBody TeacherInput input) {
        return teacherService.updateTeacher(idTeacher, input);
    }

    @GetMapping("/search-teacher")
    public ResponseEntity<Page<TeacherDto>> searchByTeacherFacility(TeacherParam param,
                                                                    Pageable pageable) {
        return teacherService.searchTeacherFacility(param, pageable);
    }
}
