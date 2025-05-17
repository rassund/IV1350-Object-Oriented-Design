package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseExceptionTest {
    DatabaseException exception;

    @BeforeEach
    void setUp() {
        exception = new DatabaseException("Test message");
    }

    @AfterEach
    void tearDown() {
        exception = null;
    }

    @Test
    void DatabaseExceptionNotNull() {
        assertNotNull(exception);
    }

    @Test
    void DatabaseExceptionMessageNotNull() {
        assertNotNull(exception.getMessage());
    }

    @Test
    void DatabaseExceptionMessageCorrect() {
        assertEquals("Test message", exception.getMessage());
    }
}
