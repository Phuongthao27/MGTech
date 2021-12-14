package com.mangotech.edu.service.course.dto;

import com.mangotech.edu.service.facility.dto.FacilityDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {

    private String id;
    private FacilityDto facility;
    private String name;
    private Long openDay;
    private Long closeDay;
}
