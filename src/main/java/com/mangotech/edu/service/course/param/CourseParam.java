package com.mangotech.edu.service.course.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseParam {

    private Long facilityId;
    private String facilityName;
    private String name;
    private Long openDayStart;
    private Long openDayEnd;
    private Long closeDayStart;
    private Long closeDayEnd;
}
