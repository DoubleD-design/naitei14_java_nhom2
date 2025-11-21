package vn.sun.membermanagementsystem.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when user is not authenticated
 * HTTP Status: 401 Unauthorized
 */
public class UnauthorizedException extends BaseException {

    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED.value());
    }

    public UnauthorizedException() {
        super("Unauthorized access", HttpStatus.UNAUTHORIZED.value());
    }
}
