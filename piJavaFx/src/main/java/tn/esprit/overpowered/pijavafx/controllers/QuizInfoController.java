/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import tn.esprit.overpowered.byusforus.entities.quiz.Quiz;
import tn.esprit.overpowered.byusforus.services.quiz.QuizTryFacadeRemote;
import util.factories.CreateAlert;
import util.routers.FXRouter;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class QuizInfoController implements Initializable {

    @FXML
    private Button startQuizBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onStartQuizBtnClicked(ActionEvent event) throws IOException {
        Optional<ButtonType> alertResult = CreateAlert.CreateAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "We need your permission.", "In order to take the quiz, we need access to your webcam feed. Do you accept?");
        if (alertResult.isPresent() && alertResult.get() == ButtonType.OK) {
            FXRouter.goTo("TryQuiz");
        }
    }

}
