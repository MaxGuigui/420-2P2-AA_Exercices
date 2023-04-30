package org.calma.pig.exercices.laboSpacial;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.calma.pig.exercices.laboSpacial.models.cell.Cell;
import org.calma.pig.exercices.laboSpacial.models.cell.CellType;

public abstract class Grid extends Pane implements IGridEvents {
    protected Canvas canvas;
    private Cell[][] cells;
    private int rows;
    private int columns;
    private int cellSize = 8;
    private double zoomFactor = 1;

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
        double gridWidth = columns * cellSize;
        double gridHeight = rows * cellSize;

        double zeroW = canvasWidth/2 - gridWidth/2;
        double zeroH = canvasHeight/2 - gridHeight/2;

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                double x = col * cellSize * zoomFactor;
                double y = row * cellSize * zoomFactor;

//                DRAW HORIZONTAL LINES
                if((col + 5) % 10 == 0){
                    gc.setLineWidth(1.0);
                    gc.setStroke(Color.RED);
                }
                else{
                    gc.setLineWidth(1.0);
                    gc.setStroke(Color.GRAY);
                }
                gc.strokeLine(0 + zeroW, x + 0.5 + zeroH, gridWidth + zeroW, x + 0.5 + zeroH);

//                DRAW VERTICAL LINES
                if((row + 5) % 10 == 0){
                    gc.setLineWidth(1.0);
                    gc.setStroke(Color.RED);
                }
                else{
                    gc.setLineWidth(1.0);
                    gc.setStroke(Color.GRAY);
                }
                gc.strokeLine(y + 0.5 + zeroW, 0 + zeroH, y + 0.5 + zeroW, gridHeight + zeroH);
            }
        }

        drawMainLines();
    }

    protected void drawMainLines() {
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();
        double gridWidth = columns * cellSize;
        double gridHeight = rows * cellSize;

        double zeroW = canvasWidth/2 - gridWidth/2;
        double zeroH = canvasHeight/2 - gridHeight/2;

        GraphicsContext gc = canvas.getGraphicsContext2D();

        double x = columns / 2.0 * cellSize * zoomFactor;
        double y = rows / 2.0 * cellSize * zoomFactor;

//        DRAW X AND Y AXES
        gc.setLineWidth(1.0);
        gc.setStroke(Color.BLACK);
        gc.strokeLine(0 + zeroW, x + 0.5 + zeroH, gridWidth + zeroW, x + 0.5 + zeroH);
        gc.strokeLine(y + 0.5 + zeroW, 0 + zeroH, y + 0.5 + zeroW, gridHeight + zeroH);

        x = columns * cellSize * zoomFactor;
        y = rows * cellSize * zoomFactor;

//        DRAW BORDERS
        gc.setLineWidth(2.0);
        gc.strokeLine(0 + zeroW, 0.5 + zeroH, gridWidth + zeroW, 0.5 + zeroH);
        gc.strokeLine(0.5 + zeroW, 0 + zeroH, 0.5 + zeroW, gridHeight + zeroH);
        gc.strokeLine(0 + zeroW, x + 0.5 + zeroH, gridWidth + zeroW, x + 0.5 + zeroH);
        gc.strokeLine(y + 0.5 + zeroW, 0 + zeroH, y + 0.5 + zeroW, gridHeight + zeroH);
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

    public double getZoomFactor() {
        return zoomFactor;
    }
    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }
}
