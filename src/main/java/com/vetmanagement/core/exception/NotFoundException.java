package com.vetmanagement.core.exception;

/**
 * Custom exception thrown when a requested resource is not found.
 */
public class NotFoundException extends RuntimeException {
    /**
     * Constructs a new NotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public NotFoundException(String message) {
        super(message);
    }
}
