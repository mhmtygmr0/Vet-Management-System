package com.vetmanagement.dao;

import com.vetmanagement.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndAppointmentDateTimeBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    Page<Appointment> findByDoctorIdAndAppointmentDateTimeBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);

    Page<Appointment> findByAnimalIdAndAppointmentDateTimeBetween(Long animalId, LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);
}
