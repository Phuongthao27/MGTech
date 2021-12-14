package com.mangotech.edu.service.facility.input;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacilityInput {

    @NotBlank(message = "Facility's name is null !")
    private String name;

    private String note;
    private Integer areaId;
}
