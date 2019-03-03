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
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class CreateQuestionsController implements Initializable {

    @FXML
    private Accordion questionsAccordion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillTitledPanes();
    }

    public void fillTitledPanes() {
        questionsAccordion.getPanes().forEach((pane) -> {
            GridPane gridPane = new GridPane();
            gridPane.add(new Label("Question:"), 0, 0);
            gridPane.add(new Label("Points"), 0, 1);
            gridPane.add(new Label("Choices"), 0, 2);
            TextField questionTextField = new TextField();
            TextField pointsTextField = new TextField();
            TextField choiceTextField = new TextField();
            Button addChoiceBtn = new Button();
            gridPane.add(questionTextField, 1, 0);
            gridPane.add(pointsTextField, 1, 1);
            gridPane.add(choiceTextField, 1, 2);
            gridPane.add(addChoiceBtn, 1, 2);
            pane.setText("sss");
            pane.setContent(gridPane);
        });
    }

}
