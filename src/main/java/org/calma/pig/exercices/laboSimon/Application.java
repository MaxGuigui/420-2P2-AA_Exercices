package org.calma.pig.exercices.laboSimon;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = creationSceneSimon();
        stage.setTitle("Simon");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public Scene creationSceneSimon(){

        Parent root = null;
        try {
            root = (AnchorPane) FXMLLoader.load(getClass().getResource("Simon.fxml"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Scene(root, 960,	540);
    }

}
