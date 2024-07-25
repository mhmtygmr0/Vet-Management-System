package com.vetmanagement.business.abstracts;

import com.vetmanagement.entities.AvailableDate;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {
    AvailableDate get(Long id);

    Page<AvailableDate> cursor(int page, int pageSize);

    AvailableDate save(AvailableDate availableDate);

    AvailableDate update(AvailableDate availableDate);

    boolean delete(Long id);
}
