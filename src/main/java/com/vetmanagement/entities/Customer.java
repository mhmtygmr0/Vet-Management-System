package com.vetmanagement.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", unique = true, nullable = false)
    private Long id;

    @NotNull(message = "Please do not leave the name field empty !!!")
    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(name = "customer_phone")
    private String phone;

    @NotNull(message = "Please do not leave the email field empty !!!")
    @Email(message = "Please enter a valid email address !!!")
    @Column(name = "customer_mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "customer_address")
    private String address;

    @Column(name = "customer_city")
    private String city;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Animal> animals;
}
