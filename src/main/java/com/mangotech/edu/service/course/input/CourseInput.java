package com.mangotech.edu.service.course.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Duong.PI
 */
@Getter
@Setter
public class CourseInput {

    @NotNull(message = "facilityId is mandatory")
    private Long facilityId;

    @NotBlank(message = "facilityName is mandatory")
    private String facilityName;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotNull(message = "openDay is mandatory")
    private Long openDay;

    @NotNull(message = "closeDay is mandatory")
    private Long closeDay;

    @NotNull(message = "deposit is mandatory")
    private Double deposit;

    @NotNull(message = "tuition is mandatory")
    private Double tuition;
}
