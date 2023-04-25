package org.calma.pig.exercices.laboTempete.systemeParticules;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Settings {

    // Scene
    private DoubleProperty canvasWidth = new SimpleDoubleProperty(960);
    private DoubleProperty canvasHeight = new SimpleDoubleProperty(540);

    // Forces
    private ObjectProperty<Vector2D> forceGravity = new SimpleObjectProperty<>(new Vector2D(0, 1));
    private DoubleProperty gravityX = new SimpleDoubleProperty(forceGravity.getValue().x);
    private DoubleProperty gravityY = new SimpleDoubleProperty(forceGravity.getValue().y);

    // Émetteur
    private IntegerProperty emitterFrequency = new SimpleIntegerProperty(0);
    private DoubleProperty emitterWidth = new SimpleDoubleProperty(canvasWidth.doubleValue());
    private DoubleProperty emitterLocationY = new SimpleDoubleProperty(0);

    // Particules
    private DoubleProperty particleWidth = new SimpleDoubleProperty(5);
    private DoubleProperty particleHeight = new SimpleDoubleProperty(particleWidth.doubleValue());
    private DoubleProperty particleLifeSpanMax = new SimpleDoubleProperty(256);
    private DoubleProperty particleMaxSpeed = new SimpleDoubleProperty(4);

    // Singleton : Design Pattern!! https://en.wikipedia.org/wiki/Singleton_pattern
    private static Settings settings = new Settings();

    public static Settings get() {
        return settings;
    }

    public final ObjectProperty<Vector2D> forceGravityProperty() {
        return this.forceGravity;
    }

    public final Vector2D getForceGravity() {
        return this.forceGravityProperty().get();
    }

    public final void setForceGravity(final Vector2D forceGravity) {
        this.forceGravityProperty().set(forceGravity);
    }

    public final IntegerProperty emitterFrequencyProperty() {
        return this.emitterFrequency;
    }

    public final int getEmitterFrequency() {
        return this.emitterFrequencyProperty().get();
    }

    public final void setEmitterFrequency(final int emitterFrequency) {
        this.emitterFrequencyProperty().set(emitterFrequency);
    }

    public final DoubleProperty emitterWidthProperty() {
        return this.emitterWidth;
    }

    public final double getEmitterWidth() {
        return this.emitterWidthProperty().get();
    }

    public final void setEmitterWidth(final double emitterWidth) {
        this.emitterWidthProperty().set(emitterWidth);
    }

    public final DoubleProperty emitterLocationYProperty() {
        return this.emitterLocationY;
    }

    public final double getEmitterLocationY() {
        return this.emitterLocationYProperty().get();
    }

    public final void setEmitterLocationY(final double emitterLocationY) {
        this.emitterLocationYProperty().set(emitterLocationY);
    }

    public final DoubleProperty particleWidthProperty() {
        return this.particleWidth;
    }

    public final double getParticleWidth() {
        return this.particleWidthProperty().get();
    }

    public final void setParticleWidth(final double particleWidth) {
        this.particleWidthProperty().set(particleWidth);
    }

    public final DoubleProperty particleHeightProperty() {
        return this.particleHeight;
    }

    public final double getParticleHeight() {
        return this.particleHeightProperty().get();
    }

    public final void setParticleHeight(final double particleHeight) {
        this.particleHeightProperty().set(particleHeight);
    }

    public final DoubleProperty particleLifeSpanMaxProperty() {
        return this.particleLifeSpanMax;
    }

    public final double getParticleLifeSpanMax() {
        return this.particleLifeSpanMaxProperty().get();
    }

    public final void setParticleLifeSpanMax(final double particleLifeSpanMax) {
        this.particleLifeSpanMaxProperty().set(particleLifeSpanMax);
    }

    public final DoubleProperty particleMaxSpeedProperty() {
        return this.particleMaxSpeed;
    }

    public final double getParticleMaxSpeed() {
        return this.particleMaxSpeedProperty().get();
    }

    public final void setParticleMaxSpeed(final double particleMaxSpeed) {
        this.particleMaxSpeedProperty().set(particleMaxSpeed);
    }

    public final DoubleProperty canvasWidthProperty() {
        return this.canvasWidth;
    }

    public final double getCanvasWidth() {
        return this.canvasWidthProperty().get();
    }

    public final void setCanvasWidth(final double canvasWidth) {
        this.canvasWidthProperty().set(canvasWidth);
    }

    public final DoubleProperty canvasHeightProperty() {
        return this.canvasHeight;
    }

    public final double getCanvasHeight() {
        return this.canvasHeightProperty().get();
    }

    public final void setCanvasHeight(final double canvasHeight) {
        this.canvasHeightProperty().set(canvasHeight);
    }

}