package com.vetmanagement.business.abstracts;

import com.vetmanagement.entities.Appointment;
import org.springframework.data.domain.Page;

public interface IAppointmentService {
    Appointment get(Long id);

    Page<Appointment> cursor(int page, int pageSize);

    Appointment save(Appointment appointment);

    Appointment update(Appointment appointment);

    boolean delete(Long id);
}
