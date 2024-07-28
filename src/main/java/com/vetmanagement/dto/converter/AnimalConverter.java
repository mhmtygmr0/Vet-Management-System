package com.vetmanagement.dto.converter;

import com.vetmanagement.dao.CustomerRepo;
import com.vetmanagement.dto.request.animal.AnimalSaveRequest;
import com.vetmanagement.dto.response.AnimalResponse;
import com.vetmanagement.entities.Animal;
import com.vetmanagement.entities.Customer;
import org.springframework.stereotype.Component;

/**
 * Converter class for transforming between Animal entities
 * and DTOs (Data Transfer Objects) such as AnimalSaveRequest and AnimalResponse.
 */
@Component
public class AnimalConverter {

    private final CustomerRepo customerRepo;

    /**
     * Constructs an AnimalConverter with the specified CustomerRepo.
     *
     * @param customerRepo the repository for accessing customer data
     */
    public AnimalConverter(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    /**
     * Converts an AnimalSaveRequest to an Animal entity.
     *
     * @param animalSaveRequest the request object containing animal data
     * @return the corresponding Animal entity
     */
    public Animal convertToAnimal(AnimalSaveRequest animalSaveRequest) {
        if (animalSaveRequest == null) {
            return null;
        }
        Animal animal = new Animal();
        animal.setName(animalSaveRequest.getName());
        animal.setSpecies(animalSaveRequest.getSpecies());
        animal.setBreed(animalSaveRequest.getBreed());
        animal.setGender(animalSaveRequest.getGender());
        animal.setColour(animalSaveRequest.getColour());
        animal.setDateOfBirth(animalSaveRequest.getDateOfBirth());
        Customer customer = customerRepo.findById(animalSaveRequest.getCustomerId()).orElse(null);
        animal.setCustomer(customer);
        return animal;
    }

    /**
     * Converts an Animal entity to an AnimalResponse DTO.
     *
     * @param animal the Animal entity to convert
     * @return the corresponding AnimalResponse DTO
     */
    public AnimalResponse toAnimalResponse(Animal animal) {
        if (animal == null) {
            return null;
        }
        AnimalResponse response = new AnimalResponse();
        response.setId(animal.getId());
        response.setName(animal.getName());
        response.setSpecies(animal.getSpecies());
        response.setBreed(animal.getBreed());
        response.setGender(animal.getGender());
        response.setColour(animal.getColour());
        response.setDateOfBirth(animal.getDateOfBirth());
        response.setCustomerId(animal.getCustomer().getId());
        return response;
    }
}
