package vn.sun.membermanagementsystem.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a requested resource is not found
 * HTTP Status: 404 Not Found
 */
public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue),
                HttpStatus.NOT_FOUND.value());
    }

    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("%s not found with id: %d", resourceName, id),
                HttpStatus.NOT_FOUND.value());
    }
}
