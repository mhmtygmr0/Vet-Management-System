package com.vetmanagement.api;

import com.vetmanagement.business.abstracts.IAvailableDateService;
import com.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.vetmanagement.core.result.Result;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.core.utilies.ResultHelper;
import com.vetmanagement.dto.request.availableDate.AvailableDateSaveRequest;
import com.vetmanagement.dto.request.availableDate.AvailableDateUpdateRequest;
import com.vetmanagement.dto.response.AvailableDateResponse;
import com.vetmanagement.dto.response.CursorResponse;
import com.vetmanagement.entities.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/availableDates")
public class AvailableDateController {
    private final IAvailableDateService availableDateService;
    private final IModelMapperService modelMapper;

    public AvailableDateController(IAvailableDateService availableDateService, IModelMapperService modelMapper) {
        this.availableDateService = availableDateService;
        this.modelMapper = modelMapper;
    }

    /**
     * Get available date by ID
     *
     * @param id Available date ID
     * @return AvailableDateResponse containing available date details
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get(@PathVariable("id") Long id) {
        AvailableDate availableDate = this.availableDateService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class));
    }

    /**
     * Get a paginated list of available dates
     *
     * @param page     Page number
     * @param pageSize Number of items per page
     * @return Paginated list of AvailableDateResponse
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<AvailableDate> availableDatePage = this.availableDateService.cursor(page, pageSize);
        Page<AvailableDateResponse> availableDateResponsePage = availableDatePage.map(availableDate -> this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class));
        return ResultHelper.cursor(availableDateResponsePage);
    }

    /**
     * Save a new available date
     *
     * @param availableDateSaveRequest Request containing details of the available date to save
     * @return AvailableDateResponse containing saved available date details
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        return this.availableDateService.save(availableDateSaveRequest);
    }

    /**
     * Update an existing available date
     *
     * @param availableDateUpdateRequest Request containing updated details of the available date
     * @return AvailableDateResponse containing updated available date details
     */
    @PutMapping()
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest) {
        this.availableDateService.get(availableDateUpdateRequest.getId());
        AvailableDate updateAvailableDate = this.modelMapper.forRequest().map(availableDateUpdateRequest, AvailableDate.class);
        this.availableDateService.update(updateAvailableDate);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAvailableDate, AvailableDateResponse.class));
    }

    /**
     * Delete an available date by ID
     *
     * @param id Available date ID
     * @return Result indicating the status of the operation
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.availableDateService.delete(id);
        return ResultHelper.ok();
    }
}
