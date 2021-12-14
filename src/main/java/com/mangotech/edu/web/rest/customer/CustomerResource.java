package com.mangotech.edu.web.rest.customer;

import com.mangotech.edu.service.customer.CustomerService;
import com.mangotech.edu.service.customer.dto.CustomerDto;
import com.mangotech.edu.service.customer.input.CustomerInput;
import com.mangotech.edu.service.customer.param.CustomerParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerResource {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<CustomerDto>> search(CustomerParam param, Pageable pageable) {
        return customerService.search(param, pageable);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerInput input) {
        return customerService.create(input);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable(name = "id") Long id, @Valid @RequestBody CustomerInput input) {
        return customerService.update(input, id);
    }

    @DeleteMapping
    public ResponseEntity<List<CustomerDto>> delete(@RequestBody List<Long> ids) {
        return customerService.delete(ids);
    }

}
