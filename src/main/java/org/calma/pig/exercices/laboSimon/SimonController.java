package org.calma.pig.exercices.laboSimon;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.calma.pig.utils.BackgroundUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.calma.pig.exercices.laboSimon.Constantes.*;

public class SimonController {
    @FXML
    private ListView<String> affichageEntree;
    @FXML
    private ListView<String> affichageValide;
    @FXML
    private AnchorPane anchorSlider;
    @FXML
    private AnchorPane root;
    @FXML
    private Slider slider;
    @FXML
    private Button btnDemarrer;
    @FXML
    private Button btnStop;
    @FXML
    private Button btnReessayer;
    @FXML
    private Button btnNextLevel;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnMenu1;
    @FXML
    private Button btnHaut;
    @FXML
    private Button btnHautDroit;
    @FXML
    private Button btnHautGauche;
    @FXML
    private Button btnDroit;
    @FXML
    private Button btnBas;
    @FXML
    private Button btnBasDroit;
    @FXML
    private Button btnBasGauche;
    @FXML
    private Button btnGauche;
    @FXML
    private CheckBox checkDebug;
    @FXML
    private Circle circle;
    @FXML
    private Circle circleGameOver;
    @FXML
    private Circle circleReussite;
    @FXML
    private Circle circleTemplate;
    @FXML
    private AnchorPane pageGameOver;
    @FXML
    private AnchorPane pageReussite;
    @FXML
    private Text textCoups;
    @FXML
    private Text textMode;

    private List<Button> listBtnSimon;
    private List<Button> listValide;
    private List<Button> listEntree;
    private Mode mode;
    private Boolean isDebugMode;
    private SoundPlayer soundPlayer;
    private Integer niveau;

