package com.mangotech.edu.service.course;

import com.mangotech.edu.service.course.dto.CourseDto;
import com.mangotech.edu.service.course.input.CourseInput;
import com.mangotech.edu.service.course.param.CourseParam;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CourseService {
    ResponseEntity<Page<CourseDto>> find(CourseParam input, Pageable pageable);

    ResponseEntity<CourseDto> insert(CourseInput input) throws RuntimeException;

    ResponseEntity<CourseDto> update(CourseInput input, Long id) throws RuntimeException;

    ResponseEntity<List<CourseDto>> delete(List<Long> ids);
}
