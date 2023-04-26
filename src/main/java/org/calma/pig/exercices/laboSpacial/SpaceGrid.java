package org.calma.pig.exercices.laboSpacial;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.calma.pig.exercices.laboSpacial.models.cell.Cell;
import org.calma.pig.exercices.laboSpacial.models.cell.CellListener;
import org.calma.pig.exercices.laboSpacial.models.obstacle.Obstacle;
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
    }

    public void initializeGrid(List<Obstacle> obstacles) {
        // On update la position des cellules en fonction de la vraie position de l'emplacement

        for (Iterator<Obstacle> iterator = obstacles.iterator(); iterator.hasNext(); ) {
            Obstacle obstacle = iterator.next();

            List<Cell> cells = obstacle.getGeometry();
            for (Iterator<Cell> cellIterator = cells.iterator(); cellIterator.hasNext(); ) {
                Cell cell = cellIterator.next();

                Cell cellUpdated = new Cell((int) (cell.getX() + obstacle.getRealPosition().getX()),
                                        (int) (cell.getY() + obstacle.getRealPosition().getY()),
                                        obstacle.getColor()
                                );

                cellUpdated.setLabel(obstacle.getDescription());

                this.setCell(cellUpdated);
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

