package com.vetmanagement.business.abstracts;

import com.vetmanagement.entities.Vaccine;
import org.springframework.data.domain.Page;

public interface IVaccineService {
    Vaccine get(int id);

    Page<Vaccine> cursor(int page, int pageSize);

    Vaccine save(Vaccine vaccine);

    Vaccine update(Vaccine vaccine);

    boolean delete(int id);
}
