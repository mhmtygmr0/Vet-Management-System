package com.vetmanagement.business.concretes;

import com.vetmanagement.business.abstracts.IDoctorService;
import com.vetmanagement.core.exception.NotFoundException;
import com.vetmanagement.core.utilies.Msg;
import com.vetmanagement.dao.DoctorRepo;
import com.vetmanagement.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorManager implements IDoctorService {

    private final DoctorRepo doctorRepo;

    public DoctorManager(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    /**
     * Retrieve a doctor by its ID.
     *
     * @param id Doctor ID
     * @return Doctor entity
     */
    @Override
    public Doctor get(Long id) {
        return this.doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    /**
     * Get a paginated list of doctors.
     *
     * @param page     Page number
     * @param pageSize Number of items per page
     * @return Page of Doctor entities
     */
    @Override
    public Page<Doctor> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.doctorRepo.findAll(pageable);
    }

    /**
     * Save a new doctor.
     *
     * @param doctor Doctor entity to save
     * @return Saved Doctor entity
     */
    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepo.save(doctor);
    }

    /**
     * Update an existing doctor.
     *
     * @param doctor Doctor entity to update
     * @return Updated Doctor entity
     */
    @Override
    public Doctor update(Doctor doctor) {
        return this.doctorRepo.save(doctor);
    }

    /**
     * Delete a doctor by its ID.
     *
     * @param id Doctor ID
     * @return true if the deletion is successful
     */
    @Override
    public boolean delete(Long id) {
        Doctor doctor = this.get(id);
        this.doctorRepo.delete(doctor);
        return true;
    }
}
