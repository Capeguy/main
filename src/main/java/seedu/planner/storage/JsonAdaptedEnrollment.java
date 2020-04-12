package seedu.planner.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.planner.commons.exceptions.IllegalValueException;
import seedu.planner.model.grades.Grade;
import seedu.planner.model.student.Enrollment;

/**
 * Jackson-friendly version of {@link Enrollment}.
 */
public class JsonAdaptedEnrollment {
    private final JsonAdaptedModuleCode code;

    /**
     * Represents the student's grade. Can be null.
     */
    private int credit;

    private JsonAdaptedGrade grade;

    @JsonCreator
    public JsonAdaptedEnrollment(@JsonProperty("code") JsonAdaptedModuleCode code,
                                 @JsonProperty("grade") JsonAdaptedGrade grade,
                                 @JsonProperty("credit") int credit) {
        this.code = code;
        this.grade = grade;
        this.credit = credit;
    }

    /**
     * Converts the given {@code Enrollment} into this class for Jackson use.
     */
    public JsonAdaptedEnrollment(Enrollment source) {
        this.code = new JsonAdaptedModuleCode(source.getModuleCode());
        Optional<Grade> optionalGrade = source.getGrade();
        if (optionalGrade.isPresent()) {
            this.grade = new JsonAdaptedGrade(optionalGrade.get());
        } else {
            this.grade = null;
        }
        this.credit = source.getCredit();
    }

    /**
     * Converts this Jackson-friendly adapted enrollment into the model's {@code Enrollment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted enrollment.
     */
    public Enrollment toModelType() throws IllegalValueException {
        return new Enrollment(code.toModelType(), grade == null ? Optional.empty() : Optional.of(grade.toModelType()),
                credit);
    }

    public JsonAdaptedModuleCode getCode() {
        return code;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public JsonAdaptedGrade getGrade() {
        return grade;
    }

    public void setGrade(JsonAdaptedGrade grade) {
        this.grade = grade;
    }
}
