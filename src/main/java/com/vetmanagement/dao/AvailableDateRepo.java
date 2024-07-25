package com.vetmanagement.dao;

import com.vetmanagement.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableDateRepo  extends JpaRepository<AvailableDate, Long> {
}
