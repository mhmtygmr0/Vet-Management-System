package com.vetmanagement.core.utilies;

import com.vetmanagement.core.result.Result;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.dto.response.CursorResponse;
import org.springframework.data.domain.Page;

/**
 * A utility class that provides various static methods to create standardized
 * response objects for the application. These methods help in creating
 * consistent and structured responses for API endpoints.
 */
public class ResultHelper {

    /**
     * Creates a ResultData object indicating a successful creation operation.
     *
     * @param data the data to be included in the response
     * @param <T>  the type of the data
     * @return a ResultData object with status set to true and appropriate message and code
     */
    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, Msg.CREATED, "201", data);
    }

    /**
     * Creates a ResultData object indicating a data validation error.
     *
     * @param data the data to be included in the response
     * @param <T>  the type of the data
     * @return a ResultData object with status set to false and appropriate message and code
     */
    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false, Msg.VALIDATE_ERROR, "400", data);
    }

    /**
     * Creates a ResultData object indicating a successful operation.
     *
     * @param data the data to be included in the response
     * @param <T>  the type of the data
     * @return a ResultData object with status set to true and appropriate message and code
     */
    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, Msg.OK, "200", data);
    }

    /**
     * Creates a Result object indicating a successful operation without any data.
     *
     * @return a Result object with status set to true and appropriate message and code
     */
    public static Result ok() {
        return new Result(true, Msg.OK, "200");
    }

    /**
     * Creates a Result object indicating a not found error.
     *
     * @param msg the custom error message
     * @return a Result object with status set to false and appropriate message and code
     */
    public static Result notFoundError(String msg) {
        return new Result(false, msg, "404");
    }

    /**
     * Creates a Result object indicating a generic error.
     *
     * @param msg the custom error message
     * @return a Result object with status set to false and appropriate message and code
     */
    public static Result error(String msg) {
        return new Result(false, msg, "400");
    }

    /**
     * Creates a ResultData object for paginated data (cursor-based pagination).
     *
     * @param pageData the Page object containing the paginated data
     * @param <T>      the type of the data in the page
     * @return a ResultData object with a CursorResponse containing the paginated data
     */
    public static <T> ResultData<CursorResponse<T>> cursor(Page<T> pageData) {
        CursorResponse<T> cursor = new CursorResponse<>();
        cursor.setItems(pageData.getContent());
        cursor.setPageNumber(pageData.getNumber());
        cursor.setPageSize(pageData.getSize());
        cursor.setTotalElements(pageData.getTotalElements());
        return ResultHelper.success(cursor);
    }
}
