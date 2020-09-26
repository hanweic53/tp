package seedu.tr4cker.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompletionStatusTest {

    @Test
    public void constructor_invalidCompletionStatus_throwsIllegalArgumentException() {
        int invalidPercentage = -1;
        assertThrows(IllegalArgumentException.class, () -> new CompletionStatus(invalidPercentage));
    }

    @Test
    public void isValidCompletionStatus() {

        // invalid percentage
        assertFalse(CompletionStatus.isValidCompletionStatus(-1)); // less than zero
        assertFalse(CompletionStatus.isValidCompletionStatus(101)); // greater than 100

        // valid percentage
        assertTrue(CompletionStatus.isValidCompletionStatus(50));
        assertTrue(CompletionStatus.isValidCompletionStatus(0)); // minimum
        assertTrue(CompletionStatus.isValidCompletionStatus(100)); // maximum
    }
}