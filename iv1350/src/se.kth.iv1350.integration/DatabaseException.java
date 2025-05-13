package se.kth.iv1350.integration;

import se.kth.iv1350.util.FileLogger;

/**
 * Thrown when the database cannot be called, for example if the database server isn't currently running.
 */
public class DatabaseException extends RuntimeException {
    FileLogger logger;

    /**
     * Writes a given message to the user and writes the same message to a file using a <code>FileLogger</code> instance.
     * The message is written by the user right now, as there currently is no database - thus a simulation of one is made instead.
     * @param message The message to be displayed to the user and the developer.
     */
    public DatabaseException(String message) {
        super(message);
        this.logger = new FileLogger();
        this.logger.log(message);
    }
}