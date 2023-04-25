package org.calma.pig.exercices.laboTempete;

import javafx.animation.*;
import javafx.beans.value.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.calma.pig.exercices.laboTempete.systemeParticules.*;
import org.calma.pig.utils.BackgroundUtils;

import java.util.*;

import static org.calma.pig.exercices.laboTempete.Constantes.*;

public class TempeteController {
    @FXML
    private AnchorPane applie;
    @FXML
    private Rectangle filtre;
    @FXML
    private GridPane fond;
    @FXML
    private ImageView ivCloud;
    @FXML
    private ImageView ivSnowflake;
    @FXML
    private ImageView ivSun;
    @FXML
    private AnchorPane menu;
    @FXML
    private Pane paneCloud;
    @FXML
    private Pane paneFPS;
    @FXML
    private Pane paneSnowflake;
    @FXML
    private Pane paneSun;
    @FXML
    private StackPane root;
    @FXML
    private Slider slider;
    @FXML
    private Text textFPS;

    private static Random random = new Random();
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private List<Particle> particles;
    private Image[] imagesParticules;
    private AnimationTimer animationTimer;


    @FXML
    void initialize(){
        initializeBackground();
        initializeSlider();
        initializeFiltre();

        initializeSystemParticule();

    }

    private void initializeBackground() {
        Image fondEcran = new Image(FOND_ECRAN);
        Image imgSnowflake = new Image(IMG_SNOW_FLAKE_BLUE);

        BackgroundSize bgS = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage bgFondEcran = new BackgroundImage(fondEcran, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgS);

        for (int i = 0; i < 100; i++) {
            for (int j = i % 2; j < 100; j += 2) {

                ImageView iv = new ImageView(imgSnowflake);
                iv.setFitHeight(20);
                iv.setFitWidth(20);

                fond.add(iv, j, i);
            }
        }

        applie.setPrefSize(960, 540);
        applie.setBackground(new Background(bgFondEcran));

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            applie.setPrefWidth(root.getWidth());
        });
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            applie.setPrefHeight(root.getHeight());
        });
    }
    private void initializeSlider(){
        BackgroundUtils bgUtils = new BackgroundUtils();
        bgUtils.setCornerRadii(15.0);
        Background bgBlack = bgUtils.createBackground();

        PauseTransition delay = new PauseTransition(Duration.millis(2));
        delay.setOnFinished(event -> {

            menu.setLayoutX((root.getWidth() / 2) - menu.getWidth() / 2);
        });
        delay.play();

        paneSun.setBackground(bgBlack);
        paneCloud.setBackground(bgBlack);
        paneSnowflake.setBackground(bgBlack);

        paneFPS.setBackground(bgBlack);

        ivSun.setImage(new Image(IMG_SUN));
        ivCloud.setImage(new Image(IMG_CLOUD));
        ivSnowflake.setImage(new Image(IMG_SNOW_FLAKE_WHITE));

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            menu.setLayoutX((root.getPrefWidth() /2) - menu.getWidth() /2);
        });

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {

            Double value = newValue.doubleValue();

            changeOpacityFond(value);
            changeOpacityFiltre(value/2);

            if(value > 0.5){
                Random r = new Random();
                Double low = -value;
                Double high = value;
                Double randomGravity = r.nextDouble(high-low) + low;

                Settings.get().setParticleMaxSpeed(value*10);
                Settings.get().setForceGravity(new Vector2D(randomGravity,1));
            }
            else{
                Settings.get().setParticleMaxSpeed(4);
                Settings.get().setForceGravity(new Vector2D(0,1));
            }

            Settings.get().setEmitterFrequency((int)(value*5));

        });

    }
    private void initializeFiltre(){

        filtre.setMouseTransparent(true);
        filtre.setLayoutX(0);
        filtre.setLayoutY(0);

        filtre.setFill(Color.color(0.66,0.65,0.65,0.0));

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            filtre.setWidth(root.getWidth());
        });
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            filtre.setHeight(root.getHeight());
        });
    }

    private void initializeSystemParticule(){
        particles = new ArrayList<>();

        canvas = new Canvas(Settings.get().getCanvasWidth(), Settings.get().getCanvasHeight());
        canvas.setMouseTransparent(true);
        applie.getChildren().addAll(canvas);

        graphicsContext = canvas.getGraphicsContext2D();

//        root.widthProperty().addListener((observable, oldValue, newValue) -> {
////            Settings.get().setCanvasWidth(applie.getWidth());
//            canvas.setWidth(applie.getWidth());
//        });
//        root.heightProperty().addListener((observable, oldValue, newValue) -> {
////            Settings.get().setCanvasHeight(applie.getHeight());
//            canvas.setHeight(applie.getHeight());
//        });

//        canvas.widthProperty().bind(applie.widthProperty());
//        canvas.heightProperty().bind(applie.heightProperty());

        this.preCreateImages();

        Settings.get().particleWidthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                preCreateImages();
            }
        });

        // Démarre le système de particule
        this.startAnimation();
    }

    private void changeOpacityFond(Double op) {
        BackgroundUtils bgU = new BackgroundUtils();
        bgU.setPaint(Color.color(0.0,0.0,0.0,op));
        fond.setBackground(bgU.createBackground());
    }
    private void changeOpacityFiltre(Double op) {
        filtre.setFill(Color.color(0.66,0.65,0.65,op));
    }


    private void preCreateImages() {
        this.imagesParticules = Utils.preCreateImages();
    }

    private void addParticle() {
        // Random position
        double x = Settings.get().getCanvasWidth() / 2 + random.nextDouble() * Settings.get().getEmitterWidth() - Settings.get().getEmitterWidth() / 2;
        double y = Settings.get().getEmitterLocationY();
//        double x = root.getWidth() et faire random;
//        double y = root.getHeight();

        // Grosseur
        double width = Settings.get().getParticleWidth();
        double height = Settings.get().getParticleHeight();

        // Location
        Vector2D location = new Vector2D(x, y);

        double vx = random.nextGaussian() * 0.3;
        double vy = random.nextGaussian() * 0.3 - 1.0;
        Vector2D velocity = new Vector2D(vx, vy);

        Vector2D acceleration = new Vector2D(0, 0);

        Particle sprite = new Particle(location, velocity, acceleration, width, height);

        particles.add(sprite);
    }

    private void removeDeadParticles() {
        Iterator<Particle> iter = particles.iterator();
        while (iter.hasNext()) {
            Particle particle = iter.next();
            if (particle.isDead()) {
                iter.remove();
            }
        }
    }

    private void startAnimation() {
        // start game
        animationTimer = new AnimationTimer() {

            TempeteController.FpsCounter fpsCounter = new TempeteController.FpsCounter();

            @Override
            public void handle(long now) {
                // Update la valeur du FPS
                fpsCounter.update(now);

                // Ajoute une ou des particules
                for (int i = 0; i < Settings.get().getEmitterFrequency(); i++) {
                    addParticle();
                }

                // Applique la force de gravité aux particules
                Vector2D forceGravity = Settings.get().getForceGravity();
                particles.forEach(particle -> {
                    particle.applyForce(forceGravity);
                });

                // À partir des forces appliquer précédemment, on déplace la particule en fonction de la direction, gravité et de la vélocité
                particles.stream().parallel().forEach(Sprite::move);

                // Affiche les particules à l'écran
                graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                graphicsContext.setFill(Color.TRANSPARENT);
//                graphicsContext.setFill(Color.color(0,0,0,0.8));
                graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                double particleSizeHalf = Settings.get().getParticleWidth() / 2;

                particles.stream().forEach(particle -> {
                    Image img = imagesParticules[particle.getLifeSpan()];

                    // Une particule basé sur une image
                    graphicsContext.drawImage(img, particle.getLocation().x - particleSizeHalf, particle.getLocation().y - particleSizeHalf);

//                    graphicsContext.fillRect();
//                    graphicsContext.setFill(Color.WHITE);

                    // Une couleur aléatoire comprise entre deux valeurs pourrait être une bonne idée !
//                    graphicsContext.setFill(Color.rgb(255, 255, 255, random.nextDouble()));

                    // Au lieu d'un rond on pourrait utiliser une forme générée aléatoirement
//                    graphicsContext.strokeOval(particle.getLocation().x - particleSizeHalf, particle.getLocation().y - particleSizeHalf, random.nextDouble() * 3, random.nextDouble() * 3);
//                    graphicsContext.fillOval(particle.getLocation().x - particleSizeHalf, particle.getLocation().y - particleSizeHalf, random.nextDouble() * 3, random.nextDouble() * 3);

//                    graphicsContext.strokeArc(particle.getLocation().x - particleSizeHalf, particle.getLocation().y - particleSizeHalf, particleSizeHalf * 2, particleSizeHalf * 2, 90, 10, ArcType.OPEN);
//                    graphicsContext.fillArc(particle.getLocation().x - particleSizeHalf, particle.getLocation().y - particleSizeHalf, particleSizeHalf * 2, particleSizeHalf * 2, 90, 10, ArcType.OPEN);
                });

                // On avance la durée de vie des particules
                particles.parallelStream().forEach(Sprite::decreaseLifeSpan);

                // On supprime les particules mortes
                removeDeadParticles();

                // show number of particles
                textFPS.setText(String.valueOf(Math.round(fpsCounter.getFrameRate())));
            }
        };

        animationTimer.start();
    }

    /**
     * Helper class for frame rate calculation
     */
    private static class FpsCounter {
        final long[]    frameTimes      = new long[100];
        int             frameTimeIndex  = 0;
        boolean         arrayFilled     = false;
        double          decimalsFactor  = 1000; // we want 3 decimals
        double          frameRate;

        public void update(long now) {
            long oldFrameTime = frameTimes[frameTimeIndex];

            frameTimes[frameTimeIndex] = now;

            frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;

            if (frameTimeIndex == 0) {
                arrayFilled = true;
            }

            if (arrayFilled) {
                long elapsedNanos = now - oldFrameTime;

                long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;

                frameRate = 1_000_000_000.0 / elapsedNanosPerFrame;
            }
        }

        public double getFrameRate() {
            return ((int) (frameRate * decimalsFactor)) / decimalsFactor; // reduce to n decimals
        }
    }
}