    @FXML
    void initialize(){
        BackgroundUtils bgUtils = new BackgroundUtils();
        bgUtils.setCornerRadii(150.0);

//        688141
        bgUtils.setPaint(Color.color(0.4078, 0.5059, 0.2549));
        Background bgHaut = bgUtils.createBackground();

//        547A64
        bgUtils.setPaint(Color.color(0.3294, 0.4784, 0.3922));
        Background bgDroit = bgUtils.createBackground();

//        AED05F
        bgUtils.setPaint(Color.color(0.6824, 0.8157, 0.3725));
        Background bgBas = bgUtils.createBackground();

//        366956
        bgUtils.setPaint(Color.color(0.2118, 0.4118, 0.3373));
        Background bgGauche = bgUtils.createBackground();

//        1D3630
        bgUtils.setPaint(Color.color(0.1137, 0.2118, 0.1882));
        Background bgHautGauche = bgUtils.createBackground();

//        CEE18A
        bgUtils.setPaint(Color.color(0.8078, 0.8824, 0.5412));
        Background bgHautDroit = bgUtils.createBackground();

//        14452D
        bgUtils.setPaint(Color.color(0.0784, 0.2706, 0.1765));
        Background bgBasGauche = bgUtils.createBackground();

//        2E5930
        bgUtils.setPaint(Color.color(0.1804, 0.349, 0.1882));
        Background bgBasDroit = bgUtils.createBackground();

//        1C2C1D
        bgUtils.setPaint(Color.color(0.1098, 0.1725, 0.1137));
        Background bgPlay = bgUtils.createBackground();

        bgUtils.setPaint(Color.WHITE);
        Background bgWhite = bgUtils.createBackground();
        bgUtils.setPaint(Color.RED);
        Background bgRed = bgUtils.createBackground();
        bgUtils.setPaint(Color.GREEN);
        Background bgGreen = bgUtils.createBackground();

//        BACKGROUND BTN SIMON
        btnHaut.setBackground(bgHaut);
        btnDroit.setBackground(bgDroit);
        btnBas.setBackground(bgBas);
        btnGauche.setBackground(bgGauche);
        btnHautGauche.setBackground(bgHautGauche);
        btnHautDroit.setBackground(bgHautDroit);
        btnBasGauche.setBackground(bgBasGauche);
        btnBasDroit.setBackground(bgBasDroit);

//        BACKGROUND AUTRES
        btnDemarrer.setBackground(bgWhite);
        btnStop.setBackground(bgWhite);

        btnReessayer.setBackground(bgRed);
        btnMenu.setBackground(bgRed);
        btnNextLevel.setBackground(bgGreen);
        btnMenu1.setBackground(bgGreen);

//        SIZE BTN SIMON
        btnGauche.setPrefSize(100,100);
        btnHaut.setPrefSize(100,100);
        btnDroit.setPrefSize(100,100);
        btnBas.setPrefSize(100,100);
        btnHautGauche.setPrefSize(100,100);
        btnHautDroit.setPrefSize(100,100);
        btnBasGauche.setPrefSize(100,100);
        btnBasDroit.setPrefSize(100,100);

//        INIT BTN SIMON
        btnGauche.setVisible(false);
        btnGauche.setScaleX(0);
        btnGauche.setScaleY(0);

        btnHaut.setVisible(false);
        btnHaut.setScaleX(0);
        btnHaut.setScaleY(0);

        btnDroit.setVisible(false);
        btnDroit.setScaleX(0);
        btnDroit.setScaleY(0);

        btnBas.setVisible(false);
        btnBas.setScaleX(0);
        btnBas.setScaleY(0);

        btnHautGauche.setVisible(false);
        btnHautGauche.setScaleX(0);
        btnHautGauche.setScaleY(0);

        btnHautDroit.setVisible(false);
        btnHautDroit.setScaleX(0);
        btnHautDroit.setScaleY(0);

        btnBasGauche.setVisible(false);
        btnBasGauche.setScaleX(0);
        btnBasGauche.setScaleY(0);

        btnBasDroit.setVisible(false);
        btnBasDroit.setScaleX(0);
        btnBasDroit.setScaleY(0);

//        LISTE BTN SIMON
        listBtnSimon = new ArrayList<>();
        listBtnSimon.add(btnHaut);
        listBtnSimon.add(btnDroit);
        listBtnSimon.add(btnBas);
        listBtnSimon.add(btnGauche);

        btnDemarrer.setVisible(true);

        btnStop.setOpacity(0.0);
        btnStop.setVisible(false);
        btnStop.setDisable(true);

//        IMAGES BTN
        setImageInBtn(IMG_SWORD, btnHaut);
        setImageInBtn(IMG_BOW, btnDroit);
        setImageInBtn(IMG_SHIELD, btnBas);
        setImageInBtn(IMG_HAT, btnGauche);
        setImageInBtn(IMG_OCARINA, btnHautGauche);
        setImageInBtn(IMG_TRIFORCE, btnHautDroit);
        setImageInBtn(IMG_LEAF, btnBasGauche);
        setImageInBtn(IMG_WHIP, btnBasDroit);

        setImageInBtn(IMG_PLAY, btnDemarrer);
        setImageInBtn(IMG_STOP, btnStop);

        Image imgZelda = new Image(FOND_ECRAN);
        root.setBackground(new Background(new BackgroundImage(
                imgZelda,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(960,540,false,false,false,false))));

        soundPlayer = new SoundPlayer();
    }

