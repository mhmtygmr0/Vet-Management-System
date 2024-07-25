package com.vetmanagement.dto.request.vaccine;

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
public class VaccineSaveRequest {
    @NotNull(message = "Please do not leave the name field empty !!!")
    @NotEmpty
    private String name;

    @NotNull(message = "Please do not leave the code field empty !!!")
    @NotEmpty
    private String code;

    @NotNull(message = "Please do not leave the protectionStartDate field empty !!!")
    private LocalDate protectionStartDate;

    @NotNull(message = "Please do not leave the protectionFinishDate field empty !!!")
    private LocalDate protectionFinishDate;

    private Long animalId;
}
