package seedu.address.model.module;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of Module that enforces uniqueness between its elements and does not allow nulls.
 * A moduleOffering is considered unique by comparing using {@code Module#isSameModuleOffering(Module)}. As such, adding and updating of
 * moduleOfferings uses Module#isSameModuleOffering(Module) for equality so as to ensure that the moduleOffering being added or updated is
 * unique in terms of identity in the UniqueModuleList. However, the removal of a moduleOffering uses Module#equals(Object) so
 * as to ensure that the moduleOffering with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Module#isSameModuleOffering(Module)
 */
public class UniqueModuleList implements Iterable<Module> {

    private final ObservableList<Module> internalList = FXCollections.observableArrayList();
    private final ObservableList<Module> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent moduleOffering as the given argument.
     */
    public boolean contains(Module toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameModuleOffering);
    }

    /**
     * Adds a moduleOffering to the list.
     * The moduleOffering must not already exist in the list.
     */
    public void add(Module toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            // throw new DuplicateModuleOfferingException(); TODO
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the moduleOffering {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the list.
     * The moduleOffering identity of {@code editedModule} must not be the same as another existing moduleOffering in the list.
     */
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        int index = internalList.indexOf(target);
        if (index == -1) {
            // throw new ModuleOfferingNotFoundException(); TODO
        }

        if (!target.isSameModuleOffering(editedModule) && contains(editedModule)) {
            // throw new DuplicateModuleOfferingException(); TODO
        }

        internalList.set(index, editedModule);
    }

    /**
     * Removes the equivalent moduleOffering from the list.
     * The moduleOffering must exist in the list.
     */
    public void remove(Module toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            // throw new ModuleOfferingNotFoundException(); TODO
        }
    }

    public void setModules(UniqueModuleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        requireAllNonNull(modules);
        if (!moduleOfferingsAreUnique(modules)) {
            // throw new DuplicateModuleOfferingException(); TODO
        }

        internalList.setAll(modules);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Module> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Module> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueModuleList // instanceof handles nulls
                        && internalList.equals(((UniqueModuleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code modules} contains only unique modules.
     */
    private boolean moduleOfferingsAreUnique(List<Module> modules) {
        for (int i = 0; i < modules.size() - 1; i++) {
            for (int j = i + 1; j < modules.size(); j++) {
                if (modules.get(i).isSameModuleOffering(modules.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
