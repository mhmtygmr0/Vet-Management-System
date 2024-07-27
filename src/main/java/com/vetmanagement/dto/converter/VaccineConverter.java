package com.vetmanagement.dto.converter;

import com.vetmanagement.dao.AnimalRepo;
import com.vetmanagement.dto.request.vaccine.VaccineSaveRequest;
import com.vetmanagement.dto.response.VaccineResponse;
import com.vetmanagement.entities.Animal;
import com.vetmanagement.entities.Vaccine;

import org.springframework.stereotype.Component;

@Component
public class VaccineConverter {
    private final AnimalRepo animalRepo;

    public VaccineConverter(AnimalRepo animalRepo) {
        this.animalRepo = animalRepo;
    }


    public Vaccine convertToVaccine(VaccineSaveRequest vaccineSaveRequest) {
        if (vaccineSaveRequest == null) {
            return null;
        }
        Vaccine vaccine = new Vaccine();
        vaccine.setName(vaccineSaveRequest.getName());
        vaccine.setCode(vaccineSaveRequest.getCode());
        vaccine.setProtectionStartDate(vaccineSaveRequest.getProtectionStartDate());
        vaccine.setProtectionFinishDate(vaccineSaveRequest.getProtectionFinishDate());
        Animal animal = this.animalRepo.findById(vaccineSaveRequest.getAnimalId()).get();
        vaccine.setAnimal(animal);
        return vaccine;
    }


    public VaccineResponse toVaccineResponse(Vaccine vaccine) {
        if (vaccine == null) {
            return null;
        }
        VaccineResponse response = new VaccineResponse();
        response.setId(vaccine.getId());
        response.setName(vaccine.getName());
        response.setCode(vaccine.getCode());
        response.setProtectionStartDate(vaccine.getProtectionStartDate());
        response.setProtectionFinishDate(vaccine.getProtectionFinishDate());
        response.setAnimalId(vaccine.getAnimal().getId());
        return response;
    }
}