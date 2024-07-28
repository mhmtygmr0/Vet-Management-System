package com.vetmanagement.api;

import com.vetmanagement.business.abstracts.IAnimalService;
import com.vetmanagement.business.abstracts.ICustomerService;
import com.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.vetmanagement.core.result.Result;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.core.utilies.ResultHelper;
import com.vetmanagement.dto.request.animal.AnimalSaveRequest;
import com.vetmanagement.dto.request.animal.AnimalUpdateRequest;
import com.vetmanagement.dto.response.AnimalResponse;
import com.vetmanagement.dto.response.CursorResponse;
import com.vetmanagement.entities.Animal;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;
    private final ICustomerService customerService;

    public AnimalController(IAnimalService animalService, IModelMapperService modelMapper, ICustomerService customerService) {
        this.animalService = animalService;
        this.modelMapper = modelMapper;
        this.customerService = customerService;
    }

    /**
     * Get animal by ID
     * @param id Animal ID
     * @return AnimalResponse containing animal details
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    /**
     * Get a paginated list of animals
     * @param page Page number
     * @param pageSize Number of items per page
     * @return Paginated list of AnimalResponse
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Animal> animalPage = this.animalService.cursor(page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage.map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
        return ResultHelper.cursor(animalResponsePage);
    }

    /**
     * Save a new animal
     * @param animalSaveRequest Request containing details of the animal to save
     * @return AnimalResponse containing saved animal details
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        return this.animalService.save(animalSaveRequest);
    }

    /**
     * Update an existing animal
     * @param animalUpdateRequest Request containing updated details of the animal
     * @return AnimalResponse containing updated animal details
     */
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        this.animalService.get(animalUpdateRequest.getId());
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        updateAnimal.setCustomer(this.customerService.get(animalUpdateRequest.getCustomerId()));
        this.animalService.update(updateAnimal);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAnimal, AnimalResponse.class));
    }

    /**
     * Delete an animal by ID
     * @param id Animal ID
     * @return Result indicating the status of the operation
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
        return ResultHelper.ok();
    }

    /**
     * Get animals by customer ID
     * @param customerId Customer ID
     * @param page Page number
     * @param pageSize Number of items per page
     * @return Paginated list of AnimalResponse filtered by customer ID
     */
    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> getAnimalsByCustomerId(
            @PathVariable(name = "id") Long customerId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {
        Page<Animal> animalPage = animalService.getAnimalsByCustomerId(customerId, page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage.map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class));
        return ResultHelper.cursor(animalResponsePage);
    }

    /**
     * Get animals by animal name
     * @param name Animal name
     * @param page Page number
     * @param pageSize Number of items per page
     * @return Paginated list of AnimalResponse filtered by animal name
     */
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> getAnimalsByAnimalName(
            @PathVariable(name = "name") String name,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {
        Page<Animal> animalPage = animalService.getAnimalByAnimalName(name, page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage.map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class));
        return ResultHelper.cursor(animalResponsePage);
    }
}
