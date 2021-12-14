package com.mangotech.edu.web.rest.bankaccount;

import com.mangotech.edu.service.bankaccount.BankAccountService;
import com.mangotech.edu.service.bankaccount.dto.BankAccountDto;
import com.mangotech.edu.service.bankaccount.input.BankAccountInput;
import com.mangotech.edu.service.bankaccount.param.BankAccountParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bank-account")
@AllArgsConstructor
public class BankAccountResource {

    private final BankAccountService bankAccountService;

    @GetMapping
    public ResponseEntity<Page<BankAccountDto>> findBankAccount(BankAccountParam param,
                                                                Pageable pageable){
        return bankAccountService.findAllBankAccount(param, pageable);
    }

    @PostMapping
    public ResponseEntity<BankAccountDto> createBankAccount(@RequestBody @Valid BankAccountInput input){
        return bankAccountService.createBankAccount(input);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountDto> updateBankAccount(@PathVariable(name = "id") Long id,
                                                            @RequestBody @Valid BankAccountInput input){
        return bankAccountService.updateBankAccount(id, input);
    }

    @DeleteMapping
    public ResponseEntity<List<BankAccountDto>> deleteBankAccount(@RequestBody Long[] ids){
        return bankAccountService.deleteBankAccount(ids);
    }
}
