package com.mangotech.edu.service.customer;

import com.mangotech.edu.service.customer.dto.CustomerDetailDto;
import com.mangotech.edu.service.customer.input.CustomerDetailInput;
import com.mangotech.edu.service.customer.param.CustomerDetailParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerDetailService {
    ResponseEntity<Page<CustomerDetailDto>> searchListAll(CustomerDetailParam param, Pageable pageable);

    ResponseEntity<Page<CustomerDetailDto>> searchCustomer(CustomerDetailParam param, Pageable pageable);

    ResponseEntity<CustomerDetailDto> create(CustomerDetailInput input);

    ResponseEntity<CustomerDetailDto> update(CustomerDetailInput input, Long id);

    ResponseEntity<Page<CustomerDetailDto>> searchAppointedCustomer(CustomerDetailParam param, Pageable pageable);

    ResponseEntity<List<CustomerDetailDto>> delete(List<Long> ids);

    ResponseEntity<Page<CustomerDetailDto>> searchCustomerTakeCareAfter(CustomerDetailParam param, Pageable pageable);
}
