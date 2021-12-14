package com.mangotech.edu.service.area.mapper;

import com.mangotech.edu.domain.AreaEntity;
import com.mangotech.edu.service.area.dto.AreaDto;
import com.mangotech.edu.service.area.input.AreaInput;
import com.mangotech.edu.service.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AreaMapper extends BaseMapper<AreaEntity, AreaDto, AreaInput> {}
