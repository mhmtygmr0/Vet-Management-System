package com.vetmanagement.business.abstracts;

import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.dto.request.appointment.AppointmentSaveRequest;
import com.vetmanagement.dto.response.AppointmentResponse;
import com.vetmanagement.entities.Appointment;
import org.springframework.data.domain.Page;

public interface IAppointmentService {
    Appointment get(Long id);

    Page<Appointment> cursor(int page, int pageSize);

    ResultData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest);

    Appointment update(Appointment appointment);

    boolean delete(Long id);
}
