package org.calma.pig.exercices.laboTempete;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import static org.calma.pig.exercices.laboTempete.Constantes.IMG_SNOW_FLAKE_BLUE;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = creationSceneSimon();
        stage.setTitle("Tempête");
        stage.setScene(scene);
        stage.getIcons().add(new Image(IMG_SNOW_FLAKE_BLUE));
        stage.setMinWidth(960);
        stage.setMinHeight(540);
        stage.show();
    }

    public Scene creationSceneSimon(){

        Parent root = null;
        try {
            root = (StackPane) FXMLLoader.load(getClass().getResource("Tempete.fxml"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Scene(root, 960,	540);
    }

}
