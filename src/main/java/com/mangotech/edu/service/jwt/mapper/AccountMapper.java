package com.mangotech.edu.service.jwt.mapper;

import com.mangotech.edu.config.DatabaseConfiguration;
import com.mangotech.edu.domain.UserEntity;
import com.mangotech.edu.service.jwt.dto.AccountDto;
import com.mangotech.edu.service.jwt.input.RegisterInput;
import com.mangotech.edu.service.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AccountMapper extends BaseMapper<UserEntity, AccountDto, RegisterInput> {}
