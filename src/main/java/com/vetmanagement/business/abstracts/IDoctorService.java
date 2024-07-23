package com.vetmanagement.business.abstracts;

import com.vetmanagement.entities.Doctor;
import org.springframework.data.domain.Page;

public interface IDoctorService {
    Doctor get(int id);

    Page<Doctor> cursor(int page, int pageSize);

    Doctor save(Doctor doctor);

    Doctor update(Doctor doctor);

    boolean delete(int id);
}