    @FXML
    void onClickBtnDem(MouseEvent event) {
        animerBoutonOnClick(btnDemarrer);

        isDebugMode = checkDebug.isSelected();
        mode = getMode();

        animationIntro();

        PauseTransition pause = new PauseTransition(Duration.seconds(TIME_ANIM_INTO*2));
        pause.setOnFinished(e -> {
            demarrerJeu();
        });
        pause.play();
    }
    @FXML
    void onClickBtnStop(MouseEvent event) {
        animerBoutonOnClick(btnStop);

        animationStop();

        listBtnSimon.remove(btnHautGauche);
        listBtnSimon.remove(btnHautDroit);
        listBtnSimon.remove(btnBasGauche);
        listBtnSimon.remove(btnBasDroit);
    }
    @FXML
    void onClickBtnReessayer(MouseEvent event) {
        animerBoutonOnClick(btnReessayer);

        finGameOver();

        listBtnSimon.remove(btnHautGauche);
        listBtnSimon.remove(btnHautDroit);
        listBtnSimon.remove(btnBasGauche);
        listBtnSimon.remove(btnBasDroit);

        demarrerJeu();
    }
    @FXML
    void onClickBtnNextLevel(MouseEvent event) {
        animerBoutonOnClick(btnNextLevel);

        finReussite();

        if(mode == Mode.NORMAL){
            mode = Mode.PRO;

            listBtnSimon.remove(btnHautGauche);
            listBtnSimon.remove(btnHautDroit);
            listBtnSimon.remove(btnBasGauche);
            listBtnSimon.remove(btnBasDroit);
        }

        demarrerJeu();
    }
    @FXML
    void onClickBtnMenu(MouseEvent event) {
        Button btn = (Button) event.getSource();
        animerBoutonOnClick(btnMenu);

        if(btn.getId().equals("btnMenu")){
            finGameOver();
        }
        else{
            finReussite();
        }

        animationStop();

        listBtnSimon.remove(btnHautGauche);
        listBtnSimon.remove(btnHautDroit);
        listBtnSimon.remove(btnBasGauche);
        listBtnSimon.remove(btnBasDroit);
    }
    @FXML
    void onClickBtnSimon(MouseEvent event) {
        Button btn = (Button) event.getSource();
        animerBoutonOnClick(btn);
        jouerSonBtn(btn);

        listEntree.add(btn);

        if(isDebugMode){
            affichageEntree.getItems().clear();
            for (Iterator<Button> iterator = listEntree.iterator(); iterator.hasNext(); ) {
                Button next =  iterator.next();
                affichageEntree.getItems().add(next.getId());
                affichageValide.scrollTo(listEntree.size());
            }
        }

        if(listEntree.size() == listValide.size()){

            textCoups.setText("Niveau : " + niveau++);

            if(verifierListDuJeu()){
                if(listEntree.size() == 12){
                    reussite();
                }
                else{
                    if(listEntree.size() == 5 && mode == Mode.PRO){
                        activerModePro();
                    }

                    Button btnRand = getRandomBtn();
                    listValide.add(btnRand);
                    animerListValide();

                    listEntree.clear();

                    affichageValide.getItems().add(btnRand.getId());
                    affichageValide.scrollTo(listValide.size());
                }
            }
            else{
                gameOver();
            }
        }
        else{
            if(!verifierListDuJeu()) {
                gameOver();
            }
        }
    }

    @FXML
    void onMouseEnteredBtn(MouseEvent event) {
        Button btn =(Button)event.getSource();
        btn.setOpacity(ON_MOUSE_ENTERED_OPACITY);
    }
    @FXML
    void onMouseExitedBtn(MouseEvent event) {
        Button btn =(Button)event.getSource();
        btn.setOpacity(DEFAULT_OPACITY);
    }

    private void setImageInBtn(String src, Button btn){
        Image img = new Image(src);
        ImageView iV = new ImageView(img);
        iV.setFitWidth(50);
        iV.setFitHeight(50);
        btn.setGraphic(iV);
    }

    private void jouerSonBtn(Button btn){

        if(btn == btnHaut){
            jouerSon(SON_SWORD);
        }
        else if(btn == btnDroit){
            jouerSon(SON_BOW);
        }
        else if(btn == btnBas){
            jouerSon(SON_SHIELD);
        }
        else if(btn == btnGauche){
            jouerSon(SON_HAT);
        }
        else if(btn == btnHautGauche){
            soundPlayer.note_on(61);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            soundPlayer.note_off(61);
        }
        else if(btn == btnHautDroit){
            jouerSon(SON_TRIFORCE);
        }
        else if(btn == btnBasGauche){
            jouerSon(SON_LEAF);
        }
        else{
            jouerSon(SON_WHIP);
        }
    }

    private void jouerSon(String src){
        String mp3Path = getClass().getResource(src).toString();
        Media media = new Media(mp3Path);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
    }

    private ScaleTransition animerBoutonOnClick(Button btn, Double time, Boolean reverse){

        ScaleTransition trans = new ScaleTransition(Duration.seconds(time), btn);

        if(reverse){
            trans.setFromX(1.0);
            trans.setFromY(1.0);

            trans.setToX(1.4);
            trans.setToY(1.4);
        }
        else{
            trans.setFromX(1.0);
            trans.setFromY(1.0);

            trans.setToX(0.8);
            trans.setToY(0.8);
        }


        trans.setCycleCount(2);
        trans.setAutoReverse(true);
        trans.play();

        return trans;
    }
    private void animerBoutonOnClick(Button btn){
        animerBoutonOnClick(btn, TIME_ANIM_BTN, false);
    }

