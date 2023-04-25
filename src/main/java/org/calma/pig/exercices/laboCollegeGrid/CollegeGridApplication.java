package org.calma.pig.exercices.laboCollegeGrid;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.kurobako.gesturefx.GesturePane;
import org.calma.pig.exercices.laboCollegeGrid.repositories.emplacement.IEmplacementRepository;
import org.calma.pig.exercices.laboCollegeGrid.repositories.emplacement.InMemoryEmplacementRepository;

import java.io.IOException;

public class CollegeGridApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        IEmplacementRepository emplacementRepository = new InMemoryEmplacementRepository();

        CollegeGrid grilleCanvas = null;
        try {
            grilleCanvas = new CollegeGrid(emplacementRepository, 200, 200);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

//        ImageView imageView = new ImageView(new Image("/org/calma/dag/master/images/calma_p1_2.png"));

        StackPane stackPane = new StackPane();
        //stackPane.getChildren().add(imageView);
        stackPane.getChildren().add(grilleCanvas);

        //StackPane.setAlignment(imageView, Pos.TOP_LEFT);
        StackPane.setAlignment(grilleCanvas, Pos.TOP_LEFT);

        GesturePane gp = new GesturePane(stackPane);

        BorderPane root = new BorderPane(gp);

        Scene scene = new Scene(root, 960, 540);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.setTitle("College Grid");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

