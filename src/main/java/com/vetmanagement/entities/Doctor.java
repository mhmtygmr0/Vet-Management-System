package com.vetmanagement.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id", unique = true, nullable = false)
    private Long id;

    @NotNull(message = "Please do not leave the name field empty !!!")
    @Column(name = "doctor_name", nullable = false)
    private String name;

    @Column(name = "doctor_phone")
    private String phone;

    @NotNull(message = "Please do not leave the email field empty !!!")
    @Email(message = "Please enter a valid email address !!!")
    @Column(name = "doctor_mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "doctor_address")
    private String address;

    @Column(name = "doctor_city")
    private String city;

    @JsonManagedReference
    @OneToMany(mappedBy = "doctor")
    private List<AvailableDate> availableDates;

    @JsonManagedReference
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;
}
