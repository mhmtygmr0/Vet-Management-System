package com.vetmanagement.api;

import com.vetmanagement.business.abstracts.IAppointmentService;
import com.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.vetmanagement.core.result.Result;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.core.utilies.ResultHelper;
import com.vetmanagement.dto.request.appointment.AppointmentSaveRequest;
import com.vetmanagement.dto.request.appointment.AppointmentUpdateRequest;
import com.vetmanagement.dto.response.AppointmentResponse;
import com.vetmanagement.dto.response.CursorResponse;
import com.vetmanagement.entities.Appointment;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {
    private final IAppointmentService appointmentService;
    private final IModelMapperService modelMapper;

    public AppointmentController(IAppointmentService appointmentService, IModelMapperService modelMapper) {
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
    }

    /**
     * Get appointment by ID
     *
     * @param id Appointment ID
     * @return AppointmentResponse containing appointment details
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") Long id) {
        Appointment appointment = this.appointmentService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
    }

    /**
     * Get a paginated list of appointments
     *
     * @param page     Page number
     * @param pageSize Number of items per page
     * @return Paginated list of AppointmentResponse
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Appointment> appointmentPage = this.appointmentService.cursor(page, pageSize);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage.map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
        return ResultHelper.cursor(appointmentResponsePage);
    }

    /**
     * Save a new appointment
     *
     * @param appointmentSaveRequest Request containing details of the appointment to save
     * @return AppointmentResponse containing saved appointment details
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        return this.appointmentService.save(appointmentSaveRequest);
    }

    /**
     * Update an existing appointment
     *
     * @param appointmentUpdateRequest Request containing updated details of the appointment
     * @return AppointmentResponse containing updated appointment details
     */
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        this.appointmentService.get(appointmentUpdateRequest.getId());
        Appointment updateAppointment = this.modelMapper.forRequest().map(appointmentUpdateRequest, Appointment.class);
        this.appointmentService.update(updateAppointment);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAppointment, AppointmentResponse.class));
    }

    /**
     * Delete an appointment by ID
     *
     * @param id Appointment ID
     * @return Result indicating the status of the operation
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }

    /**
     * Get appointments by doctor ID and date/time range
     *
     * @param doctorId      Doctor ID
     * @param startDateTime Start of the date/time range
     * @param endDateTime   End of the date/time range
     * @param page          Page number
     * @param pageSize      Number of items per page
     * @return Paginated list of AppointmentResponse filtered by doctor ID and date/time range
     */
    @GetMapping("/filter/doctor")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> getByDoctorAndAppointmentDateTime(
            @RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Appointment> appointmentPage = appointmentService.getByDoctorAndAppointmentDateTime(doctorId, startDateTime, endDateTime, page, pageSize);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage.map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
        return ResultHelper.cursor(appointmentResponsePage);
    }

    /**
     * Get appointments by animal ID and date/time range
     *
     * @param animalId      Animal ID
     * @param startDateTime Start of the date/time range
     * @param endDateTime   End of the date/time range
     * @param page          Page number
     * @param pageSize      Number of items per page
     * @return Paginated list of AppointmentResponse filtered by animal ID and date/time range
     */
    @GetMapping("/filter/animal")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> getByAnimalAndAppointmentDateTime(
            @RequestParam Long animalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Appointment> appointmentPage = appointmentService.getByAnimalAndAppointmentDateTime(animalId, startDateTime, endDateTime, page, pageSize);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage.map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
        return ResultHelper.cursor(appointmentResponsePage);
    }
}
