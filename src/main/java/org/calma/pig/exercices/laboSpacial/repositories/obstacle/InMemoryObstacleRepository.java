package org.calma.pig.exercices.laboSpacial.repositories.obstacle;

import javafx.scene.paint.Color;
import org.calma.pig.exercices.laboSpacial.models.cell.Cell;
import org.calma.pig.exercices.laboSpacial.models.obstacle.CircularObstacle;
import org.calma.pig.exercices.laboSpacial.models.obstacle.Obstacle;
import org.calma.pig.exercices.laboSpacial.models.obstacle.RectangularObstacle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryObstacleRepository implements IObstacleRepository {
    private List<Obstacle> data;

    public InMemoryObstacleRepository() {
        this.data = new ArrayList<>();

        loadFromMemory();
    }

    public void loadFromMemory(){
        List<Obstacle> obstacles = new ArrayList<>();

        List<Cell> cellsOb1 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 10; j++) {
                cellsOb1.add(new Cell(i,j));
            }
        }
        List<Cell> cellsOb2 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                cellsOb2.add(new Cell(i,j));
            }
        }
        List<Cell> cellsOb4 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                cellsOb4.add(new Cell(i,j));
            }
        }

        RectangularObstacle ob1 = new RectangularObstacle(
                "Obstacle1",
                "Moyen obstacle rectangulaire vert",
                cellsOb1,
                new Cell(38,30));
        ob1.setColor(Color.GREEN);

        RectangularObstacle ob2 = new RectangularObstacle(
                "Obstacle2",
                "Petit obstacle rectangulaire jaune",
                cellsOb2,
                new Cell(90,36));
        ob2.setColor(Color.YELLOW);

        CircularObstacle ob3 = new CircularObstacle(
                "Obstacle3",
                "Gros obstacle circulaire bleu",
                36,
                new Cell(74,67));
        ob3.setColor(Color.BLUE);

        RectangularObstacle ob4 = new RectangularObstacle(
                "Obstacle4",
                "Moyen obstacle rectangulaire rouge",
                cellsOb4,
                new Cell(48,72));
        ob4.setColor(Color.RED);

        CircularObstacle ob5 = new CircularObstacle(
                "Obstacle5",
                "Petit obstacle circulaire violet",
                2,
                new Cell(83,74));
        ob5.setColor(Color.PURPLE);

        CircularObstacle ob6 = new CircularObstacle(
                "Obstacle6",
                "Moyen obstacle circulaire gris",
                8,
                new Cell(95,2));
        ob6.setColor(Color.GRAY);

        obstacles.add(ob1);
        obstacles.add(ob2);
        obstacles.add(ob3);
        obstacles.add(ob4);
        obstacles.add(ob5);
        obstacles.add(ob6);

        this.data = obstacles;
    }

    public List<Obstacle> findAll(){
        return this.data;
    }
    public Obstacle findByName(String name){
        List<Obstacle> obstacles = this.data;

        for(Iterator<Obstacle> iterator = obstacles.iterator(); iterator.hasNext(); ) {
            Obstacle obstacle =  iterator.next();
            if(obstacle.getName().compareTo(name) == 0){
                return obstacle;
            }
        }

        return null;
    }

    @Override
    public void createORupdate(Obstacle obstacle){
        Obstacle o = this.findByName(obstacle.getName());
        if(o!= null){
            this.data.remove(o);
        }

        this.data.add(obstacle);
    }
}