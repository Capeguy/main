package seedu.planner.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.planner.logic.commands.CommandResult;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.grades.Grade;
import seedu.planner.model.grades.LetterGrade;
import seedu.planner.model.module.ModuleCode;


//@@author thetruevincentchow

/**
 * Sets the grade of a module in the selected timetable.
 */
public class ModuleGradeSetCommand extends ModuleGradeCommand {
    public static final String MESSAGE_SET_GRADE_SUCCESS = "Set grade of module %1$s to: %2$s";


    private final LetterGrade letterGrade;
    private final boolean isSu;

    public ModuleGradeSetCommand(ModuleCode moduleCode, LetterGrade grade, boolean isSu) {
        super(moduleCode);

        requireAllNonNull(grade);
        this.letterGrade = grade;
        this.isSu = isSu;
    }

    /**
     * Generates a command execution success message for setting the enrollment with the given {@code moduleCode}
     * to have the specified {@code grade}.
     */
    private String generateSetGradeSuccessMessage(ModuleCode moduleCode, Grade grade) {
        return String.format(MESSAGE_SET_GRADE_SUCCESS, moduleCode.value, grade);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        validate(model);

        Grade grade = new Grade(letterGrade, isSu);
        model.setModuleGrade(moduleCode, grade);
        return new CommandResult(generateSetGradeSuccessMessage(moduleCode, grade));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModuleGradeSetCommand that = (ModuleGradeSetCommand) o;
        return letterGrade == that.letterGrade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letterGrade);
    }
}
//@@author