    private void animationIntro(){
//        ANIM CIRCLE
        TranslateTransition transTransCircle = new TranslateTransition(Duration.seconds(TIME_ANIM_INTO));
        transTransCircle.setFromY(circle.getCenterY());
        transTransCircle.setToY(-(root.getHeight()-circleTemplate.getLayoutY()));
        transTransCircle.setCycleCount(1);

        ScaleTransition scaleTransCircle1 = new ScaleTransition(Duration.seconds(TIME_ANIM_INTO/2));
        scaleTransCircle1.setFromY(0.5);
        scaleTransCircle1.setToY(1);
        scaleTransCircle1.setFromX(1);
        scaleTransCircle1.setToX(0.8);
        scaleTransCircle1.setCycleCount(1);

        ScaleTransition scaleTransCircle2 = new ScaleTransition(Duration.seconds(TIME_ANIM_INTO/2));
        scaleTransCircle2.setFromY(1);
        scaleTransCircle2.setToY(0.8);
        scaleTransCircle2.setCycleCount(1);

        SequentialTransition sequenTransCircle = new SequentialTransition();
        sequenTransCircle.setNode(circle);
        sequenTransCircle.getChildren().addAll(scaleTransCircle1, scaleTransCircle2);
        sequenTransCircle.setCycleCount(1);

        ParallelTransition paralTransCircle = new ParallelTransition();
        paralTransCircle.setNode(circle);
        paralTransCircle.getChildren().addAll(transTransCircle, sequenTransCircle);
        paralTransCircle.setCycleCount(1);

//        ANIM ANCHOR
        TranslateTransition transTransAnchor = new TranslateTransition(Duration.seconds(TIME_ANIM_INTO/2), anchorSlider);
        transTransAnchor.setFromY(anchorSlider.getTranslateY());
        transTransAnchor.setToY(anchorSlider.getTranslateY() + 500);
        transTransAnchor.setCycleCount(1);

//        ANIM BTN

        TranslateTransition transTransBtn = new TranslateTransition(Duration.seconds(TIME_ANIM_INTO/2), btnDemarrer);
        transTransBtn.setFromY(btnDemarrer.getTranslateY());
        transTransBtn.setToY(btnDemarrer.getTranslateY() - 500);
        transTransBtn.setCycleCount(1);

        paralTransCircle.setOnFinished(e->{
            for (Iterator<Button> iterator = listBtnSimon.iterator(); iterator.hasNext(); ) {
                Button next =  iterator.next();
                animationIntroBtn(next);
            }

            btnStop.setVisible(true);
            btnStop.setDisable(false);
            FadeTransition fadeTransBtnStop = new FadeTransition(Duration.seconds(TIME_ANIM_INTO), btnStop);
            fadeTransBtnStop.setFromValue(0.0);
            fadeTransBtnStop.setToValue(1);
            fadeTransBtnStop.setCycleCount(1);
            fadeTransBtnStop.play();
        });

//        JOUER ANIMS
        paralTransCircle.play();
        transTransAnchor.play();
        transTransBtn.play();


        checkDebug.setDisable(true);
        checkDebug.setVisible(false);
    }

    private void animationIntroBtn(Button btn){
        ScaleTransition scaleTransBtn = new ScaleTransition(Duration.seconds(TIME_ANIM_INTO));
        scaleTransBtn.setFromY(0);
        scaleTransBtn.setToY(1);
        scaleTransBtn.setFromX(0);
        scaleTransBtn.setToX(1);
        scaleTransBtn.setCycleCount(1);

        TranslateTransition transTransBtn = new TranslateTransition(Duration.seconds(TIME_ANIM_INTO));
        transTransBtn.setFromY(-btn.getHeight()/2);
        transTransBtn.setToY(0);
        transTransBtn.setCycleCount(1);

        ParallelTransition paralTransBtn = new ParallelTransition();
        paralTransBtn.setNode(btn);
        paralTransBtn.getChildren().addAll(scaleTransBtn, transTransBtn);
        paralTransBtn.setCycleCount(1);

        paralTransBtn.play();

        btn.setVisible(true);
        btn.setDisable(false);
    }

