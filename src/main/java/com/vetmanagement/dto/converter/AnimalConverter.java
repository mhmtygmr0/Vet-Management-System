package com.vetmanagement.dto.converter;

import com.vetmanagement.dao.CustomerRepo;
import com.vetmanagement.dto.request.animal.AnimalSaveRequest;
import com.vetmanagement.dto.response.AnimalResponse;
import com.vetmanagement.entities.Animal;
import com.vetmanagement.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class AnimalConverter {

    private final CustomerRepo customerRepo;

    public AnimalConverter(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


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
        Customer customer = customerRepo.findById(animalSaveRequest.getCustomerId()).get();
        animal.setCustomer(customer);
        return animal;
    }


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
