package com.vetmanagement.dto.converter;

import com.vetmanagement.dao.DoctorRepo;
import com.vetmanagement.dto.request.availableDate.AvailableDateSaveRequest;
import com.vetmanagement.dto.response.AvailableDateResponse;
import com.vetmanagement.entities.AvailableDate;
import com.vetmanagement.entities.Doctor;
import org.springframework.stereotype.Component;

/**
 * Converter class for transforming between AvailableDate entities
 * and DTOs (Data Transfer Objects) such as AvailableDateSaveRequest and AvailableDateResponse.
 */
@Component
public class AvailableDateConverter {

    private final DoctorRepo doctorRepo;

    /**
     * Constructs an AvailableDateConverter with the specified DoctorRepo.
     *
     * @param doctorRepo the repository for accessing doctor data
     */
    public AvailableDateConverter(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    /**
     * Converts an AvailableDateSaveRequest to an AvailableDate entity.
     *
     * @param availableDateSaveRequest the request object containing available date data
     * @return the corresponding AvailableDate entity
     */
    public AvailableDate convertToAvailableDate(AvailableDateSaveRequest availableDateSaveRequest) {
        if (availableDateSaveRequest == null) {
            return null;
        }
        AvailableDate availableDate = new AvailableDate();
        availableDate.setAvailableDate(availableDateSaveRequest.getAvailableDate());
        Doctor doctor = doctorRepo.findById(availableDateSaveRequest.getDoctorId()).orElse(null);
        availableDate.setDoctor(doctor);
        return availableDate;
    }

    /**
     * Converts an AvailableDate entity to an AvailableDateResponse DTO.
     *
     * @param availableDate the AvailableDate entity to convert
     * @return the corresponding AvailableDateResponse DTO
     */
    public AvailableDateResponse toAvailableDateResponse(AvailableDate availableDate) {
        if (availableDate == null) {
            return null;
        }
        AvailableDateResponse response = new AvailableDateResponse();
        response.setId(availableDate.getId());
        response.setAvailableDate(availableDate.getAvailableDate());
        response.setDoctorId(availableDate.getDoctor().getId());
        return response;
    }
}
