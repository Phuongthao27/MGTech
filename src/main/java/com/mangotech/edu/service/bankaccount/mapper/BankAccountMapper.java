package com.mangotech.edu.service.bankaccount.mapper;

import com.mangotech.edu.domain.BankAccountEntity;
import com.mangotech.edu.service.bankaccount.dto.BankAccountDto;
import com.mangotech.edu.service.bankaccount.input.BankAccountInput;
import com.mangotech.edu.service.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BankAccountMapper extends BaseMapper<BankAccountEntity, BankAccountDto, BankAccountInput> {

    @Override
    @Mapping(source = "facility.name", target = "facilityName")
    BankAccountDto entityToDto(BankAccountEntity bankAccountEntity);
}
