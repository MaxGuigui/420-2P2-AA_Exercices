package org.calma.pig.exercices.laboCollegeGrid.models.emplacement;

public enum EmplacementType {
    CLASSE("CLASSE"),
    BUREAU("BUREAU"),
    ESCALIER("ESCALIER"),
    CORRIDOR("CORRIDOR"),
    UTILITAIRES("UTILITAIRES"),
    TOILETTES("TOILETTES"),
    AUTRES("AUTRES");

    private String label;

    EmplacementType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