    private void animationStop(){
        for (Iterator<Button> iterator = listBtnSimon.iterator(); iterator.hasNext(); ) {
            Button next =  iterator.next();
            animationStopBtn(next);
        }

        FadeTransition fadeTransBtnStop = new FadeTransition(Duration.seconds(TIME_ANIM_INTO/4), btnStop);
        fadeTransBtnStop.setFromValue(1);
        fadeTransBtnStop.setToValue(0);
        fadeTransBtnStop.setCycleCount(1);
        fadeTransBtnStop.play();
        btnStop.setDisable(true);
        btnStop.setVisible(false);

//        ANIM CIRCLE
        TranslateTransition transTransCircle = new TranslateTransition(Duration.seconds(TIME_ANIM_INTO/2));
        transTransCircle.setFromY(-(root.getHeight()-circleTemplate.getLayoutY()));
        transTransCircle.setToY(0);
        transTransCircle.setCycleCount(1);

        ScaleTransition scaleTransCircle1 = new ScaleTransition(Duration.seconds(TIME_ANIM_INTO/4));
        scaleTransCircle1.setFromY(0.8);
        scaleTransCircle1.setToY(1);
        scaleTransCircle1.setFromX(0.8);
        scaleTransCircle1.setToX(1);
        scaleTransCircle1.setCycleCount(1);

        ScaleTransition scaleTransCircle2 = new ScaleTransition(Duration.seconds(TIME_ANIM_INTO/4));
        scaleTransCircle2.setFromY(1);
        scaleTransCircle2.setToY(0.5);
        scaleTransCircle2.setCycleCount(1);

        SequentialTransition sequenTransCircle = new SequentialTransition();
        sequenTransCircle.setNode(circle);
        sequenTransCircle.getChildren().addAll(scaleTransCircle1, scaleTransCircle2);
        sequenTransCircle.setCycleCount(1);

        ParallelTransition paralTransCircle = new ParallelTransition();
        paralTransCircle.setNode(circle);
        paralTransCircle.getChildren().addAll(transTransCircle, sequenTransCircle);
        paralTransCircle.setCycleCount(1);

//        ANIM ANCHOR
        TranslateTransition transTransAnchor = new TranslateTransition(Duration.seconds(TIME_ANIM_INTO/2), anchorSlider);
        transTransAnchor.setFromY(anchorSlider.getTranslateY());
        transTransAnchor.setToY(anchorSlider.getTranslateY() - 500);
        transTransAnchor.setCycleCount(1);

//        ANIM BTN

        TranslateTransition transTransBtn = new TranslateTransition(Duration.seconds(TIME_ANIM_INTO/2), btnDemarrer);
        transTransBtn.setFromY(btnDemarrer.getTranslateY());
        transTransBtn.setToY(btnDemarrer.getTranslateY() + 500);
        transTransBtn.setCycleCount(1);

//        JOUER ANIMS
        paralTransCircle.play();
        transTransAnchor.play();
        transTransBtn.play();

        btnDemarrer.setDisable(false);

        affichageValide.setVisible(false);
        affichageEntree.setVisible(false);
        checkDebug.setDisable(false);
        checkDebug.setVisible(true);

        textMode.setVisible(false);
        textCoups.setVisible(false);
    }

    private void animationStopBtn(Button btn){

        ScaleTransition scaleTransBtn = new ScaleTransition(Duration.seconds(TIME_ANIM_INTO/2));
        scaleTransBtn.setFromY(1);
        scaleTransBtn.setToY(0);
        scaleTransBtn.setFromX(1);
        scaleTransBtn.setToX(0);
        scaleTransBtn.setCycleCount(1);

        scaleTransBtn.setNode(btn);
        scaleTransBtn.play();

        btn.setVisible(false);
        btn.setDisable(true);
    }

