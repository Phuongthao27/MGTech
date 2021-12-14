package com.mangotech.edu.service.user.param;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
public class UserParam {

    private String name;
    private Long department;
    private String note;
}
