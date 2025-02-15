package com.vetmanagement.dto.request.availableDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateSaveRequest {
    @NotNull(message = "Please do not leave the availableDate field empty !!!")
    private LocalDate availableDate;

    @NotNull(message = "Please do not leave the doctorID field empty !!!")
    @Positive(message = "Doctor id must be positive.")
    private Long doctorId;
}
