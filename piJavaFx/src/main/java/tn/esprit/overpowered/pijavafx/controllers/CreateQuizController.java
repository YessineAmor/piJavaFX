/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import tn.esprit.overpowered.byusforus.entities.quiz.Quiz;
import util.factories.CreateAlert;
import util.routers.FXRouter;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class CreateQuizController implements Initializable {

    @FXML
    private Label jobOfferName;
    @FXML
    private GridPane quizInputGridPane;
    @FXML
    private TextArea quizDetailsTextArea;
    @FXML
    private TextField quizNameTextField;
    @FXML
    private TextField quizScoreTextField;

    private Boolean nameValid;
    private Boolean detailsValid;
    private Boolean percentageValid;
    @FXML
    private Button submitBtn;
    private AnchorPane baseCentralAnchorPane;
    @FXML
    private AnchorPane mainAnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nameValid = false;
        detailsValid = false;
        percentageValid = false;
        submitBtn.setDisable(true);

        // Decimal input control
        quizScoreTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (!quizScoreTextField.getText().matches("[0-9]{2}($|(,|\\.)[0-9]*)")) {
                    CreateAlert.CreateAlert(Alert.AlertType.ERROR, "Error Message", "Input Error!", "The percentage needs to be of decimal type.");
                    quizScoreTextField.setText("");
                    percentageValid = false;
                    updateBtnState();
                } else {
                    percentageValid = true;
                    updateBtnState();
                }
            }
        });

        // Quiz name textfield length input control
        quizNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 30) {
                CreateAlert.CreateAlert(Alert.AlertType.ERROR, "Error!", "Input Error!", "Length needs to be < 30 chars");
                quizNameTextField.setText(quizNameTextField.getText().substring(0, 30));
            }
            nameValid = true;
            updateBtnState();
        });

        // Quiz details text area length input control
        quizDetailsTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() >= 240) {
                CreateAlert.CreateAlert(Alert.AlertType.ERROR, "Error!", "Input Error!", "Length needs to be < 240 chars");
                quizDetailsTextArea.setText(quizDetailsTextArea.getText().substring(0, 239));
            }
            detailsValid = true;
            updateBtnState();

        });

    }

    public void updateBtnState() {
        if (detailsValid && nameValid && percentageValid && !quizDetailsTextArea.getText().isEmpty() && !quizScoreTextField.getText().isEmpty() && !quizNameTextField.getText().isEmpty()) {
            submitBtn.setDisable(false);
        } else {
            submitBtn.setDisable(true);

        }
    }

    @FXML
    private void onSubmitBtnClicked(ActionEvent event) throws IOException {
        FXRouter.goTo("CreateQuestions");
    }

    

}
