package vn.sun.membermanagementsystem.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when trying to create a resource that already exists
 * HTTP Status: 409 Conflict
 */
public class DuplicateResourceException extends BaseException {

    public DuplicateResourceException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }

    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exists with %s: '%s'", resourceName, fieldName, fieldValue),
                HttpStatus.CONFLICT.value());
    }
}
