package com.mangotech.edu.service.facility;

import com.mangotech.edu.domain.AreaEntity;
import com.mangotech.edu.domain.FacilityEntity;
import com.mangotech.edu.repository.area.AreaRepository;
import com.mangotech.edu.repository.falicity.FacilityRepository;
import com.mangotech.edu.service.facility.dto.FacilityDto;
import com.mangotech.edu.service.facility.input.FacilityInput;
import com.mangotech.edu.service.facility.mapper.FacilityMapper;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;
    private final FacilityMapper facilityMapper;
    private final AreaRepository areaRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<FacilityDto>> find(String name, String note, Integer pageSize, Integer pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<FacilityEntity> result = facilityRepository.find(name, note, pageable);
        return ResponseEntity.ok().body(result.map(facilityMapper::entityToDto));
    }

    @Override
    @Transactional
    public ResponseEntity<FacilityDto> save(FacilityInput facilityInput) {
        // Check area's id exists or not
        Optional<AreaEntity> optAreaEntity = areaRepository.findByIdAndRemovedFalse(facilityInput.getAreaId());
        if (optAreaEntity.isEmpty()) {
            throw new RuntimeException("Area does not exist !");
        }

        // Check facility is existing or not
        String facilityName = facilityInput.getName().trim();
        Long numOfFacility = facilityRepository.countAllByNameIgnoreCaseAndRemovedFalse(facilityName);
        if (numOfFacility > 0) {
            throw new RuntimeException("Facility is existing !");
        }

        // Save facility to database
        FacilityEntity facilityEntity = facilityMapper.inputToEntity(facilityInput);
        AreaEntity areaEntity = optAreaEntity.get();
        facilityEntity.setArea(areaEntity);
        facilityRepository.save(facilityEntity);

        // Convert facilityEntity to facilityDto and return
        return ResponseEntity.ok().body(facilityMapper.entityToDto(facilityEntity));
    }

    @Override
    @Transactional
    public ResponseEntity<FacilityDto> update(FacilityInput facilityInput, Long facilityId) {
        // Check area exists or not by id
        Optional<AreaEntity> optAreaEntity = areaRepository.findByIdAndRemovedFalse(facilityInput.getAreaId());
        if (optAreaEntity.isEmpty()) {
            throw new RuntimeException("Area does not exist !");
        }

        // Check facility exists or not by id
        Optional<FacilityEntity> optFacilityEntityById = facilityRepository.findOneById(facilityId);
        if (optFacilityEntityById.isEmpty()) {
            throw new RuntimeException("Facility is not existed !");
        }

        FacilityEntity facilityEntityById = optFacilityEntityById.get();
        if (!facilityInput.getName().equals(facilityEntityById.getName())) {
            // Check if another facility is using updated name or not
            Long numOfFacility = facilityRepository.countAllByNameIgnoreCaseAndRemovedFalse(facilityInput.getName());
            if (numOfFacility > 0) {
                throw new RuntimeException("Facility is existing !");
            }
        }

        // update facility
        facilityMapper.inputToEntity(facilityInput, facilityEntityById);
        AreaEntity areaEntity = optAreaEntity.get();
        facilityEntityById.setArea(areaEntity);
        facilityRepository.save(facilityEntityById);

        // Convert facilityEntity to facilityDto and return
        return ResponseEntity.ok().body(facilityMapper.entityToDto(facilityEntityById));
    }

    @Override
    @Transactional
    public ResponseEntity<FacilityDto> delete(Long facilityId) {
        Optional<FacilityEntity> optFacilityEntityById = facilityRepository.findOneById(facilityId);
        if (optFacilityEntityById.isEmpty()) {
            throw new RuntimeException("Facility is not existed !");
        }

        // Change facility's removed attribute to true
        FacilityEntity facilityEntity = optFacilityEntityById.get();
        facilityEntity.setRemoved(true);
        facilityRepository.save(facilityEntity);

        // Convert facilityEntity to facilityDto and return
        return ResponseEntity.ok().body(facilityMapper.entityToDto(facilityEntity));
    }
}
