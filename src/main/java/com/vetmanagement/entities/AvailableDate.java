package com.vetmanagement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "available_dates")
public class AvailableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_date_id", unique = true)
    private Long id;

    @NotNull(message = "Please do not leave the availableDate field empty !!!")
    @Column(name = "available_date")
    @Temporal(TemporalType.DATE)
    private LocalDate availableDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "available_doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;
}
