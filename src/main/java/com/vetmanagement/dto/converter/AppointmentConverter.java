package com.vetmanagement.dto.converter;

import com.vetmanagement.dao.AnimalRepo;
import com.vetmanagement.dao.DoctorRepo;
import com.vetmanagement.dto.request.appointment.AppointmentSaveRequest;
import com.vetmanagement.dto.response.AppointmentResponse;
import com.vetmanagement.entities.Animal;
import com.vetmanagement.entities.Appointment;
import com.vetmanagement.entities.Doctor;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConverter {
    private final AnimalRepo animalRepo;
    private final DoctorRepo doctorRepo;

    public AppointmentConverter(AnimalRepo animalRepo, DoctorRepo doctorRepo) {
        this.animalRepo = animalRepo;
        this.doctorRepo = doctorRepo;
    }


    public Appointment saveAppointment(AppointmentSaveRequest appointmentSaveRequest) {
        if (appointmentSaveRequest == null) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setAppointmentDateTime(appointmentSaveRequest.getAppointmentDateTime());
        Animal animal = animalRepo.findById(appointmentSaveRequest.getAnimalId()).get();
        appointment.setAnimal(animal);
        Doctor doctor = doctorRepo.findById(appointmentSaveRequest.getDoctorId()).get();
        appointment.setDoctor(doctor);
        return appointment;
    }


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