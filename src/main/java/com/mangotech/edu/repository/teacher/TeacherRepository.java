package com.mangotech.edu.repository.teacher;

import com.mangotech.edu.domain.TeacherEntity;
import com.mangotech.edu.service.teacher.param.TeacherParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

    @Query("select t from TeacherEntity t " +
        "left join t.facility f left join f.users u " +
        "where t.removed = false  and u.username = :username " +
        "and (:#{#teacher.idFacility} is null or f.id = :#{#teacher.idFacility})" +
        "and (:#{#teacher.gender} is null or t.gender = :#{#teacher.gender})" +
        "and (:#{#teacher.nameTeacher} is null or t.nameTeacher like %:#{#teacher.nameTeacher}%)" +
        "and (:#{#teacher.national} is null or t.national = :#{#teacher.national})" +
        "and (:#{#teacher.dateOfBirth} is null or t.dateOfBirth = :#{#teacher.dateOfBirth})" +
        "and (:#{#teacher.expectedDate} is null or t.expectedDate = :#{#teacher.expectedDate})" +
        "and (:#{#teacher.dayWillCome} is null or t.dayWillCome = :#{#teacher.dayWillCome})" +
        "and (:#{#teacher.expectedDateToGo} is null or t.expectedDateToGo = :#{#teacher.expectedDateToGo})" +
        "and (:#{#teacher.classId} is null or t.classId = :#{#teacher.classId}) " +
        "and (:#{#teacher.status} is null or t.status = :#{#teacher.status}) " +
        "and (:#{#teacher.note} is null or t.note like %:#{#teacher.note}%) order by t.status")
    Page<TeacherEntity> searchFacility(@Param("teacher") TeacherParam teacher,@Param("username") String username, Pageable page);

    @Query("select t from TeacherEntity t where t.removed = false and t.facility.id = :id order by t.status")
    Page<TeacherEntity> findByFacility(@Param("id") Long id, Pageable page);

    @Query("select t from TeacherEntity t " +
        "where t.removed = false  " +
        "and (:#{#teacher.idFacility} is null or t.facility.id = :#{#teacher.idFacility})" +
        "and (:#{#teacher.gender} is null or t.gender = :#{#teacher.gender})" +
        "and (:#{#teacher.nameTeacher} is null or t.nameTeacher like %:#{#teacher.nameTeacher}%)" +
        "and (:#{#teacher.national} is null or t.national = :#{#teacher.national})" +
        "and (:#{#teacher.dateOfBirth} is null or t.dateOfBirth = :#{#teacher.dateOfBirth})" +
        "and (:#{#teacher.expectedDate} is null or t.expectedDate = :#{#teacher.expectedDate})" +
        "and (:#{#teacher.dayWillCome} is null or t.dayWillCome = :#{#teacher.dayWillCome})" +
        "and (:#{#teacher.expectedDateToGo} is null or t.expectedDateToGo = :#{#teacher.expectedDateToGo})" +
        "and (:#{#teacher.classId} is null or t.classId = :#{#teacher.classId}) " +
        "and (:#{#teacher.status} is null or t.status = :#{#teacher.status}) " +
        "and (:#{#teacher.note} is null or t.note like %:#{#teacher.note}%) order by t.status")
    Page<TeacherEntity> search(@Param("teacher") TeacherParam teacher, Pageable page);

    Optional<TeacherEntity> findByIdAndRemovedIsFalse(Long id);
}