    private void demarrerJeu(){
        listValide = new ArrayList<>();
        listEntree = new ArrayList<>();

        btnStop.setDisable(false);
        btnHaut.setDisable(false);
        btnDroit.setDisable(false);
        btnBas.setDisable(false);
        btnGauche.setDisable(false);
        btnDemarrer.setDisable(true);

        pageGameOver.setDisable(true);
        pageGameOver.setVisible(false);
        pageReussite.setDisable(true);
        pageReussite.setVisible(false);

        niveau = 1;

        if(isDebugMode){
            affichageValide.getItems().clear();
            affichageEntree.getItems().clear();

            affichageValide.setVisible(true);
            affichageEntree.setVisible(true);
        }

        Button btn = getRandomBtn();
        animerBoutonOnClick(btn, 0.5, true);
        jouerSonBtn(btn);
        listValide.add(btn);
        affichageValide.getItems().add(btn.getId());

        listBtnSimon.remove(btnHautGauche);
        listBtnSimon.remove(btnHautDroit);
        listBtnSimon.remove(btnBasGauche);
        listBtnSimon.remove(btnBasDroit);

        textMode.setVisible(true);
        textCoups.setVisible(true);
        textMode.setText("Mode : " + mode.name());
        textCoups.setText("Niveau : 1");
    }

    private Button getRandomBtn(){
        Collections.shuffle(listBtnSimon);
        return listBtnSimon.get(0);
    }

    private Boolean verifierListDuJeu(){
        boolean valide = true;
        for (int i = 0; i < listEntree.size(); i++) {
            if(!listEntree.get(i).equals(listValide.get(i))){
                valide = false;
            }
        }
        return valide;
    }

    private void animerListValide(){
        new Thread(() -> {
            for (Iterator<Button> iterator = listValide.iterator(); iterator.hasNext(); ) {
                Button next =  iterator.next();
                animerBoutonOnClick(next, 0.5, true);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }

    private void gameOver(){
        jouerSon(SON_GAME_OVER);

        btnStop.setDisable(true);
        btnHaut.setDisable(true);
        btnDroit.setDisable(true);
        btnBas.setDisable(true);
        btnGauche.setDisable(true);

        pageGameOver.setDisable(false);
        pageGameOver.setVisible(true);
        pageGameOver.setScaleX(1);
        pageGameOver.setScaleY(1);

        ScaleTransition scaTranBack = new ScaleTransition(Duration.seconds(TIME_ANIM_INTO/2), circleGameOver);
        scaTranBack.setFromX(0);
        scaTranBack.setFromY(0);
        scaTranBack.setToX(6);
        scaTranBack.setToY(6);

        scaTranBack.play();

    }

    private void finGameOver(){

        pageGameOver.setDisable(true);
        pageGameOver.setVisible(false);

        ScaleTransition scaTranBack = new ScaleTransition(Duration.seconds(TIME_ANIM_INTO/2), pageGameOver);
        scaTranBack.setFromX(1);
        scaTranBack.setFromY(1);
        scaTranBack.setToX(0);
        scaTranBack.setToY(0);
        scaTranBack.play();

    }

    private void reussite(){
        jouerSon(SON_REUSSITE);

        btnStop.setDisable(true);
        btnHaut.setDisable(true);
        btnDroit.setDisable(true);
        btnBas.setDisable(true);
        btnGauche.setDisable(true);

        pageReussite.setDisable(false);
        pageReussite.setVisible(true);
        pageReussite.setScaleX(1);
        pageReussite.setScaleY(1);

        ScaleTransition scaTranBack = new ScaleTransition(Duration.seconds(TIME_ANIM_INTO/2), circleReussite);
        scaTranBack.setFromX(0);
        scaTranBack.setFromY(0);
        scaTranBack.setToX(6);
        scaTranBack.setToY(6);

        scaTranBack.play();

    }

    private void finReussite(){

        pageReussite.setDisable(true);
        pageReussite.setVisible(false);

        ScaleTransition scaTranBack = new ScaleTransition(Duration.seconds(TIME_ANIM_INTO/2), pageReussite);
        scaTranBack.setFromX(1);
        scaTranBack.setFromY(1);
        scaTranBack.setToX(0);
        scaTranBack.setToY(0);
        scaTranBack.play();

    }

    private Mode getMode(){

        if(slider.getValue() == 1){
            return Mode.PRO;
        }

        return Mode.NORMAL;
    }

    private void activerModePro(){
        listBtnSimon.add(btnHautGauche);
        listBtnSimon.add(btnHautDroit);
        listBtnSimon.add(btnBasGauche);
        listBtnSimon.add(btnBasDroit);

        if(!btnHautGauche.isVisible()){
            animationIntroBtn(btnHautGauche);
            animationIntroBtn(btnHautDroit);
            animationIntroBtn(btnBasGauche);
            animationIntroBtn(btnBasDroit);
        }

    }

}
