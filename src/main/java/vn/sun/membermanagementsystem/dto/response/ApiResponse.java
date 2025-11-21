package vn.sun.membermanagementsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Standard API Response wrapper for all REST endpoints
 * Format: { status, message, data, errors, timestamp }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    /**
     * HTTP status code (200, 400, 404, 500, etc.)
     */
    private int status;

    /**
     * Response message
     */
    private String message;

    /**
     * Response data (for success responses)
     */
    private T data;

    /**
     * Validation errors map (for validation failures)
     * Example: { "email": "Email is required", "name": "Name must not be blank" }
     */
    private Map<String, String> errors;

    /**
     * Timestamp of the response
     */
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    // ============ Static Factory Methods for Success Responses ============

    /**
     * Success response with data
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .status(200)
                .message("Success")
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Success response with custom message and data
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .status(200)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Success response with only message (no data)
     */
    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .status(200)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Created response (201)
     */
    public static <T> ApiResponse<T> created(T data) {
        return ApiResponse.<T>builder()
                .status(201)
                .message("Created successfully")
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Created response with custom message
     */
    public static <T> ApiResponse<T> created(String message, T data) {
        return ApiResponse.<T>builder()
                .status(201)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // ============ Static Factory Methods for Error Responses ============

    /**
     * Generic error response
     */
    public static <T> ApiResponse<T> error(int status, String message) {
        return ApiResponse.<T>builder()
                .status(status)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Bad request (400) with message
     */
    public static <T> ApiResponse<T> badRequest(String message) {
        return ApiResponse.<T>builder()
                .status(400)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Validation error (400) with field errors
     */
    public static <T> ApiResponse<T> validationError(Map<String, String> errors) {
        return ApiResponse.<T>builder()
                .status(400)
                .message("Validation failed")
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Validation error with custom message
     */
    public static <T> ApiResponse<T> validationError(String message, Map<String, String> errors) {
        return ApiResponse.<T>builder()
                .status(400)
                .message(message)
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Unauthorized (401)
     */
    public static <T> ApiResponse<T> unauthorized(String message) {
        return ApiResponse.<T>builder()
                .status(401)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Forbidden (403)
     */
    public static <T> ApiResponse<T> forbidden(String message) {
        return ApiResponse.<T>builder()
                .status(403)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Not found (404)
     */
    public static <T> ApiResponse<T> notFound(String message) {
        return ApiResponse.<T>builder()
                .status(404)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Conflict (409)
     */
    public static <T> ApiResponse<T> conflict(String message) {
        return ApiResponse.<T>builder()
                .status(409)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Internal server error (500)
     */
    public static <T> ApiResponse<T> internalError(String message) {
        return ApiResponse.<T>builder()
                .status(500)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Internal server error with default message
     */
    public static <T> ApiResponse<T> internalError() {
        return ApiResponse.<T>builder()
                .status(500)
                .message("Internal server error")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
