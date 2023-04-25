package org.calma.pig.exercices.laboSpacial.models.cell;

public enum CellState {
    WALKABLE("WALKABLE"),
    NOT_WALKABLE("NOT_WALKABLE"),
    NOT_USED("NOT_USED");

    private String label;

    CellState(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}