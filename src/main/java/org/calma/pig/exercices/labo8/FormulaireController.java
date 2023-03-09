package org.calma.pig.exercices.labo8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class FormulaireController {

    @FXML
    private Button btnExVersIn;
    @FXML
    private Button btnInVersEx;
    @FXML
    private Button btnVerif;
    @FXML
    private DatePicker dpDate;
    @FXML
    private ImageView ivCourrielValid;
    @FXML
    private ImageView ivLogo;
    @FXML
    private ListView<String> lvExclus;
    @FXML
    private ListView<String> lvInclus;
    @FXML
    private Text textError;
    @FXML
    private Text textErrorCourriel;
    @FXML
    private Text textErrorDate;
    @FXML
    private Text textErrorNom;
    @FXML
    private Text textErrorPrenom;
    @FXML
    private Text textInfos;
    @FXML
    private TextField tfCourriel;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;

    private Image imageCourrielInvalid;
    private Image imageCourrielValid;
    private List<String> infos;
    private Boolean touchDep;
    private Boolean courrielValide;

    @FXML
    void initialize(){
        imageCourrielInvalid = new Image("org/calma/pig/exercices/labo8/images/email_invalid.png");
        imageCourrielValid = new Image("org/calma/pig/exercices/labo8/images/email_valid.png");

        tfPrenom.setFocusTraversable(true);

        lvInclus.getItems().add("Réseau");
        lvInclus.getItems().add("Jeux");
        lvInclus.getItems().add("Base de données");
        lvInclus.getItems().add("Programmation");
        lvInclus.getItems().add("Web");

        touchDep = false;
        courrielValide = false;
    }

    @FXML
    void onClickBtnVerif(MouseEvent event) {
        infos = new ArrayList<>();
        infos.add(tfPrenom.getText());
        infos.add(tfNom.getText());
        infos.add(tfCourriel.getText());

        boolean isFormValide = true;

        textErrorPrenom.setVisible(false);
        textErrorNom.setVisible(false);
        textErrorDate.setVisible(false);
        textErrorCourriel.setVisible(false);
        textError.setVisible(false);
        textInfos.setVisible(false);

        if(infos.get(0).equals("")){
            System.out.println(infos);
            textError.setVisible(true);
            textError.setText("Votre prénom est obligatoire !");

            textErrorPrenom.setVisible(true);

            isFormValide = false;
        }
        else if (infos.get(0).length() < 3) {
            textError.setVisible(true);
            textError.setText("Votre prénom doit faire au moins 3 lettres de long !");

            textErrorPrenom.setVisible(true);

            isFormValide = false;
        }
        else{
            textError.setVisible(false);
            textErrorPrenom.setVisible(false);

        }

        if(isFormValide){
            if(infos.get(1).equals("")){
                textError.setVisible(true);
                textError.setText("Votre nom de famille est obligatoire !");

                textErrorNom.setVisible(true);

                isFormValide = false;
            }
            else if (infos.get(1).length() < 3) {
                textError.setVisible(true);
                textError.setText("Votre nom doit faire au moins 3 lettres de long !");

                textErrorNom.setVisible(true);

                isFormValide = false;
            }
            else{
                textError.setVisible(false);

                textErrorNom.setVisible(false);
            }
        }

        if(isFormValide){
            if(dpDate.getValue() == null){
                textError.setVisible(true);
                textError.setText("Votre date de naissance est obligatoire !");

                textErrorDate.setVisible(true);

                isFormValide = false;
            }
            else if(dpDate.getValue().isAfter(LocalDate.now())){
                textError.setVisible(true);
                textError.setText("Votre date de naissance doit être avant aujourd'hui !");

                textErrorDate.setVisible(true);

                isFormValide = false;
            }
            else{
                textErrorDate.setVisible(false);
            }
        }

        if(isFormValide){
            if(infos.get(2).equals("")){
                textError.setVisible(true);
                textError.setText("Votre courriel est obligatoire !");

                textErrorCourriel.setVisible(true);

                courrielValide = false;

                isFormValide = false;
            }
            else if(!infos.get(2).contains("@") || !infos.get(2).contains(".")){
                textError.setVisible(true);
                textError.setText("Votre courriel n'est pas valide, " +
                        "il doit contenir au moins un '@' et un '.' !");

                textErrorCourriel.setVisible(true);

                courrielValide = false;

                isFormValide = false;
            }
            else{
                textError.setVisible(false);
                textErrorCourriel.setVisible(false);

                courrielValide = true;
            }
        }

        if(isFormValide){
            if(!touchDep){
                textInfos.setVisible(true);
                textInfos.setText("Avez-vous vérifier vos départements ?" +
                        " (Cliquez à nouveau si vous avez bien vérifier.)");

                isFormValide = false;
                touchDep = true;
            }
        }

        if(isFormValide){
            InfosInMemory infosInMemory = sauvegarderInfos();

            System.out.println("Formulaire envoyé !");
            System.out.println(infosInMemory);

            lancerPageConfirmation();
        }
    }

    @FXML
    void onClickBtnExVersIn(MouseEvent event) {
        touchDep = true;

        String addedItem = lvExclus.getSelectionModel().getSelectedItem();
        if(addedItem != null) {
            lvInclus.getItems().add(addedItem);
            lvExclus.getItems().remove(addedItem);
        }
    }
    @FXML
    void onClickBtnInVersEx(MouseEvent event) {
        touchDep = true;

        String removedItem = lvInclus.getSelectionModel().getSelectedItem();
        if(removedItem != null) {
            lvExclus.getItems().add(removedItem);
            lvInclus.getItems().remove(removedItem);
        }
    }
    @FXML
    void onActionDatePicker(ActionEvent event) {
        changeId();
    }
    @FXML
    void lancerPageDocumentation(ActionEvent event) {
        Parent root = null;

        try {
            root = (AnchorPane) FXMLLoader.load(getClass().getResource("Documentation.fxml"));
            Scene scene = new Scene(root, 400, 500);
            Stage stage = new Stage();

            stage.setTitle("Documentation");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void lancerPageFAQ(ActionEvent event) {
        Parent root = null;

        try {
            root = (AnchorPane) FXMLLoader.load(getClass().getResource("Faq.fxml"));
            Scene scene = new Scene(root, 400, 500);
            Stage stage = new Stage();

            stage.setTitle("Foire aux questions");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void onKeyTypedTextField(KeyEvent event) {
        actualiserImageCourriel();
        changeId();
    }

    private void changeId() {
        String prenom = tfPrenom.getText().toUpperCase();
        String nom = tfNom.getText().toUpperCase();
        String jour = "JJ";
        String annee = "AA";

        if(prenom.length() >= 3){
            prenom = prenom.substring(0,3);
        }
        else{
            switch (prenom.length()){
                case 2:
                    prenom = prenom + "E";
                    break;
                case 1:
                    prenom = prenom + "RE";
                    break;
                default:
                    prenom = "PRE";
                    break;
            }
        }

        if(nom.length() >= 3){
            nom = nom.substring(0,3);
        }
        else{
            switch (nom.length()){
                case 2:
                    nom += "M";
                    break;
                case 1:
                    nom += "OM";
                    break;
                default:
                    nom = "NOM";
                    break;
            }
        }

        if(dpDate.getValue() != null){
            jour = String.valueOf(dpDate.getValue().getDayOfMonth());
            annee = String.valueOf(dpDate.getValue().getYear()).substring(2,4);

            if(jour.length() < 2){
                jour = "0" + jour;
            }
        }

        tfID.setText(prenom + '-' + nom + '-' + jour + '-' + annee);
    }

    public InfosInMemory sauvegarderInfos(){
        String i = tfID.getText();
        String p = tfPrenom.getText();
        String n = tfNom.getText();
        LocalDate d = dpDate.getValue();
        String c = tfCourriel.getText();
        List<String> lIn = lvInclus.getItems();
        List<String> lEx = lvExclus.getItems();

        return new InfosInMemory(i, p, n, d, c, lIn, lEx);
    }

    private void lancerPageConfirmation(){
        Parent root = null;

        try {
            root = (AnchorPane) FXMLLoader.load(getClass().getResource("Confirmation.fxml"));
            Scene scene = new Scene(root, 330, 100);
            Stage stage = new Stage();

            stage.setTitle("Confirmation");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void actualiserImageCourriel(){
        String courriel = tfCourriel.getText();
        if(courriel.equals("")){
            courrielValide = false;
        }
        else if(!courriel.contains("@") || !courriel.contains(".")){
            courrielValide = false;
        }
        else{
            courrielValide = true;
        }

        if (courrielValide){
            ivCourrielValid.setImage(imageCourrielValid);
        }
        else{
            ivCourrielValid.setImage(imageCourrielInvalid);
        }
    }
}
