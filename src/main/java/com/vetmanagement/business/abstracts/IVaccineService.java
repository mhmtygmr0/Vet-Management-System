package com.vetmanagement.business.abstracts;

import com.vetmanagement.entities.Vaccine;
import org.springframework.data.domain.Page;

public interface IVaccineService {
    Vaccine get(Long id);

    Page<Vaccine> cursor(int page, int pageSize);

    Vaccine save(Vaccine vaccine);

    Vaccine update(Vaccine vaccine);

    boolean delete(Long id);

    Page<Vaccine> getVaccinesByAnimalId(Long animalId, int page, int pageSize);
}
