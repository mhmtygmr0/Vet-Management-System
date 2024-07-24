package com.vetmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private int id;
    private LocalDate appointmentDate;
    private String animalName;
    private String doctorName;
}
