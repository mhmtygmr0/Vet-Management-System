package com.vetmanagement.dto.converter;

import com.vetmanagement.dao.AnimalRepo;
import com.vetmanagement.dto.request.vaccine.VaccineSaveRequest;
import com.vetmanagement.dto.response.VaccineResponse;
import com.vetmanagement.entities.Animal;
import com.vetmanagement.entities.Vaccine;
import org.springframework.stereotype.Component;

/**
 * Converter class for transforming between Vaccine entities
 * and DTOs (Data Transfer Objects) such as VaccineSaveRequest and VaccineResponse.
 */
@Component
public class VaccineConverter {
    private final AnimalRepo animalRepo;

    /**
     * Constructs a VaccineConverter with the specified AnimalRepo.
     *
     * @param animalRepo the repository for accessing animal data
     */
    public VaccineConverter(AnimalRepo animalRepo) {
        this.animalRepo = animalRepo;
    }

    /**
     * Converts a VaccineSaveRequest to a Vaccine entity.
     *
     * @param vaccineSaveRequest the request object containing vaccine data
     * @return the corresponding Vaccine entity
     */
    public Vaccine convertToVaccine(VaccineSaveRequest vaccineSaveRequest) {
        if (vaccineSaveRequest == null) {
            return null;
        }
        Vaccine vaccine = new Vaccine();
        vaccine.setName(vaccineSaveRequest.getName());
        vaccine.setCode(vaccineSaveRequest.getCode());
        vaccine.setProtectionStartDate(vaccineSaveRequest.getProtectionStartDate());
        vaccine.setProtectionFinishDate(vaccineSaveRequest.getProtectionFinishDate());
        Animal animal = this.animalRepo.findById(vaccineSaveRequest.getAnimalId()).orElse(null);
        vaccine.setAnimal(animal);
        return vaccine;
    }

    /**
     * Converts a Vaccine entity to a VaccineResponse DTO.
     *
     * @param vaccine the Vaccine entity to convert
     * @return the corresponding VaccineResponse DTO
     */
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
