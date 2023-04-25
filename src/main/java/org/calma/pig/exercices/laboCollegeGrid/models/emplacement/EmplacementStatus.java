package org.calma.pig.exercices.laboCollegeGrid.models.emplacement;

import org.calma.pig.exercices.laboCollegeGrid.models.AbstractObject;

public class EmplacementStatus extends AbstractObject {
    public EmplacementStatus(int id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public EmplacementStatus() {
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
