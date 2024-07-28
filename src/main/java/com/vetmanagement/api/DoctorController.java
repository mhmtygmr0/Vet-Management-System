package com.vetmanagement.api;

import com.vetmanagement.business.abstracts.IDoctorService;
import com.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.vetmanagement.core.result.Result;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.core.utilies.ResultHelper;
import com.vetmanagement.dto.request.doctor.DoctorSaveRequest;
import com.vetmanagement.dto.request.doctor.DoctorUpdateRequest;
import com.vetmanagement.dto.response.CursorResponse;
import com.vetmanagement.dto.response.DoctorResponse;
import com.vetmanagement.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public DoctorController(IDoctorService doctorService, IModelMapperService modelMapper) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    /**
     * Get doctor by ID
     *
     * @param id Doctor ID
     * @return DoctorResponse containing doctor details
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get(@PathVariable("id") Long id) {
        Doctor doctor = this.doctorService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(doctor, DoctorResponse.class));
    }

    /**
     * Get a paginated list of doctors
     *
     * @param page     Page number
     * @param pageSize Number of items per page
     * @return Paginated list of DoctorResponse
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Doctor> doctorPage = this.doctorService.cursor(page, pageSize);
        Page<DoctorResponse> doctorResponsePage = doctorPage.map(doctor -> this.modelMapper.forResponse().map(doctor, DoctorResponse.class));
        return ResultHelper.cursor(doctorResponsePage);
    }

    /**
     * Save a new doctor
     *
     * @param doctorSaveRequest Request containing details of the doctor to save
     * @return DoctorResponse containing saved doctor details
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        Doctor saveDoctor = this.modelMapper.forRequest().map(doctorSaveRequest, Doctor.class);
        this.doctorService.save(saveDoctor);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveDoctor, DoctorResponse.class));
    }

    /**
     * Update an existing doctor
     *
     * @param doctorUpdateRequest Request containing updated details of the doctor
     * @return DoctorResponse containing updated doctor details
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        this.doctorService.get(doctorUpdateRequest.getId());
        Doctor updateDoctor = this.modelMapper.forRequest().map(doctorUpdateRequest, Doctor.class);
        this.doctorService.save(updateDoctor);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateDoctor, DoctorResponse.class));
    }

    /**
     * Delete a doctor by ID
     *
     * @param id Doctor ID
     * @return Result indicating the status of the operation
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.doctorService.delete(id);
        return ResultHelper.ok();
    }
}
