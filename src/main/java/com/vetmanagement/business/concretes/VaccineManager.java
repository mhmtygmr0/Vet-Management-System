package com.vetmanagement.business.concretes;

import com.vetmanagement.business.abstracts.IVaccineService;
import com.vetmanagement.core.exception.NotFoundException;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.core.utilies.Msg;
import com.vetmanagement.core.utilies.ResultHelper;
import com.vetmanagement.dao.VaccineRepo;
import com.vetmanagement.dto.converter.VaccineConverter;
import com.vetmanagement.dto.request.vaccine.VaccineSaveRequest;
import com.vetmanagement.dto.response.VaccineResponse;
import com.vetmanagement.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccineManager implements IVaccineService {

    private final VaccineRepo vaccineRepo;
    private final AnimalManager animalManager;
    private final VaccineConverter vaccineConverter;

    public VaccineManager(VaccineRepo vaccineRepo, AnimalManager animalManager, VaccineConverter vaccineConverter) {
        this.vaccineRepo = vaccineRepo;
        this.animalManager = animalManager;
        this.vaccineConverter = vaccineConverter;
    }

    @Override
    public Vaccine get(Long id) {
        return this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.vaccineRepo.findAll(pageable);
    }

    @Override
    public ResultData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest) {
        this.animalManager.get(vaccineSaveRequest.getAnimalId());

        if (vaccineSaveRequest.getProtectionFinishDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The protection finish date is in the past. Cannot register vaccine.");
        }

        this.validateExistingVaccines(vaccineSaveRequest);

        Vaccine saveVaccine = this.vaccineConverter.convertToVaccine(vaccineSaveRequest);
        this.vaccineRepo.save(saveVaccine);

        return ResultHelper.created(this.vaccineConverter.toVaccineResponse(saveVaccine));
    }

    private void validateExistingVaccines(VaccineSaveRequest vaccineSaveRequest) {
        List<Vaccine> existingVaccines = vaccineRepo.findByNameAndCodeAndAnimalId(
                vaccineSaveRequest.getName(),
                vaccineSaveRequest.getCode(),
                vaccineSaveRequest.getAnimalId()
        );

        if (!vaccineSaveRequest.getProtectionStartDate().isBefore(vaccineSaveRequest.getProtectionFinishDate())) {
            throw new IllegalArgumentException("The protection start date must be before the protection finish date.");
        }

        for (Vaccine existingVaccine : existingVaccines) {
            if (!existingVaccine.getProtectionFinishDate().isBefore(vaccineSaveRequest.getProtectionFinishDate())) {
                throw new IllegalArgumentException("A vaccine with the same name, code, and animal ID already exists" +
                        " with a protection finish date in the future or overlapping the new vaccine's dates.");
            }
        }
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public boolean delete(Long id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    @Override
    public Page<Vaccine> getVaccinesByAnimalId(Long animalId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.vaccineRepo.findByAnimalId(animalId, pageable);
    }

    public List<Vaccine> findVaccinesByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate) {
        return vaccineRepo.findByProtectionFinishDateBetween(startDate, endDate);
    }
}
