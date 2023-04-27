package org.calma.pig.exercices.laboSpacial.models.obstacle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import org.calma.pig.exercices.laboSpacial.models.cell.Cell;

public class CircularObstacle extends Obstacle{
    private Point2D pointDepart;
    private SimpleIntegerProperty pointDepartX;
    private SimpleIntegerProperty pointDepartY;
    private SimpleIntegerProperty diametre;
    private SimpleObjectProperty<Cell> realPosition;

    public CircularObstacle(){
        this.init();

        this.pointDepartX.addListener((observable, oldValue, newValue) -> {
            this.pointDepart = new Point2D(newValue.doubleValue(), this.pointDepart.getY());

            System.out.println(this.pointDepart);
        });

        this.pointDepartY.addListener((observable, oldValue, newValue) -> {
            this.pointDepart = new Point2D(this.pointDepart.getX(), newValue.doubleValue());

            System.out.println(this.pointDepart);
        });
    }

    public CircularObstacle(String name, String description, Integer diametre, Cell realPosition) {
        this.init();

        this.setName(name);
        this.setDescription(description);
        this.setDiametre(diametre*8);
        this.setRealPosition(realPosition);
    }

    private void init(){

        this.pointDepart = new Point2D(0,0);
        this.pointDepartX = new SimpleIntegerProperty();
        this.pointDepartY = new SimpleIntegerProperty();

        this.diametre = new SimpleIntegerProperty();

        this.realPosition = new SimpleObjectProperty<Cell>();
    }

    public Point2D getPointDepart() {
        return pointDepart;
    }
    public void setPointDepart(Point2D pointDepart) {
        this.pointDepart = pointDepart;
        this.pointDepartX.set((int)pointDepart.getX());
        this.pointDepartY.set((int)pointDepart.getY());
    }

    public SimpleIntegerProperty getPointDepartXProperty(){
        return this.pointDepartX;
    }
    public SimpleIntegerProperty getPointDepartYProperty(){
        return this.pointDepartY;
    }

    public int getDiametre() {
        return diametre.get();
    }
    public SimpleIntegerProperty diametreProperty() {
        return diametre;
    }
    public void setDiametre(int diametre) {
        this.diametre.set(diametre);
    }

    public Cell getRealPosition() {
        return this.realPosition.getValue();
    }
    public void setRealPosition(Cell realPosition) {
        this.realPosition.set(realPosition);
    }

    @Override
    public String toString() {
        return "CircularObstacle{" +
                super.toString() +
                "} " ;
    }
}
