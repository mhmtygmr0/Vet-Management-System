package com.vetmanagement.business.abstracts;

import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.dto.request.availableDate.AvailableDateSaveRequest;
import com.vetmanagement.dto.response.AvailableDateResponse;
import com.vetmanagement.entities.AvailableDate;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {
    AvailableDate get(Long id);

    Page<AvailableDate> cursor(int page, int pageSize);

    ResultData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest);

    AvailableDate update(AvailableDate availableDate);

    boolean delete(Long id);
}
