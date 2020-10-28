package seedu.tr4cker.model.daily.exceptions;

/**
 * Signals that the operation will result in duplicate Todos (Todos are considered duplicates if they have
 * the same identity).
 */
public class DuplicateTodoException extends RuntimeException {
    public DuplicateTodoException() {
        super("Operation would result in duplicate daily todo tasks");
    }
}