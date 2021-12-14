package com.mangotech.edu.repository.course;

import com.mangotech.edu.domain.CourseEntity;
import com.mangotech.edu.service.course.param.CourseParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Duong.PI
 */
@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    @Query(
        " SELECT c FROM CourseEntity c WHERE c.removed = false" +
        " AND ( :#{#course.name} IS NULL OR c.name LIKE %:#{#course.name}% )" +
        " AND ( :#{#course.facilityName} IS NULL OR c.facility.name LIKE %:#{#course.facilityName}% )" +
        " AND ( :#{#course.closeDayStart} IS NULL OR c.closeDay >= :#{#course.closeDayStart} )" +
        " AND ( :#{#course.closeDayEnd} IS NULL OR c.closeDay <= :#{#course.closeDayEnd} )" +
        " AND ( :#{#course.openDayStart} IS NULL OR c.openDay >= :#{#course.openDayStart} )" +
        " AND ( :#{#course.openDayEnd} IS NULL OR c.openDay <= :#{#course.openDayEnd} )"
    )
    Page<CourseEntity> find(@Param(value = "course") CourseParam course, Pageable pageable);

    Long countAllByNameAndRemovedFalseAndFacilityIdAndFacilityRemovedFalse(String name, Long facilityId);
}
