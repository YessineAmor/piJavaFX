/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.util.Skill;
import util.routers.FXRouter;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ListJobOfferCandidatesController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane candidatesGridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(20);
        JobOffer jobOffer = (JobOffer) FXRouter.getData();
        int index = 0;
        int colIndex = 0;
        for (Candidate candidate : jobOffer.getRegisteredCandidates()) {
            VBox candidateHBox = new VBox();
            HBox btnHBox = new HBox();
            Label nameLabel = new Label("Name: " + candidate.getFirstName() + " " + candidate.getLastName());
            Label skillsLabel = new Label("Skills match: " + 100 * calculateCandidateMatchPercentage(candidate.getSkills(), jobOffer.getSkills()) + "%");
            JFXButton detailsBtn = new JFXButton("See More Details");
            nameLabel.setFont(Font.font("System", 23));
            skillsLabel.setFont(Font.font("System", 23));
            detailsBtn.setFont(Font.font("System", 15));
            candidateHBox.getChildren().add(nameLabel);
            candidateHBox.getChildren().add(skillsLabel);
            btnHBox.getChildren().add(detailsBtn);
            btnHBox.setAlignment(Pos.CENTER_RIGHT);
            candidateHBox.getChildren().add(btnHBox);
            if (colIndex % 2 == 0) {
                candidatesGridPane.add(candidateHBox, 0, index);
                colIndex = 1;
            } else {
                candidatesGridPane.add(candidateHBox, 1, index);
                colIndex = 0;
                index++;
            }
        }
        // anchorPane.getChildren().add(gridPane);
    }

    public double calculateCandidateMatchPercentage(Set<Skill> candidateSkillSet, Set<Skill> requiredSkillSet) {
        int requiredSkillsNumber = requiredSkillSet.size();
        int candidateMatchedSkills = 0;
        for (Skill candidateSkill : candidateSkillSet) {
            for (Skill requiredSkill : requiredSkillSet) {
                if (candidateSkill.toString().equals(requiredSkill.toString())) {
                    candidateMatchedSkills++;
                }
            }
        }
        return (double) candidateMatchedSkills / requiredSkillsNumber;
    }

}
