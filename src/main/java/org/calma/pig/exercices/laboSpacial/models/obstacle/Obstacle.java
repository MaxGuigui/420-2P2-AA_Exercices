package org.calma.pig.exercices.laboSpacial.models.obstacle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.calma.pig.exercices.laboSpacial.models.AbstractObject;
import org.calma.pig.exercices.laboSpacial.models.cell.Cell;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Obstacle extends AbstractObject {
    private SimpleStringProperty description;
    private ObjectProperty<Color> color;
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
        this.description = new SimpleStringProperty();

        this.color = new SimpleObjectProperty<Color>();

        this.pointDepart = new Point2D(0,0);
        this.pointDepartX = new SimpleIntegerProperty();
        this.pointDepartY = new SimpleIntegerProperty();

        this.geometry = new SimpleListProperty<Cell>(FXCollections.observableArrayList());

        this.realPosition = new SimpleObjectProperty<Cell>();
    }

    public String getDescription() {
        return description.getValue();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public SimpleStringProperty getDescriptionProperty() {
        return description;
    }

    public Color getColor() {
        return color.getValue();
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public ObjectProperty<Color> getColorProperty() {
        return color;
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
                "description=" + description.getValue() +
                ", color=" + color.getValue() +
                ", " + super.toString() +
                "} " ;
    }
}
