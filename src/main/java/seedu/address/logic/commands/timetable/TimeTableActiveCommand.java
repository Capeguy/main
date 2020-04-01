package seedu.address.logic.commands.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_SEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_YEAR;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.time.StudentSemester;


/**
 * Sets a timetable as the currently selected timetable in the currently selected student's timetable list.
 */
public class TimeTableActiveCommand extends TimeTableCommand {
    public static final String COMMAND_WORD = "active";

    public static final String MESSAGE_USAGE = getQualifiedCommand(COMMAND_WORD)
        + ": Sets the active timetable of the active student.\n"
        + "Parameters: "
        + "[" + PREFIX_STUDENT_YEAR + "YEAR] "
        + "[" + PREFIX_STUDENT_SEM + "SEMESTER] \n"
        + "Example: " + getQualifiedCommand(COMMAND_WORD) + " year/1 sem/ONE";

    public static final String MESSAGE_ACTIVE_TIMETABLE_SUCCESS = "Set semester as active: %1$s";
    public static final String MESSAGE_INVALID_SEMESTER = "Semester does not exist in list of timetables: %1$s";

    private final StudentSemester studentSemester;

    public TimeTableActiveCommand(StudentSemester studentSemester) {
        requireAllNonNull(studentSemester);
        this.studentSemester = studentSemester;
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(StudentSemester semesterYear) {
        return String.format(MESSAGE_ACTIVE_TIMETABLE_SUCCESS, semesterYear);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Student activeStudent = model.getActiveStudent();
        if (activeStudent == null) {
            throw new CommandException(Messages.MESSAGE_NO_STUDENT_ACTIVE);
        }

        if (!model.getPlanner().hasSemester(studentSemester)) {
            throw new CommandException(String.format(MESSAGE_INVALID_SEMESTER, studentSemester));
        }

        model.activateSemester(studentSemester);

        return new CommandResult(generateSuccessMessage(studentSemester));
    }
}
