package org.calma.pig.exercices.laboSpacial.models.obstacle;

import org.calma.pig.exercices.laboSpacial.models.AbstractObject;

public class ObstacleStatus extends AbstractObject {
    public ObstacleStatus(int id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public ObstacleStatus() {
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
