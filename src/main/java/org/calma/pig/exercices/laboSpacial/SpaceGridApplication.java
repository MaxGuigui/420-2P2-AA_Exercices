package org.calma.pig.exercices.laboSpacial;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.kurobako.gesturefx.GesturePane;
import org.calma.pig.exercices.laboSpacial.repositories.obstacle.IObstacleRepository;
import org.calma.pig.exercices.laboSpacial.repositories.obstacle.InMemoryObstacleRepository;

import java.io.IOException;

public class SpaceGridApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        IObstacleRepository emplacementRepository = new InMemoryObstacleRepository();

        SpaceGrid grilleCanvas = null;
        try {
            grilleCanvas = new SpaceGrid(emplacementRepository, 200, 200);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(grilleCanvas);

        StackPane.setAlignment(grilleCanvas, Pos.CENTER);

        GesturePane gp = new GesturePane(stackPane);

        BorderPane root = new BorderPane(gp);

        Scene scene = new Scene(root, 960, 540);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.setTitle("Planification spacial !");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

