package org.calma.pig.exercices.laboSpacial.models.obstacle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.geometry.Point2D;
import org.calma.pig.exercices.laboSpacial.models.cell.Cell;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Obstacle extends AbstractObstacle {
    private Point2D pointDepart;
    private SimpleIntegerProperty pointDepartX;
    private SimpleIntegerProperty pointDepartY;
    private SimpleListProperty<Cell> geometry;
    private SimpleObjectProperty<Cell> realPosition;

    public Obstacle(){
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

    public Obstacle(String name, String description, List<Cell> geometry, Cell realPosition) {
        this.init();

        this.setName(name);
        this.setDescription(description);
        this.setGeometry(geometry);
        this.setRealPosition(realPosition);
    }

    private void init(){

        this.pointDepart = new Point2D(0,0);
        this.pointDepartX = new SimpleIntegerProperty();
        this.pointDepartY = new SimpleIntegerProperty();

        this.geometry = new SimpleListProperty<Cell>(FXCollections.observableArrayList());

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

    public List<Cell> getGeometry() {
        return geometry.getValue();
    }

    public SimpleListProperty<Cell> getGeometryProperty() {
        return geometry;
    }

    public void addGeometry(Cell cellPosition){
        this.geometry.getValue().add(cellPosition);
    }

    public void setGeometry(List<Cell> geometry) {
        this.geometry.set(FXCollections.observableList(geometry));
    }

    public Cell getRealPosition() {
        return this.realPosition.getValue();
    }

    public void setRealPosition(Cell realPosition) {
        this.realPosition.set(realPosition);
    }

    @Override
    public String toString() {
        return "Obstacle{" +
                super.toString() +
                "} " ;
    }
}
