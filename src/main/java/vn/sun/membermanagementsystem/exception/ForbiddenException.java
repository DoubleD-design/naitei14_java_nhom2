package vn.sun.membermanagementsystem.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when user doesn't have permission to access a resource
 * HTTP Status: 403 Forbidden
 */
public class ForbiddenException extends BaseException {

    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN.value());
    }

    public ForbiddenException() {
        super("Access denied", HttpStatus.FORBIDDEN.value());
    }
}
