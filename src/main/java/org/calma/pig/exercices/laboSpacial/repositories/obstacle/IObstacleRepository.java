package org.calma.pig.exercices.laboSpacial.repositories.obstacle;


import org.calma.pig.exercices.laboSpacial.models.obstacle.Obstacle;

import java.io.IOException;
import java.util.List;

public interface IObstacleRepository {
    List<Obstacle> findAll() throws IOException;

    public Obstacle findByName(String name);

    public void createORupdate(Obstacle obstacle);
}