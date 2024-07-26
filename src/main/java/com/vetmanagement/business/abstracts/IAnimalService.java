package com.vetmanagement.business.abstracts;

import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.dto.request.animal.AnimalSaveRequest;
import com.vetmanagement.dto.response.AnimalResponse;
import com.vetmanagement.entities.Animal;
import org.springframework.data.domain.Page;

public interface IAnimalService {
    Animal get(Long id);

    Page<Animal> cursor(int page, int pageSize);

    ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest);

    Animal update(Animal animal);

    boolean delete(Long id);

    Page<Animal> getAnimalsByCustomerId(Long customerId, int page, int pageSize);

    Page<Animal> getAnimalByAnimalName(String name, int page, int pageSize);
}
