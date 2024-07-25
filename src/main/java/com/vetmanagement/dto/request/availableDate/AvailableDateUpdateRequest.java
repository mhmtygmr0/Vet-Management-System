package com.vetmanagement.dto.request.availableDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateUpdateRequest {
    private Long id;
    private LocalDate availableDate;
    private Long doctorId;
}
