package com.mangotech.edu.service.customer;

import com.mangotech.edu.domain.*;
import com.mangotech.edu.repository.course.CourseRepository;
import com.mangotech.edu.repository.customer.CustomerDetailRepository;
import com.mangotech.edu.repository.customer.CustomerRepository;
import com.mangotech.edu.repository.falicity.FacilityRepository;
import com.mangotech.edu.repository.user.UserRepository;
import com.mangotech.edu.service.customer.dto.CustomerDetailDto;
import com.mangotech.edu.service.customer.input.CustomerDetailInput;
import com.mangotech.edu.service.customer.mapper.CustomerDetailMapper;
import com.mangotech.edu.service.customer.mapper.CustomerMapper;
import com.mangotech.edu.service.customer.param.CustomerDetailParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerDetailServiceImpl implements CustomerDetailService {

    private final CustomerDetailRepository customerDetailRepository;
    private final CustomerRepository customerRepository;
    private final CustomerDetailMapper customerDetailMapper;
    private final CustomerMapper customerMapper;
    private final CourseRepository courseRepository;
    private final FacilityRepository facilityRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<CustomerDetailDto>> searchListAll(CustomerDetailParam param, Pageable pageable) {
        return ResponseEntity.ok().body(customerDetailRepository.searchAll(param, pageable).map(customerDetailMapper::entityToDto));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<CustomerDetailDto>> searchCustomer(CustomerDetailParam param, Pageable pageable) {
        return ResponseEntity.ok().body(customerDetailRepository.searchCustomer(param, pageable).map(customerDetailMapper::entityToDto));
    }

    @Override
    @Transactional
    public ResponseEntity<CustomerDetailDto> create(CustomerDetailInput input) {
        CustomerDetailEntity entity = customerDetailMapper.inputToEntity(input);
        checkCustomerInfo(input, entity);
        //kiểm tra thông tin khách hàng
        if (input.getCustomerId() != null) {
            CustomerEntity customerEntity = customerRepository.findById(input.getCustomerId()).orElseThrow(
                () -> new RuntimeException("Khách hàng không tồn tại !")
            );
            customerMapper.inputToEntity(input.getCustomerInput(), customerEntity);
            customerEntity = customerRepository.save(customerEntity);
            entity.setCustomer(customerEntity);
        } else {
            CustomerEntity customerEntity = customerMapper.inputToEntity(input.getCustomerInput());
            customerEntity = customerRepository.saveAndFlush(customerEntity);
            customerEntity.setCustomerCode("HS" + genCodeCustomer(customerEntity.getId()));
            customerEntity = customerRepository.save(customerEntity);
            entity.setCustomer(customerEntity);
        }
        return ResponseEntity.ok().body(customerDetailMapper.entityToDto(customerDetailRepository.save(entity)));
    }

    @Override
    @Transactional
    public ResponseEntity<CustomerDetailDto> update(CustomerDetailInput input, Long id) {
        CustomerDetailEntity entity = customerDetailRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Bản ghi không tồn tại !")
        );
        if (input.getCustomerId() == null || input.getCustomerId() != entity.getCustomer().getId()) {
            throw new RuntimeException("Bản ghi không tồn tại !");
        }
        checkCustomerInfo(input, entity);
        return ResponseEntity.ok().body(customerDetailMapper.entityToDto(customerDetailRepository.save(entity)));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<CustomerDetailDto>> searchAppointedCustomer(CustomerDetailParam param, Pageable pageable) {
        return ResponseEntity.ok().body(customerDetailRepository.searchAppointedCustomer(param, pageable).map(customerDetailMapper::entityToDto));
    }

    @Override
    @Transactional
    public ResponseEntity<List<CustomerDetailDto>> delete(List<Long> ids) {
        List<CustomerDetailDto> delCustomerDetai = new ArrayList<>();
        for (Long id : ids) {
            Optional<CustomerDetailEntity> entity = customerDetailRepository.findById(id);
            if (!entity.isPresent()) {
                throw new RuntimeException("Bản ghi không tồn tại !");
            }
            entity.get().setRemoved(true);
            customerDetailRepository.save(entity.get());
            delCustomerDetai.add(customerDetailMapper.entityToDto(entity.get()));
        }
        return ResponseEntity.ok().body(delCustomerDetai);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<CustomerDetailDto>> searchCustomerTakeCareAfter(CustomerDetailParam param, Pageable pageable) {
        return ResponseEntity.ok().body(customerDetailRepository.searchTakeCareAfter(param, pageable).map(customerDetailMapper::entityToDto));
    }

    private void checkCustomerInfo(CustomerDetailInput input, CustomerDetailEntity entity) {
        // kiểm tra thông tin nhân viên marketing
        UserEntity marketPersion = userRepository.findById(input.getMarkPersionId()).orElseThrow(
            () -> new RuntimeException("Nhân viên marketing không tồn tại !")
        );
        entity.setMarkPerson(marketPersion);

        //kiểm tra thông tin cơ sở
        if (input.getFacilityId() != null) {
            FacilityEntity facility = facilityRepository.findById(input.getFacilityId()).orElseThrow(
                () -> new RuntimeException("Cơ sở không tồn tại !")
            );
            entity.setFacility(facility);
        }

        //kiểm tra khóa học đăng ký
        if (input.getCourseId() != null) {
            CourseEntity course = courseRepository.findById(input.getCourseId()).orElseThrow(
                () -> new RuntimeException("Khóa học không tồn tại !")
            );
            entity.setCourse(course);
            if (input.getTuition1() != null && input.getTuition2() != null && input.getTuition3() != null &&
                ((input.getTuition1() + input.getTuition2() + input.getTuition3()) < course.getTuition())) {
                throw new RuntimeException("Khách hàng chưa nộp đủ học phí !");
            }
            if (input.getDeposit() != null && input.getDeposit() < course.getDeposit()) {
                throw new RuntimeException("Số tiền cọc của khách hàng chưa đủ !");
            }
        }

        // kiểm tra thu tiền cọc
        if (input.getDeposit() != null) {
            if (input.getDepositHolderId() == null) {
                throw new RuntimeException("Nhân viên thu tiền cọc không được để trống !");
            }
            Optional<UserEntity> depositHolder = userRepository.findById(input.getDepositHolderId());
            if (!depositHolder.isPresent()) {
                throw new RuntimeException("Nhân viên thu tiền cọc không tồn tại");
            }
            entity.setDepositHolder(depositHolder.get());
        }

        // kiểm tra thu tiền đợt 1
        if (input.getTuition1() != null) {
            if (input.getTuitionHolderId1() == null) {
                throw new RuntimeException("Nhân viên thu học phí đợt 1 không được để trống !");
            }
            Optional<UserEntity> tuiPersion1 = userRepository.findById(input.getTuitionHolderId1());
            if (!tuiPersion1.isPresent()) {
                throw new RuntimeException("Nhân viên thu tiền đợt 1 không tồn tại");
            }
            entity.setTuitionHolder1(tuiPersion1.get());
        }

        // kiểm tra thu tiền đợt 2
        if (input.getTuition2() != null) {
            if (input.getTuitionHolderId2() == null) {
                throw new RuntimeException("Nhân viên thu học phí đợt 2 không được để trống !");
            }
            Optional<UserEntity> tuiPersion2 = userRepository.findById(input.getTuitionHolderId2());
            if (!tuiPersion2.isPresent()) {
                throw new RuntimeException("Nhân viên thu tiền đợt 2 không tồn tại");
            }
            entity.setTuitionHolder2(tuiPersion2.get());
        }

        // kiểm tra thu tiền đợt 3
        if (input.getTuition3() != null) {
            if (input.getTuitionHolderId3() == null) {
                throw new RuntimeException("Nhân viên thu học phí đợt 3 không được để trống !");
            }
            Optional<UserEntity> tuiPersion3 = userRepository.findById(input.getTuitionHolderId3());
            if (!tuiPersion3.isPresent()) {
                throw new RuntimeException("Nhân viên thu tiền đợt 3 không tồn tại");
            }
            entity.setTuitionHolder3(tuiPersion3.get());
        }


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
