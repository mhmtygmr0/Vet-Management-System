package com.vetmanagement.dto.request.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveRequest {
    private LocalDateTime appointmentDateTime;
    private Long animalId;
    private Long doctorId;
}
