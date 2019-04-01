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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView confettiImageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        confettiImageView.fitWidthProperty().bind(FXRouter.scene.widthProperty());
        FXRouter.scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("scene width changed: " + newValue);
            resultLabel.setLayoutX(100);
            resultLabel.relocate((double) newValue / 2, 200);
            System.out.println("rseult label layout x " + resultLabel.getLayoutX());
            infoLabel.setLayoutX((double) newValue / 2);
            confettiImageView.setFitWidth((double) newValue);
        });
        QuizTry quizTry = (QuizTry) FXRouter.getData();
        System.out.println("quiz try serial" + QuizTry.getSerialVersionUID());
        float score = quizTry.getPercentage();
        float percentageToPass = quizTry.getQuiz().getPercentageToPass();
        System.out.println("Score user: " + score);
        System.out.println("score to pass : " + percentageToPass);
        if (score >= percentageToPass) {
            resultLabel.setText("Congratulations!");
            infoLabel.setText("You passed this quiz with a " + score + "% score ");
        } else {
            confettiImageView.setVisible(false);
            resultLabel.setText("You didn't pass.");
            infoLabel.setText("Sorry to inform you that your score " + score + "% is less than the "
                    + "required score to pass this quiz. ");

        }
    }

}
