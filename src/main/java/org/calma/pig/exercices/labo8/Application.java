package org.calma.pig.exercices.labo8;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = creationSceneFormulaire();
        stage.setTitle("Formulaire");
        stage.setScene(scene);
        stage.show();
    }

    public Scene creationSceneFormulaire(){

        Parent root = null;
        try {
            root = (VBox) FXMLLoader.load(getClass().getResource("Formulaire.fxml"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Scene(root, 640, 480);
    }

}
