package com.mangotech.edu.service.department.mapper;

import com.mangotech.edu.domain.DepartmentEntity;
import com.mangotech.edu.service.department.dto.DepartmentDto;
import com.mangotech.edu.service.department.input.DepartmentInput;
import com.mangotech.edu.service.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DepartmentMapper extends BaseMapper<DepartmentEntity, DepartmentDto, DepartmentInput> {

}
