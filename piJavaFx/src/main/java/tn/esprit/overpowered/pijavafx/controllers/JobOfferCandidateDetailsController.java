/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.overpowered.byusforus.entities.candidat.CandidateApplication;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.services.candidat.CandidateApplicationFacadeRemote;
import tn.esprit.overpowered.byusforus.util.JobApplicationState;
import util.factories.CreateAlert;
import util.routers.FXRouter;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class JobOfferCandidateDetailsController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorScrollPane;
    @FXML
    private GridPane candidatesGridPane;
    @FXML
    private Label titleLabel;
    @FXML
    private Text nameText;
    @FXML
    private Text fullProfleText;
    @FXML
    private Text motivationLetterText;
    @FXML
    private Text viewResumeText;
    @FXML
    private Text skillsText;
    @FXML
    private HBox buttonsHBox;
    @FXML
    private JFXButton refuseCandidacyBtn;
    @FXML
    private JFXButton inviteBtn;
    private Candidate candidate;
    private JobOffer jobOffer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<Candidate, JobOffer> candidateJobOfferMap = (HashMap) FXRouter.getData();
        candidate = candidateJobOfferMap.keySet().stream().findFirst().get();
        jobOffer = candidateJobOfferMap.values().stream().findFirst().get();
        titleLabel.setText(candidate.getFirstName() + " " + candidate.getLastName() + " " + titleLabel.getText());
        nameText.setText(candidate.getFirstName() + " " + candidate.getLastName());
        scrollPane.setPrefHeight((double) FXRouter.scene.heightProperty().doubleValue() - 20);
        anchorScrollPane.setPrefHeight((double) FXRouter.scene.heightProperty().doubleValue() - 10);
        scrollPane.setPrefWidth((double) FXRouter.scene.widthProperty().doubleValue() - 55);
        anchorScrollPane.setPrefWidth((double) FXRouter.scene.widthProperty().doubleValue());
        double ss = (FXRouter.scene.widthProperty().doubleValue() / 2);
        titleLabel.setLayoutX(ss);
        FXRouter.scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setPrefWidth((double) newValue - 55);
            anchorScrollPane.setPrefWidth((double) newValue);
            double titleLabelLayoutX = (newValue.doubleValue() / 2) - titleLabel.getWidth() / 2;
            titleLabel.setLayoutX(titleLabelLayoutX);
            double gridPaneLayoutX = (newValue.doubleValue() / 2) - candidatesGridPane.getWidth() / 2;
            candidatesGridPane.setLayoutX(gridPaneLayoutX);
            double buttonsHBoxLayoutX = (newValue.doubleValue() / 2) - buttonsHBox.getWidth() / 2;
            buttonsHBox.setLayoutX(buttonsHBoxLayoutX);
        });
        FXRouter.scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setPrefHeight((double) newValue - 20);
            anchorScrollPane.setPrefHeight((double) newValue - 10);
        });
    }

    @FXML
    private void onRefuseCandidacyBtnClicked(ActionEvent event) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Insert reason for refusal here:"), 0, 0);
        JFXTextArea refusalTextArea = new JFXTextArea();
        gridPane.add(refusalTextArea, 1, 0);
        gridPane.setPadding(new Insets(20));
        JFXButton validateBtn = new JFXButton();
        validateBtn.setText("Submit");
        gridPane.setHgap(40);
        validateBtn.setStyle("-fx-background-color: white;");
        dialogVbox.setPadding(new Insets(20));
        dialogVbox.getChildren().add(gridPane);
        dialogVbox.getChildren().add(validateBtn);
        Scene dialogScene = new Scene(dialogVbox, 800, 200);
        dialog.setScene(dialogScene);
        dialog.show();
        validateBtn.setOnAction((validateBtnEvent) -> {
            if (refusalTextArea.getText().isEmpty()) {
                CreateAlert.CreateAlert(Alert.AlertType.ERROR, "Error encountered", "Incomplete info",
                        "You need to specifiy a reason for the refusal");
            } else {
                try {
                    String jndiName = "piJEE-ejb-1.0/CandidateApplicationFacade!tn.esprit.overpowered.byusforus.services.candidat.CandidateApplicationFacadeRemote";
                    Context context = new InitialContext();
                    CandidateApplicationFacadeRemote candidateApplicationFacade = (CandidateApplicationFacadeRemote) context.lookup(jndiName);
                    CandidateApplication cApp = candidateApplicationFacade.getApplicationByCandidateId(candidate.getId(), jobOffer.getId());
                    cApp.setAdditionalInfo(refusalTextArea.getText());
                    cApp.setJobApplicationState(JobApplicationState.REFUSED);
                    candidateApplicationFacade.updateCandidateApplication(cApp);
                } catch (NamingException ex) {
                    Logger.getLogger(JobOfferCandidateDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    @FXML
    private void onInviteBtnClicked(ActionEvent event) {
    }

}
