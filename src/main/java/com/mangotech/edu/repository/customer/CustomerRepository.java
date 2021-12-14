package com.mangotech.edu.repository.customer;

import com.mangotech.edu.domain.CustomerEntity;
import com.mangotech.edu.service.customer.param.CustomerParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.removed = false " +
        " AND ( :#{#param.name} IS NULL OR c.name LIKE %:#{#param.name}% ) " +
        " AND ( :#{#param.phone} IS NULL OR c.phone LIKE %:#{#param.phone}% ) " +
        " AND ( :#{#param.faceBook} IS NULL OR c.faceBook LIKE %:#{#param.faceBook}% ) " +
        " AND ( :#{#param.email} IS NULL OR c.email LIKE %:#{#param.email}% ) " +
        " AND ( :#{#param.genderId} IS NULL OR c.genderId = :#{#param.genderId} ) " +
        " AND ( :#{#param.code} IS NULL OR c.customerCode LIKE %:#{#param.code}% ) " +
        " AND ( :#{#param.note} IS NULL OR c.note LIKE %:#{#param.note}% ) "
    )
    Page<CustomerEntity> search(@Param(value = "param") CustomerParam param, Pageable pageable);
}
