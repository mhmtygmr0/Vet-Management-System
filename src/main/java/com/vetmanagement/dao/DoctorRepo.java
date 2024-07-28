package com.vetmanagement.dao;

import com.vetmanagement.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Doctor entities.
 * It extends JpaRepository to provide CRUD operations
 * and additional query methods for Doctor entities.
 */
@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
}
