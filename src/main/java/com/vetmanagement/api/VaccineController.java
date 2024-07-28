package com.vetmanagement.api;

import com.vetmanagement.business.abstracts.IAnimalService;
import com.vetmanagement.business.abstracts.IVaccineService;
import com.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.vetmanagement.core.result.Result;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.core.utilies.ResultHelper;
import com.vetmanagement.dto.request.vaccine.VaccineSaveRequest;
import com.vetmanagement.dto.request.vaccine.VaccineUpdateRequest;
import com.vetmanagement.dto.response.CursorResponse;
import com.vetmanagement.dto.response.VaccineResponse;
import com.vetmanagement.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {
    private final IVaccineService vaccineService;
    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;

    public VaccineController(IVaccineService vaccineService, IAnimalService animalService, IModelMapperService modelMapper) {
        this.vaccineService = vaccineService;
        this.animalService = animalService;
        this.modelMapper = modelMapper;
    }

    /**
     * Get vaccine by ID
     *
     * @param id Vaccine ID
     * @return VaccineResponse containing vaccine details
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get(@PathVariable("id") Long id) {
        Vaccine vaccine = this.vaccineService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
    }

    /**
     * Get a paginated list of vaccines
     *
     * @param page     Page number
     * @param pageSize Number of items per page
     * @return Paginated list of VaccineResponse
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Vaccine> vaccinePage = this.vaccineService.cursor(page, pageSize);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage.map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
        return ResultHelper.cursor(vaccineResponsePage);
    }

    /**
     * Save a new vaccine
     *
     * @param vaccineSaveRequest Request containing details of the vaccine to save
     * @return VaccineResponse containing saved vaccine details
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        return this.vaccineService.save(vaccineSaveRequest);
    }

    /**
     * Update an existing vaccine
     *
     * @param vaccineUpdateRequest Request containing updated details of the vaccine
     * @return VaccineResponse containing updated vaccine details
     */
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        this.vaccineService.get(vaccineUpdateRequest.getId());
        Vaccine updateVaccine = this.modelMapper.forRequest().map(vaccineUpdateRequest, Vaccine.class);
        updateVaccine.setAnimal(this.animalService.get(vaccineUpdateRequest.getAnimalId()));
        this.vaccineService.update(updateVaccine);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateVaccine, VaccineResponse.class));
    }

    /**
     * Delete a vaccine by ID
     *
     * @param id Vaccine ID
     * @return Result indicating the status of the operation
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.vaccineService.delete(id);
        return ResultHelper.ok();
    }

    /**
     * Get a paginated list of vaccines by animal ID
     *
     * @param animalId Animal ID
     * @param page     Page number
     * @param pageSize Number of items per page
     * @return Paginated list of VaccineResponse
     */
    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> getVaccinesByAnimalId(
            @PathVariable(name = "id") Long animalId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {
        Page<Vaccine> vaccinePage = this.vaccineService.getVaccinesByAnimalId(animalId, page, pageSize);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage.map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
        return ResultHelper.cursor(vaccineResponsePage);
    }

    /**
     * Get a list of vaccines by protection finish date range
     *
     * @param startDate Start date of the range
     * @param endDate   End date of the range
     * @return List of vaccines within the specified date range
     */
    @GetMapping("/filter")
    public List<Vaccine> getVaccinesByProtectionFinishDateBetween(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return vaccineService.findVaccinesByProtectionFinishDateBetween(startDate, endDate);
    }

}
