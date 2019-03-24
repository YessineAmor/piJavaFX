/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import tn.esprit.overpowered.byusforus.entities.quiz.Question;
import tn.esprit.overpowered.byusforus.entities.quiz.Quiz;
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
    @FXML
    private Label quizName;
    @FXML
    private Label numberOfQuestions;
    @FXML
    private Label minScore;
    @FXML
    private TextArea quizDetails;

    private List<Question> questions;
    private Quiz quiz;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        quiz = (Quiz) FXRouter.getData();
        questions = quiz.getQuestions();
        quizName.setText(quiz.getName());
        quizDetails.setText(quiz.getDetails());
        numberOfQuestions.setText(Integer.toString(questions.size()));
        minScore.setText(Float.toString(quiz.getPercentageToPass()));
    }

    @FXML
    private void onStartQuizBtnClicked(ActionEvent event) throws IOException {
        Optional<ButtonType> alertResult = CreateAlert.CreateAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "We need your permission.", "In order to take the quiz, we need access to your webcam feed. Do you accept?");
        if (alertResult.isPresent() && alertResult.get() == ButtonType.OK) {
            FXRouter.goTo("TryQuiz", quiz);
        }
    }

}
