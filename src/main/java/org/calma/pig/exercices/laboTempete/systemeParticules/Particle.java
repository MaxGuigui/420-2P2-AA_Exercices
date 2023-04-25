package org.calma.pig.exercices.laboTempete.systemeParticules;

import javafx.scene.Node;

public class Particle extends Sprite {
    public Particle(Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super( location, velocity, acceleration, width, height);
    }

    @Override
    public Node createView() {
        return null;
    }

    public void decreaseLifeSpan() {
        this.lifeSpan--;
    }
}