/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import tn.esprit.overpowered.byusforus.entities.quiz.QuizTry;
import util.routers.FXRouter;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class QuizResultsController implements Initializable {

    @FXML
    private Label resultLabel;
    @FXML
    private Label infoLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        QuizTry quizTry = (QuizTry) FXRouter.getData();
        float score = quizTry.getPercentage();
        if (score >= quizTry.getQuiz().getPercentageToPass()) {
            resultLabel.setText("Congratulations!");
            infoLabel.setText("You passed this quiz with a " + score + "% score ");
        }
    }

}
