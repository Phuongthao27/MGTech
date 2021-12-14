package com.mangotech.edu.service.user.mapper;

import com.mangotech.edu.config.DatabaseConfiguration;
import com.mangotech.edu.domain.UserEntity;
import com.mangotech.edu.service.facility.mapper.FacilityMapper;
import com.mangotech.edu.service.mapper.BaseMapper;
import com.mangotech.edu.service.user.dto.UserDto;
import com.mangotech.edu.service.user.input.UserInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = { FacilityMapper.class })
public interface UserMapper extends BaseMapper<UserEntity, UserDto, UserInput> {
    @Override
    @Mapping(source = "facilities", target = "facilities")
    @Mapping(source = "department.name", target = "departmentName")
    UserDto entityToDto(UserEntity userEntity);

    @Override
    void inputToEntity(UserInput userInput, @MappingTarget UserEntity userEntity);
}
