/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.naming.NamingException;
import javax.swing.JCheckBox;
import tn.esprit.overpowered.byusforus.entities.util.ExpertiseLevel;
import tn.esprit.overpowered.byusforus.entities.util.Skill;
import util.routers.FXRouter;
import utilJoboffer.HandleOffer;
import utilJoboffer.SkillView;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class CreateJobOfferController implements Initializable {

    ObservableList<String> expertiseLevels = FXCollections.
            observableArrayList("JUNIOR", "ASSOCIATE", "EXPERT");
    ObservableList<SkillView> skills = FXCollections.
            observableArrayList(new SkillView("JAVA"),
                    new SkillView("C"),
                    new SkillView("SOC"), new SkillView("MICROSOFT"), new SkillView("NETWORKING"));
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField location;
    @FXML
    private JFXComboBox<String> expertiseLevelComboBox;
    @FXML
    private JFXTextField neededCandidates;
    @FXML
    private Button createOfferButton;
    @FXML
    private TableView<SkillView> skillsTable;
    @FXML
    private AnchorPane rightMenuAnchorPane;
    @FXML
    private AnchorPane centralAnchorPane;
    @FXML
    private AnchorPane topMenuAnchorPane;
    @FXML
    private MenuBar topMenu;
    @FXML
    private Button homePageButton;
    @FXML
    private TextArea description;
    @FXML
    private Button myprofileButton;
    @FXML
    private Button offersButton;
    @FXML
    private Button logoutButton;
    @FXML
    private JFXComboBox<?> stateCombobox;
    @FXML
    private AnchorPane parentAnchorPane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        expertiseLevelComboBox.setValue("JUNIOR");
        expertiseLevelComboBox.setItems(expertiseLevels);
        TableColumn skillColumn = new TableColumn("SKILLS");
        TableColumn selectColumn = new TableColumn("SELECT");
        skillsTable.getColumns().addAll(skillColumn, selectColumn);

        skillColumn.setCellValueFactory(
                new PropertyValueFactory<SkillView, String>("skill"));
        selectColumn.setCellValueFactory(
                new PropertyValueFactory<SkillView, String>("select"));
        skillsTable.setItems(skills);

    }

    @FXML
    private void createOfferButtonOnClicked(MouseEvent event) throws NamingException {
        ObservableList<SkillView> selectSkills = FXCollections
                .observableArrayList();
        List<Skill> listofSkills = new ArrayList<>();
        skills.stream().filter((skil) -> (skil.getSelect().isSelected())).forEachOrdered((skil) -> {
            listofSkills.add(Skill.valueOf(skil.getSkill()));
        });
        HandleOffer.createJobOffer(title.getText(), location.getText(),
                ExpertiseLevel.valueOf(expertiseLevelComboBox.getValue()), listofSkills,
                Integer.parseInt(neededCandidates.getText()));

    }

    @FXML
    private void homePageButton(MouseEvent event) {
    }

    @FXML
    private void myprofileButton(MouseEvent event) {
    }

    @FXML
    private void offersButtonOnClicked(MouseEvent event) {
    }

    @FXML
    private void logoutButtonOnClicked(MouseEvent event) throws IOException {
       FXRouter.when("LoginView", "Login.fxml","Login",600,400);
        FXRouter.setRouteContainer("LoginView",parentAnchorPane);
        FXRouter.goTo("LoginView");
    }

}
