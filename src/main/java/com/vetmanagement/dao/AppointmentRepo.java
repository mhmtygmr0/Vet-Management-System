package com.vetmanagement.dao;

import com.vetmanagement.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Repository interface for managing Appointment entities.
 * It extends JpaRepository to provide CRUD operations
 * and additional query methods for Appointment entities.
 */
@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    /**
     * Checks if an appointment exists for the given doctor ID within the specified time range.
     *
     * @param doctorId      the ID of the doctor
     * @param startDateTime the start date and time of the appointment
     * @param endDateTime   the end date and time of the appointment
     * @return true if an appointment exists, false otherwise
     */
    boolean existsByDoctorIdAndAppointmentDateTimeBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    /**
     * Finds a page of Appointment entities for the given doctor ID within the specified time range.
     *
     * @param doctorId      the ID of the doctor
     * @param startDateTime the start date and time of the appointments
     * @param endDateTime   the end date and time of the appointments
     * @param pageable      the pagination information
     * @return a page of Appointment entities for the specified doctor ID and time range
     */
    Page<Appointment> findByDoctorIdAndAppointmentDateTimeBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);

    /**
     * Finds a page of Appointment entities for the given animal ID within the specified time range.
     *
     * @param animalId      the ID of the animal
     * @param startDateTime the start date and time of the appointments
     * @param endDateTime   the end date and time of the appointments
     * @param pageable      the pagination information
     * @return a page of Appointment entities for the specified animal ID and time range
     */
    Page<Appointment> findByAnimalIdAndAppointmentDateTimeBetween(Long animalId, LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);
}
