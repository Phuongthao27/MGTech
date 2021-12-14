package com.mangotech.edu.service.course.mapper;

import com.mangotech.edu.domain.CourseEntity;
import com.mangotech.edu.service.course.dto.CourseDto;
import com.mangotech.edu.service.course.input.CourseInput;
import com.mangotech.edu.service.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CourseMapper extends BaseMapper<CourseEntity, CourseDto, CourseInput> {}
