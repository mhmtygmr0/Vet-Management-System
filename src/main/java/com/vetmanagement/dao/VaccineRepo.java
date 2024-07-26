package com.vetmanagement.dao;

import com.vetmanagement.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long> {
    Page<Vaccine> findByAnimalId(Long animalId, Pageable pageable);

    @Query("SELECT v FROM Vaccine v WHERE v.name = :name AND v.code = :code AND v.animal.id = :animalId")
    List<Vaccine> findByNameAndCodeAndAnimalId(@Param("name") String name, @Param("code") String code, @Param("animalId") Long animalId);
}
