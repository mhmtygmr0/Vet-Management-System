package com.vetmanagement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id", unique = true, nullable = false)
    private Long id;

    @NotNull(message = "Please do not leave the name field empty !!!")
    @Column(name = "vaccine_name", nullable = false)
    private String name;

    @NotNull(message = "Please do not leave the code field empty !!!")
    @Column(name = "vaccine_code", unique = true, nullable = false)
    private String code;

    @NotNull(message = "Please do not leave the protectionStartDate field empty !!!")
    @Temporal(TemporalType.DATE)
    @Column(name = "vaccine_protection_start_date", nullable = false)
    private LocalDate protectionStartDate;

    @NotNull(message = "Please do not leave the protectionFinishDate field empty !!!")
    @Temporal(TemporalType.DATE)
    @Column(name = "vaccine_protection_finish_date", nullable = false)
    private LocalDate protectionFinishDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "vaccine_animal_id", referencedColumnName = "animal_id")
    private Animal animal;
}
