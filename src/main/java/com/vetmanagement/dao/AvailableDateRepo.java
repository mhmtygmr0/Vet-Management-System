package com.vetmanagement.dao;

import com.vetmanagement.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Repository interface for managing AvailableDate entities.
 * It extends JpaRepository to provide CRUD operations
 * and additional query methods for AvailableDate entities.
 */
@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {

    /**
     * Finds an AvailableDate entity by doctor ID and available date.
     *
     * @param doctorId      the ID of the doctor
     * @param availableDate the available date
     * @return an Optional containing the AvailableDate entity if found, or empty if not found
     */
    Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);
}
