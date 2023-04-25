package org.calma.pig.exercices.laboCollegeGrid.models.emplacement;

public enum EmplacementState {
    WALKABLE("WALKABLE"),
    NOT_WALKABLE("NOT_WALKABLE"),
    ENTRY_POINT("ENTRY_POINT");

    private String label;

    EmplacementState(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
