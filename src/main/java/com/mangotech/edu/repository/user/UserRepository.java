package com.mangotech.edu.repository.user;

import com.mangotech.edu.domain.UserEntity;
import com.mangotech.edu.service.user.param.UserParam;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findTopByUsername(String username);

    @Query(
        "select u from UserEntity u where u.removed = false " +
        "and (:#{#userParam.name} is null or u.username like %:#{#userParam.name}%) " +
        "and (:#{#userParam.department} is null or u.department = :#{#userParam.department}) " +
        "and (:#{#userParam.note} is null or u.note like %:#{#userParam.note}%)"
    )
    Page<UserEntity> findAllUser(@Param(value = "userParam") UserParam userParam, Pageable pageable);

    @Query("select u from UserEntity u where u.removed = false and u.id = :id")
    Optional<UserEntity> findById(@Param(value = "id") Long id);

    List<UserEntity> findUserByRemovedIsFalseAndIdIn(Long[] ids);
}
