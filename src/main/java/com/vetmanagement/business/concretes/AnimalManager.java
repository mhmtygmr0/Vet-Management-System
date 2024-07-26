package com.vetmanagement.business.concretes;

import com.vetmanagement.business.abstracts.IAnimalService;
import com.vetmanagement.core.exception.NotFoundException;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.core.utilies.Msg;
import com.vetmanagement.core.utilies.ResultHelper;
import com.vetmanagement.dao.AnimalRepo;
import com.vetmanagement.dto.converter.AnimalConverter;
import com.vetmanagement.dto.request.animal.AnimalSaveRequest;
import com.vetmanagement.dto.response.AnimalResponse;
import com.vetmanagement.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepo animalRepo;
    private final CustomerManager customerManager;
    private final AnimalConverter converter;

    public AnimalManager(AnimalRepo animalRepo, CustomerManager customerManager, AnimalConverter animalConverter) {
        this.animalRepo = animalRepo;
        this.customerManager = customerManager;
        this.converter = animalConverter;
    }

    @Override
    public Animal get(Long id) {
        return this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.animalRepo.findAll(pageable);
    }

    @Override
    public ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest) {
        this.customerManager.get(animalSaveRequest.getCustomerId());
        Animal saveAnimal = this.converter.convertToAnimal(animalSaveRequest);
        this.animalRepo.save(saveAnimal);
        return ResultHelper.created(this.converter.toAnimalResponse(saveAnimal));
    }

    @Override
    public Animal update(Animal animal) {
        return this.animalRepo.save(animal);
    }

    @Override
    public boolean delete(Long id) {
        Animal animal = this.get(id);
        this.animalRepo.delete(animal);
        return true;
    }

    @Override
    public Page<Animal> getAnimalsByCustomerId(Long customerId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.animalRepo.findByCustomerId(customerId, pageable);
    }

    @Override
    public Page<Animal> getAnimalByAnimalName(String name, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.animalRepo.findByName(name, pageable);
    }
}
