package com.mangotech.edu.service.customer.mapper;

import com.mangotech.edu.domain.CustomerEntity;
import com.mangotech.edu.service.customer.dto.CustomerDto;
import com.mangotech.edu.service.customer.input.CustomerInput;
import com.mangotech.edu.service.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CustomerMapper extends BaseMapper<CustomerEntity, CustomerDto, CustomerInput> {}
