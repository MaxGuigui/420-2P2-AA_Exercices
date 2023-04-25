package org.calma.pig.exercices.laboSpacial.models.cell;

public enum CellType {
    STANDARD("STANDARD"),
    ENTRY_POINT("ENTRY_POINT"),
    EXIT_POINT("EXIT_POINT");

    private String label;

    CellType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}