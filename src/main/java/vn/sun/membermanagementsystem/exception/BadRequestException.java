package vn.sun.membermanagementsystem.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a bad request is made
 * HTTP Status: 400 Bad Request
 */
public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause, HttpStatus.BAD_REQUEST.value());
    }
}
