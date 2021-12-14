package com.mangotech.edu.repository.department;

import com.mangotech.edu.domain.DepartmentEntity;
import com.mangotech.edu.domain.FacilityEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    Optional<DepartmentEntity> findTopByRoleAndRemovedFalse(String role);

    Optional<DepartmentEntity> findByIdAndRemovedIsFalse(Long id);

}
