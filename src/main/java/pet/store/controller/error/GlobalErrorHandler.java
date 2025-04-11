package pet.store.controller.error;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Global exception handler for the application.
 * This class provides centralized exception handling across all controllers.
 */
@RestControllerAdvice // Annotation to mark the class as a global exception handler.
@Slf4j // Annotation to enable logging for the class.
public class GlobalErrorHandler {
    /**
     * Handles NoSuchElementException by returning a 404 Not Found response.
     * This method is called when a resource is not found in the database.
     * 
     * @param ex The exception that was thrown
     * @return A map containing the error message
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public Map<String, String> handleNoSuchElementException(NoSuchElementException ex) {
        log.error("NoSuchElementException: {}", ex.toString());
        return Map.of("message", ex.toString()); // Returns a map containing the error message.
    }
}
