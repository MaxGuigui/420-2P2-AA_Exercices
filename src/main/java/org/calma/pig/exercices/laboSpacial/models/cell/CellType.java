package org.calma.pig.exercices.laboSpacial.models.cell;

import org.calma.pig.exercices.laboSpacial.models.obstacle.Obstacle;

public enum CellType {
    STANDARD("STANDARD"),
    RECT_OBST("RECT_OBST"),
    CIRC_OBST("CIRC_OBST");

    private String label;

    CellType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
