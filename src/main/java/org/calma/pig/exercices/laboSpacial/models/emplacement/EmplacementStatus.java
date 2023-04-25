package org.calma.pig.exercices.laboSpacial.models.emplacement;

import org.calma.pig.exercices.laboSpacial.models.AbstractObject;

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
