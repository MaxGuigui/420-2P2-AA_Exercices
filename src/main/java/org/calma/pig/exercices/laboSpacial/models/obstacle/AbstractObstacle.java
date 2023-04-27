package org.calma.pig.exercices.laboSpacial.models.obstacle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractObstacle {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private ObjectProperty<Color> color;

    public AbstractObstacle() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.color = new SimpleObjectProperty<Color>();
    }

    public AbstractObstacle(int id, String name, String description, Color color) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.color = new SimpleObjectProperty<Color>(color);
    }

    public int getId() {
        return id.getValue();
    }

    public SimpleIntegerProperty getIdProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.getValue();
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty getDescriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> getColorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    @Override
    public String toString() {
        return "AbstractObstacle{" +
                "id=" + id.getValue() +
                ", name=" + name.getValue() +
                ", description=" + description.getValue() +
                ", color=" + color.getValue() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        AbstractObstacle that = (AbstractObstacle) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
