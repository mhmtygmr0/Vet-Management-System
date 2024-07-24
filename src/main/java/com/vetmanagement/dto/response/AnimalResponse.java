package com.vetmanagement.dto.response;

import com.vetmanagement.entities.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {
    private int id;
    private String name;
    private String species;
    private String breed;
    private Animal.Gender gender;
    private String colour;
    private LocalDate dateOfBirth;
    private String customerName;
}
