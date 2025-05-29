package se.kth.iv1350.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class FileLoggerTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    private FileLogger fileLogger;

    @BeforeEach
    void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        fileLogger = null;
    }

    @AfterEach
    void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        fileLogger = null;
    }

    @Test
    void createLogStreamError() {
        String invalidFileName = "/invalidFileName";
        fileLogger = new FileLogger(invalidFileName);
        String result = outContent.toString();
        assertTrue(result.contains("CANNOT LOG"), "Incorrect error message when creating FileLogger.");
    }
}