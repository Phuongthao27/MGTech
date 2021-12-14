package com.mangotech.edu.service.bankaccount;

import com.mangotech.edu.domain.BankAccountEntity;
import com.mangotech.edu.repository.bankaccount.BankAccountRepository;
import com.mangotech.edu.repository.falicity.FacilityRepository;
import com.mangotech.edu.service.bankaccount.dto.BankAccountDto;
import com.mangotech.edu.service.bankaccount.input.BankAccountInput;
import com.mangotech.edu.service.bankaccount.mapper.BankAccountMapper;
import com.mangotech.edu.service.bankaccount.param.BankAccountParam;
import com.mangotech.edu.service.bankaccount.specification.BankAccountSpecification;
import com.mangotech.edu.constants.RemoveConstant;
import com.mangotech.edu.constants.AccountType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    private final BankAccountMapper bankAccountMapper;

    private final FacilityRepository facilityRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<BankAccountDto>> findAllBankAccount(BankAccountParam param, Pageable pageable) {
        Specification<BankAccountEntity> specification = Specification.where(new BankAccountSpecification(param, "LIKE"));
        var result = bankAccountRepository.findAll(specification, pageable);
        return ResponseEntity.ok().body(result.map(bankAccountMapper::entityToDto));
    }

    @Override
    @Transactional
    public ResponseEntity<BankAccountDto> createBankAccount(BankAccountInput input) {
        if (bankAccountRepository.existsByBankAccountNumberAndRemovedIsFalse(input.getBankAccountNumber())) {
            throw new RuntimeException("Tài khoản ngân hàng này đã dùng cho cơ sở khác!");
        }
        var result = bankAccountMapper.inputToEntity(input);
        if (input.getFacilityId() != null) {
            result.setType(AccountType.BASE_ACCOUNT);
            var facility = facilityRepository
                .findByIdAndRemovedIsFalse(input.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Cở sở không tồn tại!"));
            if (facility.getBankAccount() != null) {
                throw new RuntimeException("Cơ sở đã có tài khoản ngân hàng!");
            }
            result.setFacility(facility);
        } else {
            result.setType(AccountType.OTHER_ACCOUNT);
        }
        result = bankAccountRepository.save(result);
        return ResponseEntity
            .ok()
            .body(bankAccountMapper.entityToDto(result));
    }

    @Override
    @Transactional
    public ResponseEntity<BankAccountDto> updateBankAccount(Long id, BankAccountInput input) {
        var entity = bankAccountRepository
            .findByIdAndRemovedIsFalse(id)
            .orElseThrow(() -> new RuntimeException("Tài khoản công ty không tồn tại!"));
        if (!entity.getBankAccountNumber().equals(input.getBankAccountNumber())) {
            if (bankAccountRepository.existsByBankAccountNumberAndRemovedIsFalse(input.getBankAccountNumber())) {
                throw new RuntimeException("Tài khoản ngân hàng này đã dùng cho cơ sở khác!");
            }
        }
        if (input.getFacilityId() != null) {
            if (entity.getFacility() == null || !entity.getFacility().getId().equals(input.getFacilityId())) {
                var facility = facilityRepository
                    .findByIdAndRemovedIsFalse(input.getFacilityId())
                    .orElseThrow(() -> new RuntimeException("Cơ sở không tồn tại!"));
                if (facility.getBankAccount() != null) {
                    throw new RuntimeException("Cơ sở đã có tài khoản!");
                }
                entity.setFacility(facility);
                entity.setType(AccountType.BASE_ACCOUNT);
            }
        } else {
            entity.setType(AccountType.OTHER_ACCOUNT);
            entity.setFacility(null);
        }
        bankAccountMapper.inputToEntity(input, entity);
        bankAccountRepository.save(entity);
        return ResponseEntity
            .ok()
            .body(bankAccountMapper.entityToDto(entity));
    }

    @Override
    @Transactional
    public ResponseEntity<List<BankAccountDto>> deleteBankAccount(Long[] ids) {
        var result = bankAccountRepository
            .findAllByIdInAndRemovedIsFalse(ids)
            .stream()
            .peek(b -> {
                b.setRemoved(RemoveConstant.REMOVED);
                bankAccountRepository.save(b);
            })
            .map(bankAccountMapper::entityToDto)
            .collect(Collectors.toList());
        return ResponseEntity
            .ok()
            .body(result);
    }
}
