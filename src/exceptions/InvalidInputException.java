package exceptions;

/**
 * Custom exception for invalid input validation errors.
 * This exception is thrown when user input fails validation checks.
 * 
 * @author Library Management System Team
 * @version 1.0
 */
public class InvalidInputException extends Exception {
    
    /**
     * Constructs a new InvalidInputException with no detail message.
     */
    public InvalidInputException() {
        super();
    }
    
    /**
     * Constructs a new InvalidInputException with the specified detail message.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public InvalidInputException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new InvalidInputException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause of this exception
     */
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
