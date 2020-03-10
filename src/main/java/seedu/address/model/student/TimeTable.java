package seedu.address.model.student;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.UniqueEnrollmentList;
import seedu.address.model.time.StudentSemester;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TimeTable {
    public UniqueEnrollmentList enrollments = new UniqueEnrollmentList();

    public TimeTable() {
    }

    public TimeTable(List<Enrollment> enrollments) {
        enrollments.forEach(this.enrollments::add);
    }

    public static TimeTable sampleTimeTable() {
        TimeTable timeTable = new TimeTable();
        timeTable.addEnrollment(new Enrollment(new ModuleCode("CS2040")));
        return timeTable;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollments.remove(enrollment);
    }

    public boolean hasEnrollment(Enrollment enrollment) {
        return enrollments.contains(enrollment);
    }

    public UniqueEnrollmentList getEnrollments() {
        return enrollments;
        //return enrollments.asUnmodifiableObservableList(); //TODO: replace with ObservableList<Enrollment>
    }

    public List<ModuleCode> getModuleCodes() {
        return enrollments.stream().map(Enrollment::getModuleCode).collect(Collectors.toList());
    }

    public boolean hasModuleCode(ModuleCode moduleCode) {
        return enrollments.stream().anyMatch(enrollment -> enrollment.getModuleCode().equals(moduleCode));
    }

    public void removeModuleCode(ModuleCode moduleCode) {
        enrollments.removeIf(enrollment -> enrollment.getModuleCode().equals(moduleCode));
    }
}
