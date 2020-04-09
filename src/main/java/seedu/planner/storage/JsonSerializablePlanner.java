package seedu.planner.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.planner.commons.exceptions.IllegalValueException;
import seedu.planner.model.Planner;
import seedu.planner.model.ReadOnlyPlanner;
import seedu.planner.model.student.Student;

/**
 * An Immutable Planner that is serializable to JSON format.
 */
@JsonRootName(value = "planner")
class JsonSerializablePlanner {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Student list contains duplicate student(s).";

    private final int activeStudentIndex;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePlanner} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePlanner(@JsonProperty("activeStudentIndex") int activeStudentIndex,
                                   @JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.activeStudentIndex = activeStudentIndex;
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyPlanner} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePlanner}.
     */
    public JsonSerializablePlanner(ReadOnlyPlanner source) {
        activeStudentIndex = source.getActiveStudentIndex();
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));

    }

    /**
     * Converts this planner book into the model's {@code Planner} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Planner toModelType() throws IllegalValueException {
        Planner planner = new Planner();

        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (planner.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            planner.addStudent(student);
        }

        if (0 <= activeStudentIndex && activeStudentIndex < students.size()) {
            // TODO: ensure `activeStudent.toModelType()` can be used in `Planner#activateStudent`
            JsonAdaptedStudent jsonActiveStudent = students.get(activeStudentIndex);

            Student activeStudent = planner.getEqualStudent(jsonActiveStudent.toModelType());
            planner.activateStudent(activeStudent);
        }


        return planner;
    }

}
