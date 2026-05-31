package clydeconservationsystem;

/**
 * Custom exception class to catch incorrect values for the clydeconservationsystem package
 * @author Erik
 */
public class ValidationException extends Exception{
    /**
     * pass the string message to the parent class for the getMessage() method
     * @param message Error message to display
     */
    public ValidationException(String message){
        // pass the message to the parent getMessage() method
        super("\n"+message);
    }
}
