package se.kth.iv1350.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger {
    private PrintWriter logStream;

    public FileLogger() {
        createLogStream("log.txt");
    }

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

    public void log(String message) {
        logStream.println(message);
    }
}