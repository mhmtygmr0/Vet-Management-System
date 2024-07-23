package com.vetmanagement.business.abstracts;

import com.vetmanagement.entities.Animal;
import org.springframework.data.domain.Page;

public interface IAnimalService {
    Animal get(int id);

    Page<Animal> cursor(int page, int pageSize);

    Animal save(Animal animal);

    Animal update(Animal animal);

    boolean delete(int id);
}
