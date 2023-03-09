package org.calma.pig.exercices.labo2.ex1;

import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class ItemsInMemory {

    private List<Label> listLabel;
    private List<TextField> listTextField;
    private List<Button> listButton;

    public ItemsInMemory() {

        listLabel = new ArrayList<>();
        listTextField = new ArrayList<>();
        listButton = new ArrayList<>();

        listLabel.add(new Label("Input 1 :"));
        listLabel.add(new Label("Input 2 :"));
        listLabel.add(new Label("Input 3 :"));

        listTextField.add(new TextField());
        listTextField.add(new TextField());
        listTextField.add(new TextField());

        listButton.add(new Button("1"));
        listButton.add(new Button("2"));
        listButton.add(new Button("3"));
        listButton.add(new Button("4"));
        listButton.add(new Button("5"));
        listButton.add(new Button("6"));
        listButton.add(new Button("7"));
        listButton.add(new Button("8"));
        listButton.add(new Button("9"));
        listButton.add(new Button("0"));
        listButton.add(new Button("C"));
        listButton.add(new Button(","));
        listButton.add(new Button("="));
        listButton.add(new Button("%"));
        listButton.add(new Button("-"));
        listButton.add(new Button("+"));
    }

    public List<Label> getListLabel() {
        return listLabel;
    }

    public List<TextField> getListTextField() {
        return listTextField;
    }

    public List<Button> getListButton() {
        return listButton;
    }
}
