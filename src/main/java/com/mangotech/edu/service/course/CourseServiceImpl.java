package com.mangotech.edu.service.course;

import com.mangotech.edu.domain.CourseEntity;
import com.mangotech.edu.domain.FacilityEntity;
import com.mangotech.edu.repository.course.CourseRepository;
import com.mangotech.edu.repository.falicity.FacilityRepository;
import com.mangotech.edu.service.course.CourseService;
import com.mangotech.edu.service.course.dto.CourseDto;
import com.mangotech.edu.service.course.input.CourseInput;
import com.mangotech.edu.service.course.mapper.CourseMapper;
import com.mangotech.edu.service.course.param.CourseParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Duong.PI
 */
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    private final FacilityRepository facilityRepository;

    private final CourseMapper mapper;

    @Override
    public ResponseEntity<Page<CourseDto>> find(CourseParam input, Pageable pageable) {
        Page<CourseEntity> courseDtos = repository.find(input, pageable);
        return ResponseEntity.ok().body(courseDtos.map(mapper::entityToDto));
    }

    @Override
    @Transactional
    public ResponseEntity<CourseDto> insert(CourseInput input) throws RuntimeException {
        Long numOfCourse = repository.countAllByNameAndRemovedFalseAndFacilityIdAndFacilityRemovedFalse(
            input.getName(),
            input.getFacilityId()
        );
        if (numOfCourse > 0) {
            throw new RuntimeException("Course is existing !");
        }
        CourseEntity entity = mapper.inputToEntity(input);
        Optional<FacilityEntity> facilityEntity = facilityRepository.findById(input.getFacilityId());
        if (!facilityEntity.isPresent()) {
            throw new RuntimeException("facility is not found !");
        }
        entity.setFacility(facilityEntity.get());
        return ResponseEntity.ok().body(mapper.entityToDto(repository.save(entity)));
    }

    @Override
    @Transactional
    public ResponseEntity<CourseDto> update(CourseInput input, Long id) throws RuntimeException {
        //check if the course exists or not
        Optional<CourseEntity> oldEntity = repository.findById(id);
        if (!oldEntity.isPresent()) {
            throw new RuntimeException("Course is not found !");
        }
        // check course name
        if (!input.getName().equals(oldEntity.get().getName())) {
            //check if the new course exists or not
            Long count = repository.countAllByNameAndRemovedFalseAndFacilityIdAndFacilityRemovedFalse(
                input.getName(),
                input.getFacilityId()
            );
            if (count > 0) {
                throw new RuntimeException("Course is existing !");
            }
        }
        mapper.inputToEntity(input, oldEntity.get());
        Optional<FacilityEntity> facilityEntity = facilityRepository.findById(input.getFacilityId());
        if (!facilityEntity.isPresent()) {
            throw new RuntimeException("Facility is not found !");
        }
        oldEntity.get().setFacility(facilityEntity.get());
        return ResponseEntity.ok().body(mapper.entityToDto(repository.save(oldEntity.get())));
    }

    @Override
    @Transactional
    public ResponseEntity<List<CourseDto>> delete(List<Long> ids) throws RuntimeException {
        List<CourseDto> delCourses = new ArrayList<>();
        for (Long id : ids) {
            Optional<CourseEntity> entity = repository.findById(id);
            if (!entity.isPresent()) {
                throw new RuntimeException("Course is not found !");
            }
            entity.get().setRemoved(true);
            repository.save(entity.get());
            delCourses.add(mapper.entityToDto(entity.get()));
        }
        return ResponseEntity.ok().body(delCourses);
    }
}
