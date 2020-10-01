package seedu.tr4cker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tr4cker.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.UserPrefs;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) for {@code TagCommand}.
 */
class TagCommandTest {

    private final Model model = new ModelManager(getTypicalTr4cker(), new UserPrefs());
    private final Tag tag1 = new Tag("homework");
    private final Tag tag2 = new Tag("assignment");
    private final Set<Tag> add = new HashSet<>();
    private final Set<Tag> delete = new HashSet<>();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK, add, delete);

        String expectedMessage = String.format(TagCommand.MESSAGE_SUCCESS, taskToEdit);

        ModelManager expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        taskToEdit.addTags(add);
        taskToEdit.deleteTags(delete);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        TagCommand tagCommand = new TagCommand(outOfBoundIndex, add, delete);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK, add, delete);

        String expectedMessage = String.format(TagCommand.MESSAGE_SUCCESS, taskToEdit);

        Model expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        taskToEdit.addTags(add);
        taskToEdit.deleteTags(delete);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of Tr4cker list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTr4cker().getTaskList().size());

        TagCommand tagCommand = new TagCommand(outOfBoundIndex, add, delete);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index index = new Index(1);
        add.add(tag1);
        delete.add(tag2);
        TagCommand tagCommand1 = new TagCommand(index, add, delete);
        TagCommand tagCommand2 = new TagCommand(index, add, delete);
        TagCommand tagCommand3 = new TagCommand(index, delete, add);

        // same object -> returns true
        assertEquals(tagCommand1, tagCommand1);

        // same values -> returns true
        assertEquals(tagCommand2, tagCommand1);

        // different types -> returns false
        assertNotEquals(tagCommand1, 1);

        // null -> returns false
        assertNotEquals(tagCommand1, null);

        // different sets -> returns false
        assertNotEquals(tagCommand3, tagCommand1);
    }

}
