package com.mangotech.edu.service.area;

import com.mangotech.edu.service.area.dto.AreaDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface AreaService {
    ResponseEntity<Page<AreaDto>> findAllBankAccount(Package pageable);
}
