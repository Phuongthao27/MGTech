package com.mangotech.edu.service.user;

import com.mangotech.edu.service.user.dto.UserDto;
import com.mangotech.edu.service.user.input.UserInput;
import com.mangotech.edu.service.user.param.UserParam;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Page<UserDto>> findAllUser(Map<String, Object> userParam, Pageable page);

    ResponseEntity<UserDto> updateUser(Long id, UserInput input);

    ResponseEntity<UserDto> createUser(UserInput input);

    ResponseEntity<List<UserDto>> deleteUser(Long[] ids);
}
