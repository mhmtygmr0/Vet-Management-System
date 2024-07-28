package com.vetmanagement.core.config;

import com.vetmanagement.core.exception.NotFoundException;
import com.vetmanagement.core.result.Result;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.core.utilies.ResultHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles NotFoundException.
     *
     * @param e the thrown NotFoundException
     * @return ResponseEntity containing error details and HTTP status 404 (Not Found)
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles MethodArgumentNotValidException.
     *
     * @param e the thrown MethodArgumentNotValidException
     * @return ResponseEntity containing validation error messages and HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e) {
        List<String> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles IllegalArgumentException.
     *
     * @param e the thrown IllegalArgumentException
     * @return ResponseEntity containing error details and HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Result> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(ResultHelper.error(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles DateTimeParseException.
     *
     * @param e the thrown DateTimeParseException
     * @return ResponseEntity containing error details about invalid date/time format and HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Result> handleDateTimeParseException(DateTimeParseException e) {
        return new ResponseEntity<>(ResultHelper.error("Invalid date/time format: " + e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles DataIntegrityViolationException.
     *
     * @param e the thrown DataIntegrityViolationException
     * @return ResponseEntity containing error details about data integrity violation and HTTP status 409 (Conflict)
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Result> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String errorMessage = "A data integrity violation occurred: " + e.getMostSpecificCause().getMessage();
        return new ResponseEntity<>(ResultHelper.error(errorMessage), HttpStatus.CONFLICT);
    }

    /**
     * Handles MissingServletRequestParameterException.
     *
     * @param e the thrown MissingServletRequestParameterException
     * @return ResponseEntity containing error details about missing required parameter and HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Result> handleMissingServletRequestParameter(MissingServletRequestParameterException e) {
        String errorMessage = "Required parameter '" + e.getParameterName() + "' missing.";
        return new ResponseEntity<>(ResultHelper.error(errorMessage), HttpStatus.BAD_REQUEST);
    }
}
