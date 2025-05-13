package se.kth.iv1350.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

/**
 * Used to print messages/text onto a separate text file.
 */
public class FileLogger {
    private PrintWriter logStream;

    /**
     * Creates a text file called "log.txt" that is to contain messages for developers.
     */
    public FileLogger() {
        createLogStream("log.txt");
    }

    /**
     * Creates a text file with a given file name.
     * @param fileName The name of the file to be created.
     */
    public FileLogger(String fileName) {
        createLogStream(fileName);
    }

    private void createLogStream(String fileName) {
        try {
            logStream = new PrintWriter(new FileWriter(fileName), true);
        } catch (IOException e) {
            System.out.println("CAN NOT LOG.");
            e.printStackTrace();
        }
    }

    /**
     * Writes a given message to the created text file.
     * @param message The message that is added into the created text file.
     */
    public void log(String message) {
        ZonedDateTime now = ZonedDateTime.now();
        logStream.println();
        logStream.println(now);
        logStream.println(message);
        logStream.println();
    }
}