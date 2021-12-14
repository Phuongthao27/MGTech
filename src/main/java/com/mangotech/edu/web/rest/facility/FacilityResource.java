package com.mangotech.edu.web.rest.facility;

import com.mangotech.edu.service.facility.FacilityService;
import com.mangotech.edu.service.facility.dto.FacilityDto;
import com.mangotech.edu.service.facility.input.FacilityInput;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facility")
@AllArgsConstructor
public class FacilityResource {

    private final FacilityService facilityService;

    @GetMapping
    public ResponseEntity<Page<FacilityDto>> find(
        @RequestParam(name = "name", required = false) String name,
        @RequestParam(name = "note", required = false) String note,
        @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize,
        @RequestParam(name = "pageIndex", required = false, defaultValue = "0") Integer pageIndex
    ) {
        return facilityService.find(name, note, pageSize, pageIndex);
    }

    @PostMapping
    public ResponseEntity<FacilityDto> save(@RequestBody @Valid FacilityInput facilityInput) {
        return facilityService.save(facilityInput);
    }

    @PutMapping("/{facilityId}")
    public ResponseEntity<FacilityDto> update(@RequestBody @Valid FacilityInput facilityInput, @PathVariable Long facilityId) {
        return facilityService.update(facilityInput, facilityId);
    }

    @DeleteMapping("/{facilityId}")
    public ResponseEntity<FacilityDto> deleteById(@PathVariable Long facilityId) {
        return facilityService.delete(facilityId);
    }
}
