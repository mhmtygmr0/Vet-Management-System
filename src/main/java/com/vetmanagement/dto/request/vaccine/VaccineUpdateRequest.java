package com.vetmanagement.dto.request.vaccine;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineUpdateRequest {
    @NotNull(message = "Please do not leave the id field empty !!!")
    @Positive(message = "Vaccine id must be positive.")
    private Long id;

    @NotNull(message = "Please do not leave the name field empty !!!")
    private String name;

    @NotNull(message = "Please do not leave the code field empty !!!")
    private String code;

    @NotNull(message = "Please do not leave the protectionStartDate field empty !!!")
    private LocalDate protectionStartDate;

    @NotNull(message = "Please do not leave the protectionFinishDate field empty !!!")
    private LocalDate protectionFinishDate;

    @NotNull(message = "Please do not leave the animalID field empty !!!")
    @Positive(message = "Animal id must be positive.")
    private Long animalId;
}
