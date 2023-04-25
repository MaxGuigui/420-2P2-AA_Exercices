package org.calma.pig.exercices.laboTempete.systemeParticules;

import javafx.scene.Node;
import javafx.scene.layout.Region;

public abstract class Sprite extends Region {
    Vector2D location;
    Vector2D velocity;
    Vector2D acceleration;

    double maxSpeed = Settings.get().getParticleMaxSpeed();
    double radius;

    Node view;

    double width;
    double height;
    double centerX;
    double centerY;

    double angle;

    double lifeSpanMax = Settings.get().getParticleLifeSpanMax() - 1; // -1 because we want [0..255] for an amount of 256; otherwise we'd get DivByZero in the logic; will fix it later
    double lifeSpan = lifeSpanMax;

    public Sprite( Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
        this.location = location;
        this.velocity = velocity;
        this.acceleration = acceleration;

        this.width = width;
        this.height = height;
        this.centerX = width / 2;
        this.centerY = height / 2;

        this.radius = width / 2;

        this.view = createView();

        setPrefSize(width, height);

        if( this.view != null) {
            getChildren().add( view);
        }

    }

    public abstract Node createView();

    public void applyForce(Vector2D force) {
        acceleration.add(force);
    }

    /**
     * Méthode de déplacement standard, on calcule la vélocité en fonction de la force d'accélération accumulé (moment), ensuite, on calcule la position.
     * Nettoyage à la fin, comme ça on est prêt pour le prochain frame/animation/affichage
     * https://en.wikipedia.org/wiki/Kinematics
     * https://en.wikipedia.org/wiki/Euclidean_vector
     */
    public void move() {
        // Applique la vélocité dépendamment de la vitesse
        velocity.add(acceleration);

        // Limite la vélocité à la vitesse maximum
        velocity.limit(maxSpeed);

        // Change la position dépendamment de la vélocité
        location.add(velocity);

        // Ajuste l'angle, i.e. vers la cible (destination)
        angle = velocity.angle();

        // Clean up
        acceleration.multiply(0);
    }

    /**
     * Update node position
     */
    public void display() {
        // Déplace le Sprite en utilise la fonction de la clase Node
        this.relocate(location.x - centerX, location.y - centerY);

        // rotation
        setRotate(Math.toDegrees(angle));
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Vector2D getLocation() {
        return location;
    }

    public void setLocation( double x, double y) {
        location.x = x;
        location.y = y;
    }

    public void setLocationOffset( double x, double y) {
        location.x += x;
        location.y += y;
    }

    public void decreaseLifeSpan() {
    }

    public boolean isDead() {
        if (lifeSpan <= 0.0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getLifeSpan() {
        return (int) lifeSpan;
    }
}