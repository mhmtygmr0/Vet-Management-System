package com.vetmanagement.dao;

import com.vetmanagement.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for managing Vaccine entities.
 * It extends JpaRepository to provide CRUD operations
 * and additional query methods for Vaccine entities.
 */
@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long> {

    /**
     * Retrieves a paginated list of vaccines for a specific animal.
     *
     * @param animalId the ID of the animal
     * @param pageable pagination information
     * @return a page of vaccines
     */
    Page<Vaccine> findByAnimalId(Long animalId, Pageable pageable);

    /**
     * Finds vaccines by name, code, and animal ID.
     *
     * @param name     the name of the vaccine
     * @param code     the code of the vaccine
     * @param animalId the ID of the animal
     * @return a list of vaccines matching the specified criteria
     */
    @Query("SELECT v FROM Vaccine v WHERE v.name = :name AND v.code = :code AND v.animal.id = :animalId")
    List<Vaccine> findByNameAndCodeAndAnimalId(@Param("name") String name, @Param("code") String code, @Param("animalId") Long animalId);

    /**
     * Finds vaccines with protection finish dates between the specified start and end dates.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return a list of vaccines with protection finish dates in the specified range
     */
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
}
