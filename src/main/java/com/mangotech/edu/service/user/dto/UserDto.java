package com.mangotech.edu.service.user.dto;

import com.mangotech.edu.service.facility.dto.FacilityDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String departmentName;
    private List<FacilityDto> facilities;
    private String note;
}
