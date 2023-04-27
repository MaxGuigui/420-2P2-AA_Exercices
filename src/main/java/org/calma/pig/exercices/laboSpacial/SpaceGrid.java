package org.calma.pig.exercices.laboSpacial;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.calma.pig.exercices.laboSpacial.models.cell.Cell;
import org.calma.pig.exercices.laboSpacial.models.cell.CellListener;
import org.calma.pig.exercices.laboSpacial.models.cell.CellType;
import org.calma.pig.exercices.laboSpacial.models.obstacle.CircularObstacle;
import org.calma.pig.exercices.laboSpacial.models.obstacle.Obstacle;
import org.calma.pig.exercices.laboSpacial.models.obstacle.RectangularObstacle;
import org.calma.pig.exercices.laboSpacial.repositories.obstacle.IObstacleRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class SpaceGrid extends Grid {
    private IObstacleRepository obstacleRepository;

    public SpaceGrid(IObstacleRepository obstacleRepository, int columns, int rows) throws IOException {
        super(columns, rows);

        this.obstacleRepository = obstacleRepository;

        // Cell clicked
        this.setOnMouseClicked(new CellListener.CellClickListener(this));
        // Cell dragged into
        this.setOnMouseDragged(new CellListener.CellClickListener(this));
        // Cell hovered over
        this.setOnMouseMoved(new CellListener.CellHoverListener(this));

        this.initializeGrid(this.obstacleRepository.findAll());

        this.drawObstacles();

        this.canvas.widthProperty().bind(widthProperty());
        this.canvas.heightProperty().bind(heightProperty());

        this.widthProperty().addListener(evt -> {
            try {
                drawObstacles();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        this.heightProperty().addListener(evt -> {
            try {
                drawObstacles();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void drawObstacles() throws IOException {
        int columns = this.getColumns();
        int rows = this.getRows();
        Cell[][] cells = this.getCells();
        List<Obstacle> obstacles = obstacleRepository.findAll();
        int cellSize = this.getCellSize();
        double zoomFactor = this.getZoomFactor();

        GraphicsContext gc = canvas.getGraphicsContext2D();

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
    }

    public void initializeGrid(List<Obstacle> obstacles) {
        // On update la position des cellules en fonction de la vraie position de l'emplacement

        for (Iterator<Obstacle> iterator = obstacles.iterator(); iterator.hasNext(); ) {
            Obstacle obstacle = iterator.next();

            if(obstacle.getClass() == RectangularObstacle.class){
                RectangularObstacle rectangularObstacle = (RectangularObstacle) obstacle;

                List<Cell> cells = rectangularObstacle.getGeometry();
                for (Iterator<Cell> cellIterator = cells.iterator(); cellIterator.hasNext(); ) {
                    Cell cell = cellIterator.next();

                    Cell cellUpdated = new Cell((int) (cell.getX() + rectangularObstacle.getRealPosition().getX()),
                            (int) (cell.getY() + rectangularObstacle.getRealPosition().getY()),
                            rectangularObstacle.getColor()
                    );

                    cellUpdated.setLabel(rectangularObstacle.getDescription());
                    cellUpdated.setType(CellType.RECT_OBST);

                    this.setCell(cellUpdated);
                }
            }
            else if (obstacle.getClass() == CircularObstacle.class) {
                CircularObstacle circularObstacle = (CircularObstacle) obstacle;

                int x = (int)circularObstacle.getRealPosition().getX();
                int y = (int)circularObstacle.getRealPosition().getY();

                Cell cell = new Cell(x,y,circularObstacle.getColor());
                cell.setLabel(circularObstacle.getDescription());
                cell.setType(CellType.CIRC_OBST);

                this.setCell(cell);
            }
        }
    }

    @Override
    public void onCellClick(MouseEvent event, Cell cell, int gridX, int gridY) {
        //Si la touche CTLR n'est pas enfoncé, on retourne. Voir https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/MouseEvent.html#isControlDown--
        if(!event.isControlDown()){
            return;
        }
    }


    @Override
    public void onCellHover(Cell cell, int gridX, int gridY) {
        //this.informationsLabel.setText("x: " + gridX + " y:" + gridY + " " + cell.getLabel());
        System.out.println("x: " + gridX + " y:" + gridY + " " + cell.getLabel());
    }
}

