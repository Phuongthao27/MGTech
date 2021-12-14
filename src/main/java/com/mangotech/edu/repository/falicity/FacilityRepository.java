package com.mangotech.edu.repository.falicity;

import com.mangotech.edu.domain.FacilityEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends JpaRepository<FacilityEntity, Long> {
    @Query(
        " SELECT f FROM FacilityEntity f WHERE f.removed = false" +
        " AND (:name IS NULL OR f.name LIKE %:name%)" +
        " AND (:note IS NULL OR f.note LIKE %:note%)"
    )
    Page<FacilityEntity> find(@Param("name") String name, @Param("note") String note, Pageable pageable);

    // Tai sao dung Long lai bi warning
    Optional<FacilityEntity> findOneById(Long id);

    List<FacilityEntity> findAllByRemovedIsFalseAndIdIn(List<Long> ids);

    Optional<FacilityEntity> findByIdAndRemovedIsFalse(Long id);

    boolean existsByIdAndBankAccountNotNullAndRemovedIsFalse(Long id);

    Long countAllByNameIgnoreCaseAndRemovedFalse(String name);

    @Query("SELECT f from FacilityEntity f where f.removed = false and f.id = :id")
    FacilityEntity selectFacility(@Param("id") Long id);

    List<FacilityEntity> findByIdInAndRemovedIsFalse(Long [] idsFacility);
}
