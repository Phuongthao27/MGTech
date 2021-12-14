package com.mangotech.edu.service.teacher.mapper;

import com.mangotech.edu.domain.TeacherEntity;
import com.mangotech.edu.service.mapper.BaseMapper;
import com.mangotech.edu.service.teacher.dto.TeacherDto;
import com.mangotech.edu.service.teacher.input.TeacherInput;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TeacherMapper extends BaseMapper<TeacherEntity, TeacherDto, TeacherInput> {




}
