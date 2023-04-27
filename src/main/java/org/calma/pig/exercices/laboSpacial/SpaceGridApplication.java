package org.calma.pig.exercices.laboSpacial;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.kurobako.gesturefx.GesturePane;
import org.calma.pig.exercices.laboSpacial.repositories.obstacle.IObstacleRepository;
import org.calma.pig.exercices.laboSpacial.repositories.obstacle.InMemoryObstacleRepository;

import java.io.IOException;

public class SpaceGridApplication extends Application {
    private int rows;
    private int columns;

    @Override
    public void start(Stage primaryStage) {
        IObstacleRepository emplacementRepository = new InMemoryObstacleRepository();

        rows = 100;
        columns = 100;

        SpaceGrid grilleCanvas = null;
        try {
            grilleCanvas = new SpaceGrid(emplacementRepository, columns, rows);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        BorderPane root = initRoot(grilleCanvas, true);

        Scene scene = new Scene(root, 960, 540);
        primaryStage.getIcons().add(new Image("org/calma/pig/exercices/laboSpacial/images/logo_rocket.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.setTitle("Planification spacial !");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public BorderPane initRoot(SpaceGrid spaceGrid, boolean withGesture){
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(spaceGrid);

        StackPane.setAlignment(spaceGrid, Pos.CENTER);

        BorderPane root = null;
        if(withGesture){
            GesturePane gp = new GesturePane(stackPane);

            root = new BorderPane(gp);
        }
        else{
            root = new BorderPane(stackPane);
        }

        root.setPadding(new Insets(10.0));

        return root;
    }
}

