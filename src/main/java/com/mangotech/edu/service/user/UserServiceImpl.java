package com.mangotech.edu.service.user;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangotech.edu.repository.department.DepartmentRepository;
import com.mangotech.edu.repository.falicity.FacilityRepository;
import com.mangotech.edu.repository.user.UserRepository;
import com.mangotech.edu.security.PasswordHelper;
import com.mangotech.edu.service.user.dto.UserDto;
import com.mangotech.edu.service.user.input.UserInput;
import com.mangotech.edu.service.user.mapper.UserMapper;
import com.mangotech.edu.service.user.param.UserParam;
import com.mangotech.edu.constants.RemoveConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final PasswordHelper passwordHelper;

    private final FacilityRepository facilityRepository;

    private final UserMapper userMapper;

    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<UserDto>> findAllUser(Map<String, Object> mapUserParam, Pageable page) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        UserParam user = mapper.convertValue(mapUserParam, UserParam.class);
        var resul = userRepository.findAllUser(user, page).map(userMapper::entityToDto);
        return ResponseEntity.ok().body(resul);
    }

    @Override
    @Transactional
    public ResponseEntity<UserDto> updateUser(Long id, UserInput input) {
        var entity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Nh??n vi??n kh??ng t???n t???i!"));
        if (input.getFacilityId() == null) {
            throw new RuntimeException("C?? s??? kh??ng ???????c tr???ng!");
        } else {
            entity.setFacilities(facilityRepository.findAllByRemovedIsFalseAndIdIn(input.getFacilityId()));
        }
        if (input.getDepartmentId() == null) {
            throw new RuntimeException("Ph??ng ban kh??ng ???????c tr???ng!");
        } else {
            entity.setDepartment(
                departmentRepository
                    .findByIdAndRemovedIsFalse(input.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Ph??ng ban kh??ng t???n t???i!"))
            );
        }
        userMapper.inputToEntity(input, entity);
        userRepository.saveAndFlush(entity);
        return ResponseEntity.ok().body(userMapper.entityToDto(entity));
    }

    @Override
    @Transactional
    public ResponseEntity<UserDto> createUser(UserInput input) {
        if (input.getFacilityId() == null) {
            throw new RuntimeException("C?? s??? kh??ng ???????c tr???ng");
        }
        if (input.getDepartmentId() == null) {
            throw new RuntimeException("Ph??ng ban kh??ng ???????c tr???ng");
        }
        var result = userMapper.inputToEntity(input);
        result.setFacilities(facilityRepository.findAllByRemovedIsFalseAndIdIn(input.getFacilityId()));
        if (result.getFacilities().isEmpty()) {
            throw new RuntimeException("C?? s??? kh??ng t???n t???i!");
        }
        result.setDepartment(
            departmentRepository
                .findByIdAndRemovedIsFalse(input.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Ph??ng ban kh??ng t???n t???i!"))
        );
        final var saltPasswordPair = passwordHelper.createPassword(input.getPassword());
        result.setSalt(saltPasswordPair.getLeft());
        result.setPassword(saltPasswordPair.getRight());
        userRepository.saveAndFlush(result);
        return ResponseEntity.ok().body(userMapper.entityToDto(result));
    }

    @Override
    @Transactional
    public ResponseEntity<List<UserDto>> deleteUser(Long[] ids) {
        List<UserDto> result = new ArrayList<>();
        var entity = userRepository.findUserByRemovedIsFalseAndIdIn(ids);
        entity
            .stream()
            .map(
                s -> {
                    s.setRemoved(RemoveConstant.REMOVED);
                    return entity;
                }
            );
        result = entity.stream().map(userMapper::entityToDto).collect(Collectors.toList());
        return ResponseEntity.ok().body(result);
    }
}
