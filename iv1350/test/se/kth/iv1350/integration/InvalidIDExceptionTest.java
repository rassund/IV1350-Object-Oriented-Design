package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidIDExceptionTest {
    InvalidIDException exception;

    @BeforeEach
    void setUp() {
        exception = new InvalidIDException("Test message");
    }

    @AfterEach
    void tearDown() {
        exception = null;
    }

    @Test
    void InvalidIDExceptionNotNull() {
        assertNotNull(exception);
    }

    @Test
    void InvalidIDExceptionMessageNotNull() {
        assertNotNull(exception.getMessage());
    }

    @Test
    void InvalidIDExceptionMessageCorrect() {
        assertEquals("Test message", exception.getMessage());
    }
}
