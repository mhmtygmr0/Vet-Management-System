package com.vetmanagement.business.concretes;

import com.vetmanagement.business.abstracts.IAppointmentService;
import com.vetmanagement.core.exception.NotFoundException;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.core.utilies.Msg;
import com.vetmanagement.core.utilies.ResultHelper;
import com.vetmanagement.dao.AppointmentRepo;
import com.vetmanagement.dto.converter.AppointmentConverter;
import com.vetmanagement.dto.request.appointment.AppointmentSaveRequest;
import com.vetmanagement.dto.response.AppointmentResponse;
import com.vetmanagement.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentConverter converterAppointment;
    private final AppointmentRepo appointmentRepo;
    private final AnimalManager animalManager;
    private final DoctorManager doctorManager;
    private final AvailableDateManager availableDateManager;

    public AppointmentManager(AppointmentConverter converterAppointment, AppointmentRepo appointmentRepo, AnimalManager animalManager, DoctorManager doctorManager, AvailableDateManager availableDateManager) {
        this.converterAppointment = converterAppointment;
        this.appointmentRepo = appointmentRepo;
        this.animalManager = animalManager;
        this.doctorManager = doctorManager;
        this.availableDateManager = availableDateManager;
    }

    @Override
    public Appointment get(Long id) {
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.appointmentRepo.findAll(pageable);
    }

    @Override
    public ResultData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest) {
        this.animalManager.get(appointmentSaveRequest.getAnimalId());
        this.doctorManager.get(appointmentSaveRequest.getDoctorId());
        LocalDate availableDate = appointmentSaveRequest.getAppointmentDateTime().toLocalDate();
        this.availableDateManager.availableDoctor(appointmentSaveRequest.getDoctorId(), availableDate);

        this.appointmentExists(appointmentSaveRequest.getDoctorId(), appointmentSaveRequest.getAppointmentDateTime());

        Appointment saveAppointment = this.converterAppointment.saveAppointment(appointmentSaveRequest);
        this.appointmentRepo.save(saveAppointment);
        return ResultHelper.created(this.converterAppointment.toAppointmentResponse(saveAppointment));
    }

    void appointmentExists(Long doctorId, LocalDateTime appointmentDateTime) {
        LocalDateTime startDateTime = appointmentDateTime.withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endDateTime = startDateTime.plusHours(1); // Appointment duration assumed to be 1 hour

        boolean doctorAvailable = this.appointmentRepo
                .existsByDoctorIdAndAppointmentDateTimeBetween(doctorId, startDateTime, endDateTime);

        if (doctorAvailable) {
            throw new IllegalArgumentException("The doctor already has an appointment within the selected time range.");
        }
    }

    @Override
    public Appointment update(Appointment appointment) {
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public boolean delete(Long id) {
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return true;
    }
}
