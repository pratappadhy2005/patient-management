package au.com.pratap.exception;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {

        final Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        // Log the exception message (optional)
        log.error("Email already exists: {}", ex.getMessage());

        // Create a map to hold the error message
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Email already exists. Please use a different email.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errors); // 409 Conflict status code
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePatientNotFoundException(PatientNotFoundException ex) {
        // Log the exception message (optional)
        log.error("Patient not found: {}", ex.getMessage());

        // Create a map to hold the error message
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Patient not found. Please check the ID and try again.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors); // 404 Not Found status code
    }
}
