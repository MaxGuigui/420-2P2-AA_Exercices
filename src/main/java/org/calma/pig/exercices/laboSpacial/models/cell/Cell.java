package org.calma.pig.exercices.laboSpacial.models.cell;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Cell extends Point2D {
    private String label;
    private Color color;
    private CellType type;

    public Cell(int x, int y) {
        this(x, y, Color.WHITE);
    }
    public Cell(int x, int y, Color color) {
        super(x, y);

        this.color = color;
        this.type = CellType.STANDARD;
    }

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public CellType getType() {
        return type;
    }
    public void setType(CellType type) {
        this.type = type;
    }
}
