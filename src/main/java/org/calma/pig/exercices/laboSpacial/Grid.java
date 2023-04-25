package org.calma.pig.exercices.laboSpacial;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.calma.pig.exercices.laboSpacial.models.cell.Cell;

public abstract class Grid extends Pane implements IGridEvents {
    private Canvas canvas;
    private int rows = 100;
    private int columns = 100;
    private int cellSize = 10;
    private double zoomFactor = 1;

    // La liste des cellules **devrait** être maintenue ailleurs
    private Cell[][] cells;

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.canvas = new Canvas();

        this.getChildren().add(this.canvas);

        this.initializeCells();

        this.drawGrid();

        this.canvas.widthProperty().bind(widthProperty());
        this.canvas.heightProperty().bind(heightProperty());

        this.widthProperty().addListener(evt -> drawGrid());
        this.heightProperty().addListener(evt -> drawGrid());
    }

    private void initializeCells() {
        cells = new Cell[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                double x = col * cellSize;
                double y = row * cellSize;

                cells[row][col] = new Cell((int)x, (int)y, new Color(1,1,1, 0.5));
            }
        }
    }

    private void drawGrid() {
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                Cell cell = cells[row][col];
                gc.setFill(cell.getColor());
                double x = col * cellSize * zoomFactor;
                double y = row * cellSize * zoomFactor;
                gc.fillRect(y + 1, x + 1, cellSize * zoomFactor - 1, cellSize * zoomFactor - 1);
//                DRAW HORIZONTAL LINES
                gc.strokeLine(0, x+0.5, canvasWidth, x);
//                DRAW VERTICAL LINES
                gc.strokeLine(y+0.5, 0, y, canvasHeight);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public void setCell(Cell cell) {
        this.cells[(int) cell.getX()][(int) cell.getY()] = cell;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }
}
