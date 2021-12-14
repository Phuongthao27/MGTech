package com.mangotech.edu.service.bankaccount;

import com.mangotech.edu.service.bankaccount.dto.BankAccountDto;
import com.mangotech.edu.service.bankaccount.input.BankAccountInput;
import com.mangotech.edu.service.bankaccount.param.BankAccountParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankAccountService {

    ResponseEntity<Page<BankAccountDto>> findAllBankAccount(BankAccountParam param, Pageable pageable);

    ResponseEntity<BankAccountDto> createBankAccount(BankAccountInput input);

    ResponseEntity<BankAccountDto> updateBankAccount(Long id, BankAccountInput input);

    ResponseEntity<List<BankAccountDto>> deleteBankAccount(Long[] ids);
}
