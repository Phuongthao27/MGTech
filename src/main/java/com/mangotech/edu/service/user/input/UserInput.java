package com.mangotech.edu.service.user.input;

import java.util.List;
import lombok.Data;

@Data
public class UserInput {

    private String username;
    private String email;
    private String password;
    private Long departmentId;
    private List<Long> facilityId;
    private String note;
}
