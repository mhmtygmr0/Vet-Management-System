package com.vetmanagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", unique = true, nullable = false)
    private Long id;

    @NotNull(message = "Please do not leave the name field empty !!!")
    @Column(name = "animal_name", nullable = false)
    private String name;

    @NotNull(message = "Please do not leave the species field empty !!!")
    @Column(name = "animal_species", nullable = false)
    private String species;

    @NotNull(message = "Please do not leave the breed field empty !!!")
    @Column(name = "animal_breed", nullable = false)
    private String breed;

    @NotNull(message = "Please do not leave the gender field empty !!!")
    @Enumerated(EnumType.STRING)
    @Column(name = "animal_gender", nullable = false)
    private Gender gender;

    @NotNull(message = "Please do not leave the colour field empty !!!")
    @Column(name = "animal_colour", nullable = false)
    private String colour;

    @NotNull(message = "Please do not leave the dateOfBirth field empty !!!")
    @Temporal(TemporalType.DATE)
    @Column(name = "animal_date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    public enum Gender {
        MALE,
        FEMALE
    }
}
