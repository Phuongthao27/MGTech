package com.mangotech.edu.repository.area;

import com.mangotech.edu.domain.AreaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntity, Integer> {
    Optional<AreaEntity> findByIdAndRemovedFalse(Integer id);
}
