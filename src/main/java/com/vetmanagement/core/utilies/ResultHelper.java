package com.vetmanagement.core.utilies;


import com.vetmanagement.core.result.Result;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.dto.response.AnimalResponse;
import com.vetmanagement.dto.response.CursorResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, Msg.CREATED, "201", data);
    }

    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false, Msg.VALIDATE_ERROR, "400", data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, Msg.OK, "200", data);
    }

    public static Result ok(){
        return new Result(true,Msg.OK,"200");
    }

    public static Result notFoundError(String msg) {
        return new Result(false, msg, "404");
    }

    public static <T> ResultData<CursorResponse<T>> cursor(Page<T> pageData) {
        CursorResponse<AnimalResponse> cursor = new CursorResponse<>();
        cursor.setItems((List<AnimalResponse>) pageData.getContent());
        cursor.setPageNumber(pageData.getNumber());
        cursor.setPageSize(pageData.getSize());
        cursor.setTotalElements(pageData.getTotalElements());

        return ResultHelper.success((CursorResponse<T>) cursor);

    }
}