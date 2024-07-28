package com.vetmanagement.core.result;

import lombok.Getter;

/**
 * A generic class for representing the result of an operation
 * that includes additional data.
 *
 * @param <T> the type of the additional data
 */
@Getter
public class ResultData<T> extends Result {
    private T data;

    /**
     * Constructs a new ResultData with the specified status, message, code, and data.
     *
     * @param status  the status of the operation (true if successful, false otherwise)
     * @param message the message associated with the result
     * @param code    the code associated with the result
     * @param data    the additional data associated with the result
     */
    public ResultData(boolean status, String message, String code, T data) {
        super(status, message, code);
        this.data = data;
    }
}
