package com.mangotech.edu.service.teacher;

import com.mangotech.edu.domain.FacilityEntity;
import com.mangotech.edu.domain.TeacherEntity;
import com.mangotech.edu.repository.falicity.FacilityRepository;
import com.mangotech.edu.repository.teacher.TeacherRepository;
import com.mangotech.edu.repository.user.UserRepository;
import com.mangotech.edu.service.facility.mapper.FacilityMapper;
import com.mangotech.edu.service.teacher.dto.TeacherDto;
import com.mangotech.edu.service.teacher.input.TeacherInput;
import com.mangotech.edu.service.teacher.mapper.TeacherMapper;
import com.mangotech.edu.service.teacher.param.TeacherParam;
import com.mangotech.edu.uitls.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final FacilityRepository facilityRepository;
    private final UserRepository userRepository;
    private final FacilityMapper facilityMapper;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<TeacherDto>> search(TeacherParam param, Pageable pageable) {
        Page<TeacherDto> result = teacherRepository.search(param, pageable).map(teacherMapper::entityToDto);
        return ResponseEntity.ok().body(result);
    }

    @Override
    @Transactional
    public ResponseEntity<TeacherDto> createTeacher(TeacherInput inputTeacher) {
        FacilityEntity facilityEntity = facilityRepository
            .findByIdAndRemovedIsFalse(inputTeacher.getIdFacility())
            .orElseThrow(() -> new RuntimeException("id Facility không tồn tại"));
        TeacherEntity teacherEntity = teacherMapper.inputToEntity(inputTeacher);
        teacherEntity.setFacility(facilityEntity);
        var result = teacherRepository.saveAndFlush(teacherEntity);
        return ResponseEntity.ok().body(teacherMapper.entityToDto(result));
    }

    @Override
    @Transactional
    public ResponseEntity<List<TeacherDto>> deleteTeacher(Long[] ids) {
        List<TeacherEntity> teacherEntities = new ArrayList<>();
        for (long id : ids) {
            TeacherEntity teacherEntity = teacherRepository
                .findByIdAndRemovedIsFalse(id)
                .orElseThrow(() -> new NullPointerException("Giáo viên không tồn tại!"));
            teacherEntity.setRemoved(true);
            teacherEntities.add(teacherEntity);
            }
        return ResponseEntity.ok().body(teacherMapper.EntitiesToDtos(teacherEntities));
    }

    @Override
    @Transactional
    public ResponseEntity<TeacherDto> updateTeacher(Long idTeacher, TeacherInput teacherInput) {
        TeacherEntity teacherEntity = teacherRepository
            .findByIdAndRemovedIsFalse(idTeacher)
            .orElseThrow(() -> new RuntimeException("Giáo viên không tồn tại"));
        FacilityEntity facilityEntity = facilityRepository
            .findByIdAndRemovedIsFalse(teacherInput.getIdFacility())
            .orElseThrow(() -> new RuntimeException("Cơ sở không tồn tại"));
        teacherEntity.setFacility(facilityEntity);
        teacherMapper.inputToEntity(teacherInput, teacherEntity);
        return ResponseEntity.ok().body(teacherMapper.entityToDto(teacherEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<TeacherDto>> searchTeacherFacility(TeacherParam param, Pageable pageable) {
        String username = SecurityUtils
            .getCurrentUserLogin()
            .orElseThrow(() -> new RuntimeException("Username không tồn tại"));
        Page<TeacherDto> result = teacherRepository.searchFacility(param, username, pageable).map(teacherMapper::entityToDto);
        return ResponseEntity.ok().body(result);
    }
}
