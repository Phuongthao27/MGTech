package com.mangotech.edu.web.rest.course;

import com.mangotech.edu.service.course.CourseService;
import com.mangotech.edu.service.course.dto.CourseDto;
import com.mangotech.edu.service.course.input.CourseInput;
import com.mangotech.edu.service.course.param.CourseParam;
import java.util.List;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Duong.PI
 * API: search,insert,update,delete
 */
@RestController
@RequestMapping("/api/course")
@AllArgsConstructor
public class CourseResource {

    private final CourseService service;

    /**
     * the search
     * <p>
     * param : facilityName: String, name: String, openDayStart: Long
     * param : openDayEnd: Long, closeDayStart: Long, closeDayEnd: Long
     * return : ResponseEntity<Page<CourseDto>>
     */
    @GetMapping
    public ResponseEntity<Page<CourseDto>> search(CourseParam input, Pageable pageable) {
        return service.find(input, pageable);
    }

    /**
     * the insert
     * <p>
     * param : facilityId: Long, facilityName: String, name: String
     * param : openDay: Long, closeDay: Long
     * return : ResponseEntity<Page<CourseDto>>
     */
    @PostMapping
    public ResponseEntity<CourseDto> insert(@Valid @RequestBody CourseInput input) {
        return service.insert(input);
    }

    /**
     * the update
     * <p>
     * the path variable: id: Long
     * param : facilityId: Long, facilityName: String, name: String
     * param : openDay: Long, closeDay: Long
     * return : ResponseEntity<Page<CourseDto>>
     */
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(@PathVariable Long id, @Valid @RequestBody CourseInput input) {
        return service.update(input, id);
    }

    /**
     * the delete
     * <p>
     * request body : ids: List<Long>
     * return : ResponseEntity<List<Long>>
     */
    @DeleteMapping
    public ResponseEntity<List<CourseDto>> delete(@RequestBody List<Long> ids) {
        return service.delete(ids);
    }
}
