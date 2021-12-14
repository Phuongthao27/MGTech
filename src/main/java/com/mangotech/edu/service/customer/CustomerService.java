package com.mangotech.edu.service.customer;

import com.mangotech.edu.service.customer.dto.CustomerDto;
import com.mangotech.edu.service.customer.input.CustomerInput;
import com.mangotech.edu.service.customer.param.CustomerParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<Page<CustomerDto>> search(CustomerParam param, Pageable pageable);

    ResponseEntity<CustomerDto> create(CustomerInput input);

    ResponseEntity<CustomerDto> update(CustomerInput input, Long id);

    ResponseEntity<List<CustomerDto>> delete(List<Long> ids);
}
