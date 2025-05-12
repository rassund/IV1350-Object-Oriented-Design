package se.kth.iv1350.integration;

import se.kth.iv1350.util.FileLogger;

public class DatabaseException extends RuntimeException {
    FileLogger logger;

    public DatabaseException(String message) {
        super(message);
        this.logger = new FileLogger();
        this.logger.log(message);
    }
}