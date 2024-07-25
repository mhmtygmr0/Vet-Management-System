package com.vetmanagement.api;

import com.vetmanagement.business.abstracts.IAnimalService;
import com.vetmanagement.business.abstracts.IAppointmentService;
import com.vetmanagement.business.abstracts.IDoctorService;
import com.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.vetmanagement.core.result.Result;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.core.utilies.ResultHelper;
import com.vetmanagement.dto.request.appointment.AppointmentSaveRequest;
import com.vetmanagement.dto.request.appointment.AppointmentUpdateRequest;
import com.vetmanagement.dto.response.AppointmentResponse;
import com.vetmanagement.dto.response.CursorResponse;
import com.vetmanagement.entities.Animal;
import com.vetmanagement.entities.Appointment;
import com.vetmanagement.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {
    private final IAnimalService animalService;
    private final IAppointmentService appointmentService;
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public AppointmentController(IAnimalService animalService, IAppointmentService appointmentService, IDoctorService doctorService, IModelMapperService modelMapper) {
        this.animalService = animalService;
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") Long id) {
        Appointment appointment = this.appointmentService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
    }

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

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);
        saveAppointment.setId(null); // id alanını sıfırlayarak yeni bir nesne oluşturduğumuzdan emin oluyoruz
        Animal animal = this.animalService.get(appointmentSaveRequest.getAnimalId());
        saveAppointment.setAnimal(animal);
        Doctor doctor = this.doctorService.get(appointmentSaveRequest.getDoctorId());
        saveAppointment.setDoctor(doctor);
        this.appointmentService.save(saveAppointment);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAppointment, AppointmentResponse.class));
    }

    @PutMapping()
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        Appointment updateAppointment = this.modelMapper.forRequest().map(appointmentUpdateRequest, Appointment.class);
        this.appointmentService.update(updateAppointment);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAppointment, AppointmentResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }
}
