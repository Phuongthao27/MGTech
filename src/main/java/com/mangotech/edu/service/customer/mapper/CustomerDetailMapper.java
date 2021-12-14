package com.mangotech.edu.service.customer.mapper;

import com.mangotech.edu.domain.CustomerDetailEntity;
import com.mangotech.edu.service.customer.dto.CustomerDetailDto;
import com.mangotech.edu.service.customer.input.CustomerDetailInput;
import com.mangotech.edu.service.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CustomerDetailMapper extends BaseMapper<CustomerDetailEntity, CustomerDetailDto, CustomerDetailInput> {}
