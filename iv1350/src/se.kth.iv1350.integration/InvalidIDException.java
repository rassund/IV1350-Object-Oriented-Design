package se.kth.iv1350.integration;

/**
 * Thrown when some identifier is invalid, i.e. its value is not allowed, or it cannot be found.
 */
public class InvalidIDException extends Exception {
    /**
     * Writes a given message to the user.
     * As this is a checked exception, the developer can specify what message to give to the user.
     * @param message The message to be displayed to the user.
     */
    public InvalidIDException(String message) {
        super(message);
    }
}