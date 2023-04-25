package org.calma.pig.exercices.laboCollegeGrid.models.cell;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Cell extends Point2D {
    private String label;
    private Color color;
    private CellState state;
    private CellType type;

    public Cell(int x, int y) {
        this(x, y, Color.WHITE);
    }

    public Cell(int x, int y, Color color) {
        this(x, y, color, null, null);
    }

    public Cell(int x, int y, CellState cellState, CellType cellType) {
        this(x, y, Color.WHITE, cellState, cellType);
    }
    public Cell(int x, int y, Color color, CellState cellState, CellType cellType) {
        super(x, y);

        this.color = color;
        this.state = cellState;
        this.type = cellType;
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

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }
}
