package com.vetmanagement.dto.converter;

import com.vetmanagement.dao.DoctorRepo;
import com.vetmanagement.dto.request.availableDate.AvailableDateSaveRequest;
import com.vetmanagement.dto.response.AvailableDateResponse;
import com.vetmanagement.entities.AvailableDate;
import com.vetmanagement.entities.Doctor;
import org.springframework.stereotype.Component;

@Component
public class AvailableDateConverter {

    private final DoctorRepo doctorRepo;

    public AvailableDateConverter(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }


    public AvailableDate convertToAvailableDate(AvailableDateSaveRequest availableDateSaveRequest) {
        if (availableDateSaveRequest == null) {
            return null;
        }
        AvailableDate availableDate = new AvailableDate();
        availableDate.setAvailableDate(availableDateSaveRequest.getAvailableDate());
        Doctor doctor = doctorRepo.findById(availableDateSaveRequest.getDoctorId()).get();
        availableDate.setDoctor(doctor);
        return availableDate;
    }


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
