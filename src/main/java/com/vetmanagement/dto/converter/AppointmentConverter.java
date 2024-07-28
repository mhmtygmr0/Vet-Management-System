package com.vetmanagement.dto.converter;

import com.vetmanagement.dao.AnimalRepo;
import com.vetmanagement.dao.DoctorRepo;
import com.vetmanagement.dto.request.appointment.AppointmentSaveRequest;
import com.vetmanagement.dto.response.AppointmentResponse;
import com.vetmanagement.entities.Animal;
import com.vetmanagement.entities.Appointment;
import com.vetmanagement.entities.Doctor;
import org.springframework.stereotype.Component;

/**
 * Converter class for transforming between Appointment entities
 * and DTOs (Data Transfer Objects) such as AppointmentSaveRequest and AppointmentResponse.
 */
@Component
public class AppointmentConverter {
    private final AnimalRepo animalRepo;
    private final DoctorRepo doctorRepo;

    /**
     * Constructs an AppointmentConverter with the specified AnimalRepo and DoctorRepo.
     *
     * @param animalRepo the repository for accessing animal data
     * @param doctorRepo the repository for accessing doctor data
     */
    public AppointmentConverter(AnimalRepo animalRepo, DoctorRepo doctorRepo) {
        this.animalRepo = animalRepo;
        this.doctorRepo = doctorRepo;
    }

    /**
     * Converts an AppointmentSaveRequest to an Appointment entity.
     *
     * @param appointmentSaveRequest the request object containing appointment data
     * @return the corresponding Appointment entity
     */
    public Appointment saveAppointment(AppointmentSaveRequest appointmentSaveRequest) {
        if (appointmentSaveRequest == null) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setAppointmentDateTime(appointmentSaveRequest.getAppointmentDateTime());
        Animal animal = animalRepo.findById(appointmentSaveRequest.getAnimalId()).orElse(null);
        appointment.setAnimal(animal);
        Doctor doctor = doctorRepo.findById(appointmentSaveRequest.getDoctorId()).orElse(null);
        appointment.setDoctor(doctor);
        return appointment;
    }

    /**
     * Converts an Appointment entity to an AppointmentResponse DTO.
     *
     * @param appointment the Appointment entity to convert
     * @return the corresponding AppointmentResponse DTO
     */
    public AppointmentResponse toAppointmentResponse(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        AppointmentResponse response = new AppointmentResponse();
        response.setId(appointment.getId());
        response.setAppointmentDateTime(appointment.getAppointmentDateTime());
        response.setAnimalId(appointment.getAnimal().getId());
        response.setDoctorId(appointment.getDoctor().getId());
        return response;
    }
}
