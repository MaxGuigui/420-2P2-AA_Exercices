package org.calma.pig.exercices.laboCollegeGrid.models.emplacement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.calma.pig.exercices.laboCollegeGrid.models.AbstractObject;
import org.calma.pig.exercices.laboCollegeGrid.models.cell.Cell;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Emplacement extends AbstractObject {
    private SimpleStringProperty description;

    private ObjectProperty<EmplacementType> type;
    private ObjectProperty<EmplacementStatus> status;

    private ObjectProperty<Color> color;

    private SimpleDoubleProperty rotation;

    private Point2D pointDepart;
    private SimpleIntegerProperty pointDepartX;
    private SimpleIntegerProperty pointDepartY;

    private SimpleStringProperty imageHaut;
    private SimpleStringProperty imageBas;
    private SimpleStringProperty imageGauche;
    private SimpleStringProperty imageDroite;

    private SimpleStringProperty imagePointEntree;

    private SimpleListProperty<Cell> geometry;
    private SimpleListProperty<Cell> entryPoints;

    private SimpleObjectProperty<Cell> realPosition;

    public Emplacement(){
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

    public Emplacement(EmplacementType emplacementType, String name, String description, List<Cell> geometry, List<Cell> entryPoints, Cell realPosition) {
        this.init();

        this.setType(emplacementType);
        this.setName(name);
        this.setDescription(description);
        this.setGeometry(geometry);
        this.setEntryPoints(entryPoints);
        this.setRealPosition(realPosition);
    }

    private void init(){
        this.description = new SimpleStringProperty();

        this.type = new SimpleObjectProperty<EmplacementType>();
        this.status = new SimpleObjectProperty<EmplacementStatus>();

        this.color = new SimpleObjectProperty<Color>();

        this.rotation = new SimpleDoubleProperty();

        this.pointDepart = new Point2D(0,0);
        this.pointDepartX = new SimpleIntegerProperty();
        this.pointDepartY = new SimpleIntegerProperty();

        this.imageHaut = new SimpleStringProperty();
        this.imageBas = new SimpleStringProperty();
        this.imageDroite = new SimpleStringProperty();
        this.imageGauche = new SimpleStringProperty();
        this.imagePointEntree = new SimpleStringProperty();

        this.geometry = new SimpleListProperty<Cell>(FXCollections.observableArrayList());
        this.entryPoints = new SimpleListProperty<Cell>(FXCollections.observableArrayList());

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

    public EmplacementType getType() {
        return type.getValue();
    }

    public void setType(EmplacementType type) {
        this.type.set(type);
    }

    public ObjectProperty<EmplacementType> getTypeProperty() {
        return type;
    }

    public EmplacementStatus getStatus() {
        return status.getValue();
    }

    public void setStatus(EmplacementStatus status) {
        this.status.set(status);
    }

    public ObjectProperty<EmplacementStatus> getStatusProperty() {
        return status;
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

    public Double getRotation() {
        return rotation.getValue();
    }

    public SimpleDoubleProperty getRotationProperty() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation.set(rotation);
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

    public String getImageHaut() {
        return imageHaut.getValue();
    }

    public SimpleStringProperty getImageHautProperty(){
        return imageHaut;
    }

    public void setImageHaut(String imageHaut) {
        this.imageHaut.set(imageHaut);
    }

    public String getImageBas() {
        return imageBas.getValue();
    }

    public SimpleStringProperty getImageBasProperty(){
        return imageBas;
    }

    public void setImageBas(String imageBas) {
        this.imageBas.set(imageBas);
    }

    public String getImageGauche() {
        return imageGauche.getValue();
    }

    public SimpleStringProperty getImageGaucheProperty(){
        return imageGauche;
    }

    public void setImageGauche(String imageGauche) {
        this.imageGauche.set(imageGauche);
    }

    public String getImageDroite() {
        return imageDroite.getValue();
    }

    public SimpleStringProperty getImageDroiteProperty(){
        return imageDroite;
    }

    public void setImageDroite(String imageDroite) {
        this.imageDroite.set(imageDroite);
    }

    public String getImagePointEntree() {
        return imagePointEntree.getValue();
    }

    public SimpleStringProperty getImagePointEntreeProperty(){
        return imagePointEntree;
    }

    public void setImagePointEntree(String imagePointEntree) {
        this.imagePointEntree.set(imagePointEntree);
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

    public List<Cell> getEntryPoints() {
        return this.entryPoints.getValue();
    }

    public SimpleListProperty<Cell> getEntryPointsProperty() {
        return this.entryPoints;
    }

    public void setEntryPoints(List<Cell> entryPoints) {
        this.entryPoints.set(FXCollections.observableList(entryPoints));
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
                ", type=" + type.getValue() +
                ", status=" + status.getValue() +
                ", color=" + color.getValue() +
                ", " + super.toString() +
                "} " ;
    }
}
