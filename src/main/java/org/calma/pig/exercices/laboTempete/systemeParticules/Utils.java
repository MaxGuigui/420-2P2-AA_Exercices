package org.calma.pig.exercices.laboTempete.systemeParticules;

import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Utils {
    /**
     * Clamp value between min and max
     */
    public static double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        }

        if (value > max) {
            return max;
        }

        return value;
    }

    /**
     * Map value of a given range to a target range
     * @param value
     * @param currentRangeStart
     * @param currentRangeStop
     * @param targetRangeStart
     * @param targetRangeStop
     * @return
     */
    public static double map(double value, double currentRangeStart, double currentRangeStop, double targetRangeStart, double targetRangeStop) {
        return targetRangeStart + (targetRangeStop - targetRangeStart) * ((value - currentRangeStart) / (currentRangeStop - currentRangeStart));
    }

    /**
     * Snapshot an image out of a node, consider transparency.
     */
    public static Image createImage(Node node) {
        WritableImage wi;

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);

        int imageWidth = (int) node.getBoundsInLocal().getWidth();
        int imageHeight = (int) node.getBoundsInLocal().getHeight();

        wi = new WritableImage(imageWidth, imageHeight);

        node.snapshot(parameters, wi);

        return wi;
    }

    public static Image[] preCreateImages() {
        int count = (int) Settings.get().getParticleLifeSpanMax();

        // Gradient linéaire:  lifespan 0 -> lifespan max
        double width = count;
        Stop[] stops = new Stop[] {new Stop(0, Color.GRAY), new Stop(0.5, Color.WHITE)};
        LinearGradient linearGradient = new LinearGradient(0, 0, width, 0, false, CycleMethod.REPEAT, stops);

        Rectangle rectangle = new Rectangle(width,1);
        rectangle.setFill( linearGradient);

        Image lookupImage = createImage(rectangle);

        Image[] list = new Image[count];

        double radius = Settings.get().getParticleWidth();

        for (int i = 0; i < count; i++) {

            // Couleur en fonction de la durée de vie
            Color color = lookupImage.getPixelReader().getColor( i, 0);

            // Gradient image avec la couleur
            Circle ball = new Circle(radius);

            RadialGradient gradient1 = new RadialGradient(0, 0, 0, 0, radius, false, CycleMethod.NO_CYCLE, new Stop(0, color.deriveColor(1, 1, 1, 1)), new Stop(1, color.deriveColor(1, 1, 1, 0)));

            ball.setFill(gradient1);

            list[i] = Utils.createImage(ball);
        }

        return list;
    }
}