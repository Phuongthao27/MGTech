package com.mangotech.edu.web.rest.customer;

import com.mangotech.edu.service.customer.CustomerDetailService;
import com.mangotech.edu.service.customer.dto.CustomerDetailDto;
import com.mangotech.edu.service.customer.input.CustomerDetailInput;
import com.mangotech.edu.service.customer.param.CustomerDetailParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer-detail")
@AllArgsConstructor
public class CustomerDetailResource {

    private final CustomerDetailService customerDetailService;

    /*api danh sách tổng*/
    @GetMapping
    public ResponseEntity<Page<CustomerDetailDto>> searchAll(CustomerDetailParam param, Pageable pageable) {
        return customerDetailService.searchListAll(param, pageable);
    }

    /*api danh sách khách hàng*/
    @GetMapping("/customer")
    public ResponseEntity<Page<CustomerDetailDto>> searchCustomer(CustomerDetailParam param, Pageable pageable) {
        return customerDetailService.searchCustomer(param, pageable);
    }

    /*api danh sách chăm sóc sau*/
    @GetMapping("/customer/take-care-of")
    public ResponseEntity<Page<CustomerDetailDto>> searchCustomerTakeCareAfter(CustomerDetailParam param, Pageable pageable) {
        return customerDetailService.searchCustomerTakeCareAfter(param, pageable);
    }

    /*api danh sách khách hàng đã hẹn*/
    @GetMapping("/customer/appointment")
    public ResponseEntity<Page<CustomerDetailDto>> searchAppointedCustomer(CustomerDetailParam param, Pageable pageable) {
        return customerDetailService.searchAppointedCustomer(param, pageable);
    }

    /*api thêm 1 bản ghi trong màn hình danh sách tổng*/
    @PostMapping
    public ResponseEntity<CustomerDetailDto> create(@Valid @RequestBody CustomerDetailInput input) {
        return customerDetailService.create(input);
    }

    /*api thêm 1 bản ghi trong màn hình danh sách tổng*/
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDetailDto> update(@PathVariable Long id, @Valid @RequestBody CustomerDetailInput input) {
        return customerDetailService.update(input, id);
    }

    @DeleteMapping
    public ResponseEntity<List<CustomerDetailDto>> delete(@RequestBody List<Long> ids) {
        return customerDetailService.delete(ids);
    }

}
