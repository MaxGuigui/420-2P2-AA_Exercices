package org.calma.pig.exercices.laboSpacial.models.obstacle;

public enum ObstacleType {
    CLASSE("CLASSE"),
    BUREAU("BUREAU"),
    ESCALIER("ESCALIER"),
    CORRIDOR("CORRIDOR"),
    UTILITAIRES("UTILITAIRES"),
    TOILETTES("TOILETTES"),
    AUTRES("AUTRES");

    private String label;

    ObstacleType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
