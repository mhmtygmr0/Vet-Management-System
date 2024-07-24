package com.vetmanagement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "animal_customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @JsonManagedReference
    @OneToMany(mappedBy = "animal")
    private List<Vaccine> vaccines;

    @JsonManagedReference
    @OneToMany(mappedBy = "animal")
    private List<Appointment> appointments;
}
