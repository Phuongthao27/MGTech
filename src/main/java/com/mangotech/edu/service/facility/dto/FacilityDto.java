package com.mangotech.edu.service.facility.dto;

import com.mangotech.edu.service.area.dto.AreaDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacilityDto {

    private Long id;
    private String name;
    private String note;
    private AreaDto area;
}
