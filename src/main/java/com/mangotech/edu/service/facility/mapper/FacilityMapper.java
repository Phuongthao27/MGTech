package com.mangotech.edu.service.facility.mapper;

import com.mangotech.edu.domain.FacilityEntity;
import com.mangotech.edu.service.area.mapper.AreaMapper;
import com.mangotech.edu.service.facility.dto.FacilityDto;
import com.mangotech.edu.service.facility.input.FacilityInput;
import com.mangotech.edu.service.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
// tu dong inject, tu dong check tat ca thuoc tinh
public interface FacilityMapper extends BaseMapper<FacilityEntity, FacilityDto, FacilityInput> {}
