package com.mangotech.edu.service.facility;

import com.mangotech.edu.service.facility.dto.FacilityDto;
import com.mangotech.edu.service.facility.input.FacilityInput;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface FacilityService {
    ResponseEntity<Page<FacilityDto>> find(String name, String note, Integer pageSize, Integer pageIndex);

    ResponseEntity<FacilityDto> save(FacilityInput facilityInput);

    ResponseEntity<FacilityDto> update(FacilityInput facilityInput, Long facilityId);

    ResponseEntity<FacilityDto> delete(Long facilityId);
}
