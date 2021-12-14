package com.mangotech.edu.web.rest.user;

import com.mangotech.edu.service.user.UserService;
import com.mangotech.edu.service.user.dto.UserDto;
import com.mangotech.edu.service.user.input.UserInput;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDto>> findAllUser(@RequestParam(required = false) Map<String, Object> userParam, Pageable page) {
        return userService.findAllUser(userParam, page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long id, @RequestBody UserInput input) {
        return userService.updateUser(id, input);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserInput input) {
        return userService.createUser(input);
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<List<UserDto>> deleteUser(@PathVariable(name = "ids") Long[] ids) {
        return userService.deleteUser(ids);
    }
}
