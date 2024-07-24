package com.vetmanagement.dto.request.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {
    private int id;
    private LocalDate appointmentDate;
    private int animalId;
    private int doctorId;
}
