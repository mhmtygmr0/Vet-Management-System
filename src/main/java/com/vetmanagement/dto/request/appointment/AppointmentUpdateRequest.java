package com.vetmanagement.dto.request.appointment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {
    @Positive(message = "Appointment id must be positive.")
    private Long id;
    @NotNull(message = "Please do not leave the appointmentDate field empty !!!")
    private LocalDateTime appointmentDateTime;

    @NotNull(message = "Please do not leave the animalId field empty !!!")
    @Positive(message = "Animal id must be positive")
    private Long animalId;

    @NotNull(message = "Please do not leave the doctorId field empty !!!")
    @Positive(message = "Doctor id must be positive.")
    private Long doctorId;
}
