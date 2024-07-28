package com.vetmanagement.business.abstracts;

import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.dto.request.vaccine.VaccineSaveRequest;
import com.vetmanagement.dto.response.VaccineResponse;
import com.vetmanagement.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    Vaccine get(Long id);

    Page<Vaccine> cursor(int page, int pageSize);

    ResultData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest);

    Vaccine update(Vaccine vaccine);

    boolean delete(Long id);

    Page<Vaccine> getVaccinesByAnimalId(Long animalId, int page, int pageSize);

    List<Vaccine> findVaccinesByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
}
