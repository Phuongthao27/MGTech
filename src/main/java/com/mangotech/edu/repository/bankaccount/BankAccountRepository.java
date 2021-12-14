package com.mangotech.edu.repository.bankaccount;

import com.mangotech.edu.domain.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long>, JpaSpecificationExecutor<BankAccountEntity> {

    Optional<BankAccountEntity> findByIdAndRemovedIsFalse(Long id);

    List<BankAccountEntity> findAllByIdInAndRemovedIsFalse(Long[] ids);

    boolean existsByBankAccountNumberAndRemovedIsFalse(String bankAccountNumber);


}
