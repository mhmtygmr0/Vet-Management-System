package com.vetmanagement.core.result;

import lombok.Getter;

/**
 * A generic class for representing the result of an operation.
 */
@Getter
public class Result {
    private boolean status;
    private String message;
    private String code;

    /**
     * Constructs a new Result with the specified status, message, and code.
     *
     * @param status  the status of the operation (true if successful, false otherwise)
     * @param message the message associated with the result
     * @param code    the code associated with the result
     */
    public Result(boolean status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
