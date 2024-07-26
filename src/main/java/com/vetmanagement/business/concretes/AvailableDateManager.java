package com.vetmanagement.business.concretes;

import com.vetmanagement.business.abstracts.IAvailableDateService;
import com.vetmanagement.core.exception.NotFoundException;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.core.utilies.Msg;
import com.vetmanagement.core.utilies.ResultHelper;
import com.vetmanagement.dao.AvailableDateRepo;
import com.vetmanagement.dto.converter.AvailableDateConverter;
import com.vetmanagement.dto.request.availableDate.AvailableDateSaveRequest;
import com.vetmanagement.dto.response.AvailableDateResponse;
import com.vetmanagement.entities.AvailableDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateConverter converterAvailableDate;
    private final AvailableDateRepo availableDateRepo;
    private final DoctorManager doctorManager;

    public AvailableDateManager(AvailableDateConverter converterAvailableDate, AvailableDateRepo availableDateRepo, DoctorManager doctorManager) {
        this.converterAvailableDate = converterAvailableDate;
        this.availableDateRepo = availableDateRepo;
        this.doctorManager = doctorManager;
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<AvailableDate> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.availableDateRepo.findAll(pageable);
    }

    @Override
    public ResultData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest) {
        this.checkIfAvailableDateExists(availableDateSaveRequest);
        this.doctorManager.get(availableDateSaveRequest.getDoctorId());
        // Save the available date
        AvailableDate saveAvailableDate = this.converterAvailableDate.convertToAvailableDate(availableDateSaveRequest);
        this.availableDateRepo.save(saveAvailableDate);
        return ResultHelper.created(this.converterAvailableDate.toAvailableDateResponse(saveAvailableDate));
    }

    private void checkIfAvailableDateExists(AvailableDateSaveRequest availableDateSaveRequest) {
        // Check if an available date with the same doctor ID and date already exists
        Optional<AvailableDate> existingAvailableDate = availableDateRepo.findByDoctorIdAndAvailableDate(
                availableDateSaveRequest.getDoctorId(), availableDateSaveRequest.getAvailableDate());
        if (existingAvailableDate.isPresent()) {
            throw new IllegalArgumentException("An available date for the selected doctor " +
                    "already exists for the specified date.");
        }
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public boolean delete(Long id) {
        AvailableDate availableDate = this.get(id);
        this.availableDateRepo.delete(availableDate);
        return true;
    }

    public boolean availableDoctor(Long doctorId, LocalDate availableDate) {
        boolean doctorAvailable = this.availableDateRepo
                .findByDoctorIdAndAvailableDate(doctorId, availableDate).isPresent();
        if (!doctorAvailable) {
            throw new IllegalArgumentException("Doctor is not available on the selected date.");
        }
        return true;
    }
}
