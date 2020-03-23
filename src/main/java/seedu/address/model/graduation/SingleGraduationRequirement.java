package seedu.address.model.graduation;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.student.Student;

import java.util.List;

public class SingleGraduationRequirement extends GraduationRequirement {

    public SingleGraduationRequirement(ModuleCode moduleCode) {
        this.moduleCode = moduleCode;
    }
    public boolean isFulfilled(List<ModuleCode> moduleCodes) {
        return moduleCodes.contains(moduleCode);
    }

}
