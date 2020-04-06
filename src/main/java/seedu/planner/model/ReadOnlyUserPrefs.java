package seedu.planner.model;

import java.nio.file.Path;

import seedu.planner.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getPlannerFilePath();

}
