package com.vetmanagement.dto.request.animal;

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
    @Positive(message = "Animal id must be positive.")
    private Long id;

    @NotNull(message = "Please do not leave the name field empty !!!")
    private String name;

    @NotNull(message = "Please do not leave the species field empty !!!")
    private String species;

    @NotNull(message = "Please do not leave the breed field empty !!!")
    private String breed;

    @NotNull(message = "Please do not leave the gender field empty !!!")
    private Animal.Gender gender;

    @NotNull(message = "Please do not leave the colour field empty !!!")
    private String colour;

    @NotNull(message = "Please do not leave the dateOfBirth field empty !!!")
    private LocalDate dateOfBirth;

    @NotNull(message = "Please do not leave the customer field empty !!!")
    @Positive(message = "Customer id must be positive.")
    private Long customerId;
}
