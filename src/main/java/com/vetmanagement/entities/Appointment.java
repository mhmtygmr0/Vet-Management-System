package com.vetmanagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id", unique = true, nullable = false)
    private Long id;

    @NotNull(message = "Please do not leave the appointmentDate field empty !!!")
    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;
}
