package org.calma.pig.exercices.laboSpacial;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.calma.pig.exercices.laboSpacial.models.cell.Cell;
import org.calma.pig.exercices.laboSpacial.models.cell.CellType;

public abstract class Grid extends Pane implements IGridEvents {
    private Canvas canvas;
    private int rows;
    private int columns;
    private int cellSize = 8;
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
                gc.strokeLine(0, x + 0.5, canvasWidth, x + 0.5);

//                DRAW VERTICAL LINES
                if((row + 5) % 10 == 0){
                    gc.setLineWidth(1.0);
                    gc.setStroke(Color.RED);
                }
                else{
                    gc.setLineWidth(1.0);
                    gc.setStroke(Color.GRAY);
                }
                gc.strokeLine(y + 0.5, 0, y + 0.5, canvasHeight);
            }
        }
//        DRAW OBSTACLES
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                Cell cell = cells[row][col];
                double x = col * cellSize * zoomFactor;
                double y = row * cellSize * zoomFactor;

                Color colorObstacle = cell.getColor();
                gc.setFill(colorObstacle);

                if(cell.getType() == CellType.RECT_OBST){
                    gc.fillRect(y + 0.5, x + 0.5, cellSize * zoomFactor, cellSize * zoomFactor);
                }
                else if (cell.getType() == CellType.CIRC_OBST) {
                    gc.fillOval(x,y,80.0,50.0);
                }
            }
        }
        double x = columns / 2.0 * cellSize * zoomFactor;
        double y = rows / 2.0 * cellSize * zoomFactor;

//        DRAW X AND Y AXES
        gc.setLineWidth(1.0);
        gc.setStroke(Color.BLACK);
        gc.strokeLine(0, x + 0.5, canvasWidth, x + 0.5);
        gc.strokeLine(y + 0.5, 0, y + 0.5, canvasHeight);

        x = columns * cellSize * zoomFactor;
        y = rows * cellSize * zoomFactor;

//        DRAW BORDERS
        gc.setLineWidth(2.0);
        gc.strokeLine(0, 0.5, canvasWidth, 0.5);
        gc.strokeLine(0.5, 0, 0.5, canvasHeight);
        gc.strokeLine(0, x + 0.5, canvasWidth, x + 0.5);
        gc.strokeLine(y + 0.5, 0, y + 0.5, canvasHeight);
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
