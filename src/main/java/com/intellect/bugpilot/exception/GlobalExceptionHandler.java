package com.intellect.bugpilot.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

	    Map<String, Object> body = new HashMap<>();

	    String rawMessage = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
	    String cleanedMessage = extractDuplicateMessage(rawMessage);

	    body.put("status", HttpStatus.CONFLICT.value());
	    body.put("error", "Data Integrity Violation");
	    body.put("message", cleanedMessage);

	    return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}

	private String extractDuplicateMessage(String message) {
	    if (message == null) return null;

	    if (message.contains("for key")) {
	        return message.substring(0, message.indexOf("for key")).trim();
	    }
	    return message;
	}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Validation Error");
        body.put("message", ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toArray());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {

        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException invalidFormatEx) {

            // Check if target type is enum
            if (invalidFormatEx.getTargetType().isEnum()) {
                String invalidValue = String.valueOf(invalidFormatEx.getValue());

                // Collect allowed enum values
                String allowed = Arrays.stream(invalidFormatEx.getTargetType().getEnumConstants())
                        .map(e -> ((Enum<?>) e).name())
                        .collect(Collectors.joining(", "));

                // Build meaningful message
                String message = String.format(
                        "Invalid value '%s' for field '%s'. Allowed values are: %s",
                        invalidValue,
                        invalidFormatEx.getPath().get(0).getFieldName(),
                        allowed
                );

                // Return 400 Bad Request with meaningful message
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("Validation Error", message, HttpStatus.BAD_REQUEST.value()));
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Invalid Request", ex.getMostSpecificCause().getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    static class ErrorResponse {
        private String error;
        private String message;
        private int status;

        public ErrorResponse(String error, String message, int status) {
            this.error = error;
            this.message = message;
            this.status = status;
        }

        public String getError() { return error; }
        public String getMessage() { return message; }
        public int getStatus() { return status; }
    }
}

