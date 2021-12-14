package com.mangotech.edu.service.customer;

import com.mangotech.edu.domain.CustomerDetailEntity;
import com.mangotech.edu.domain.CustomerEntity;
import com.mangotech.edu.repository.customer.CustomerDetailRepository;
import com.mangotech.edu.repository.customer.CustomerRepository;
import com.mangotech.edu.service.customer.dto.CustomerDto;
import com.mangotech.edu.service.customer.input.CustomerInput;
import com.mangotech.edu.service.customer.mapper.CustomerMapper;
import com.mangotech.edu.service.customer.param.CustomerParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerDetailRepository customerDetailRepository;
    private final CustomerMapper mapper;


    @Override
    public ResponseEntity<Page<CustomerDto>> search(CustomerParam param, Pageable pageable) {
        return ResponseEntity.ok().body(repository.search(param, pageable).map(mapper::entityToDto));
    }

    @Override
    public ResponseEntity<CustomerDto> create(CustomerInput input) {
        CustomerEntity customerEntity = mapper.inputToEntity(input);
        customerEntity = repository.saveAndFlush(customerEntity);
        customerEntity.setCustomerCode("HS" + genCodeCustomer(customerEntity.getId()));
        customerEntity = repository.save(customerEntity);
        return ResponseEntity.ok().body(mapper.entityToDto(customerEntity));
    }

    @Override
    public ResponseEntity<CustomerDto> update(CustomerInput input, Long id) {
        CustomerEntity customerEntity = repository.findById(id).orElseThrow(
            () -> new RuntimeException("Khách hàng không tồn tại")
        );
        mapper.inputToEntity(input, customerEntity);
        customerEntity = repository.save(customerEntity);
        return ResponseEntity.ok().body(mapper.entityToDto(customerEntity));
    }

    @Override
    public ResponseEntity<List<CustomerDto>> delete(List<Long> ids) {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Long id : ids) {
            Optional<CustomerEntity> entity = repository.findById(id);
            if (!entity.isPresent()) {
                throw new RuntimeException("Khách hàng không tồn tại !");
            }
            for (CustomerDetailEntity customerDetail : entity.get().getCustomerDetails()) {
                customerDetail.setRemoved(true);
                customerDetailRepository.save(customerDetail);
            }
            entity.get().setRemoved(true);
            repository.save(entity.get());
            customerDtos.add(mapper.entityToDto(entity.get()));
        }
        return ResponseEntity.ok().body(customerDtos);
    }

    private String genCodeCustomer(Long id) {
        if (id < 10) {
            return "00" + id;
        } else if (id >= 10 && id < 100) {
            return "0" + id;
        } else {
            return Long.toString(id);
        }
    }
}
