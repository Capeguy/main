package seedu.planner.logic.commands.lessons;

import seedu.planner.logic.commands.Command;

/**
 * Base command for subcommands involving queries about modules.
 */
public abstract class LessonCommand extends Command {

    public static final String COMMAND_WORD = "lessons";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds in a certain lessons\n"
            + "Subcommands: add, remove, list\n"
            + "Example: " + getQualifiedCommand("add") + " CS2030";

    /**
     * Returns the (@code COMMAND_NAME) concatenated with the name of the input (@code subCommand).
     *
     * @param subCommand
     * @return Qualified name
     */
    public static String getQualifiedCommand(String subCommand) {
        return COMMAND_WORD + " " + subCommand;
    }
}
