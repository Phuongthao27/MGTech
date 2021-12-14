package com.mangotech.edu.service.department;

import com.mangotech.edu.service.department.dto.DepartmentDto;
import com.mangotech.edu.service.department.input.DepartmentInput;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    DepartmentDto createDto(DepartmentInput departmentInput);
}
