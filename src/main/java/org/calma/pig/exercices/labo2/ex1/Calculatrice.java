package org.calma.pig.exercices.labo2.ex1;

import org.calma.pig.utils.BackgroundUtils;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import java.util.*;

public class Calculatrice extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = creationScene();
        stage.setTitle("Calculatrice");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public Scene creationScene(){

        VBox calculette = new VBox();
        HBox ligneInput1 = new HBox();
        HBox ligneInput2 = new HBox();
        HBox ligneInput3 = new HBox();
        GridPane boutons = new GridPane();

        Label labelInput1 = new Label("Input 1 :");
        Label labelInput2 = new Label("Input 2 :");
        Label labelInput3 = new Label("Result :");

        TextField input1 = new TextField();
        TextField input2 = new TextField();
        TextField input3 = new TextField();

        ligneInput1.getChildren().add(labelInput1);
        ligneInput1.getChildren().add(input1);

        ligneInput2.getChildren().add(labelInput2);
        ligneInput2.getChildren().add(input2);

        ligneInput3.getChildren().add(labelInput3);
        ligneInput3.getChildren().add(input3);

        char[] listChar = new char[]{'1','2','3','4','5','6','7','8','9','0','C',',','=','%','-','+'};
        List<Button> listButtons = new ArrayList<>();

        for (int i = 0; i < listChar.length; i++) {
            listButtons.add(new Button(Character.toString(listChar[i])));
            listButtons.get(i).setMinWidth(40);
            listButtons.get(i).setMinHeight(40);
        }

        boutons.add(listButtons.get(6),0,0);
        boutons.add(listButtons.get(7),1,0);
        boutons.add(listButtons.get(8),2,0);
        boutons.add(listButtons.get(15),3,0);

        boutons.add(listButtons.get(3),0,1);
        boutons.add(listButtons.get(4),1,1);
        boutons.add(listButtons.get(5),2,1);
        boutons.add(listButtons.get(14),3,1);

        boutons.add(listButtons.get(0),0,2);
        boutons.add(listButtons.get(1),1,2);
        boutons.add(listButtons.get(2),2,2);
        boutons.add(listButtons.get(13),3,2);

        boutons.add(listButtons.get(10),0,3);
        boutons.add(listButtons.get(9),1,3);
        boutons.add(listButtons.get(11),2,3);
        boutons.add(listButtons.get(12),3,3);

        calculette.setPadding(new Insets(20));
        boutons.setHgap(10);
        boutons.setVgap(20);

//        Border b = new Border();

        Paint cyan = Color.rgb(59,183,255,1);
        Paint blue = Color.rgb(0,140,255,1);
        BackgroundUtils bgUtils = new BackgroundUtils();
        bgUtils.setPaint(cyan);
        boutons.setBackground(bgUtils.createBackground());

        calculette.getChildren().add(ligneInput1);
        calculette.getChildren().add(ligneInput2);
        calculette.getChildren().add(ligneInput3);
        calculette.getChildren().add(boutons);

        return new Scene(calculette, 300, 400);
    }
}
