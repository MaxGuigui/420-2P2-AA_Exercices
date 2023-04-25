package org.calma.pig.exercices.laboSpacial.models.obstacle;

public enum ObstacleState {
    WALKABLE("WALKABLE"),
    NOT_WALKABLE("NOT_WALKABLE"),
    ENTRY_POINT("ENTRY_POINT");

    private String label;

    ObstacleState(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
