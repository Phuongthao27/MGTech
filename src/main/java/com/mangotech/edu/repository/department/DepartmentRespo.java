package com.mangotech.edu.repository.department;

import com.mangotech.edu.domain.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRespo extends JpaRepository<DepartmentEntity,Long> {

}
