package com.vetmanagement.dto.request;

import com.vetmanagement.entities.Animal;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalUpdateRequest {
    @Positive
    private int id;

    @NotNull(message = "Please do not leave the name field empty !!!")
    @NotEmpty
    private String name;

    @NotNull(message = "Please do not leave the species field empty !!!")
    @NotEmpty
    private String species;

    @NotNull(message = "Please do not leave the breed field empty !!!")
    @NotEmpty
    private String breed;

    @NotNull(message = "Please do not leave the gender field empty !!!")
    private Animal.Gender gender;

    @NotNull(message = "Please do not leave the colour field empty !!!")
    @NotEmpty
    private String colour;

    @NotNull(message = "Please do not leave the dateOfBirth field empty !!!")
    private LocalDate dateOfBirth;

    @Positive
    @NotNull(message = "Please do not leave the customer field empty !!!")
    private int customerId;
}
