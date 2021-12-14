package com.mangotech.edu.service.mapper;
import com.mangotech.edu.domain.TeacherEntity;
import com.mangotech.edu.service.teacher.dto.TeacherDto;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<Entity, Dto, Input> {
    Entity inputToEntity(Input input);

    Dto entityToDto(Entity entity);

    void inputToEntity(Input input, @MappingTarget Entity entity);

    List<Dto> EntitiesToDtos(List<Entity> entities);
}
