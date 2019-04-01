/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.overpowered.byusforus.entities.quiz.Question;
import tn.esprit.overpowered.byusforus.entities.quiz.Quiz;
import tn.esprit.overpowered.byusforus.services.quiz.ChoiceFacadeRemote;
import tn.esprit.overpowered.byusforus.services.quiz.QuizFacadeRemote;
import util.exceptions.InvalidArgumentException;
import util.exceptions.WidgetNotFoundException;
import util.factories.ChangeDimensions;
import util.factories.ChangeDimensionsFactory;
import util.routers.FXRouter;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class BaseController implements Initializable {

    @FXML
    private AnchorPane generalAnchorPane;
//    @FXML
//    private AnchorPane rightMenuAnchorPane;
    @FXML
    private AnchorPane centralAnchorPane;
    @FXML
    private AnchorPane topMenuAnchorPane;
    @FXML
    private MenuBar topMenu;
    @FXML
    private AnchorPane rightMenuAnchorPane;
    @FXML
    private Button profileButton;
    @FXML
    private Button contactsButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // First register a new RouteScene
        // Then bind that RouteScene to its container
        FXRouter.when("CreateQuiz", "CreateQuiz.fxml");
        FXRouter.when("CreateQuestions", "CreateQuestions.fxml");
        FXRouter.when("TryQuiz", "TryQuiz.fxml");
        FXRouter.when("QuizInfo", "QuizInfo.fxml");
        FXRouter.when("QuizResults", "QuizResults.fxml");
        FXRouter.when("Profile", "Profile.fxml");
        FXRouter.setRouteContainer("Profile", generalAnchorPane);
        FXRouter.setRouteContainer("QuizInfo", centralAnchorPane);
        FXRouter.setRouteContainer("TryQuiz", centralAnchorPane);
        FXRouter.setRouteContainer("QuizResults", centralAnchorPane);
        FXRouter.setRouteContainer("CreateQuiz", centralAnchorPane);
        FXRouter.setRouteContainer("CreateQuestions", centralAnchorPane);
        // registering listeners for resizehttps://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
       /* ChangeDimensionsFactory cFactory = new ChangeDimensionsFactory();
        ChangeListener<Number> sideMenuChangeListener;
        Scene s = FXRouter.scene;

        try {
            sideMenuChangeListener = cFactory.createListener(
                    generalAnchorPane, "#rightMenuAnchorPane", 1, ChangeDimensions.HEIGHT);
            s.getWindow().heightProperty().addListener(sideMenuChangeListener);

        } catch (WidgetNotFoundException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidArgumentException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChangeListener<Number> topMenuAnchorPaneListener;
        try {
            topMenuAnchorPaneListener = cFactory.createListener(
                    generalAnchorPane, "#topMenuAnchorPane", 1, ChangeDimensions.WIDTH);
            s.getWindow().widthProperty().addListener(topMenuAnchorPaneListener);

        } catch (WidgetNotFoundException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidArgumentException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChangeListener<Number> topMenuChangeListener;
        try {
            topMenuChangeListener = cFactory.createListener(
                    generalAnchorPane, "#topMenu", 1, ChangeDimensions.WIDTH);
            s.getWindow().widthProperty().addListener(topMenuChangeListener);

        } catch (WidgetNotFoundException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidArgumentException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
*/

    }

    private void onCreateQuizBtnClicked(ActionEvent event) throws IOException, NamingException {
//       FXRouter.goTo("CreateQuiz");
        String jndiName = "piJEE-ejb-1.0/QuizFacade!tn.esprit.overpowered.byusforus.services.quiz.QuizFacadeRemote";
        Context context = new InitialContext();
        QuizFacadeRemote quizFacadeProxy = (QuizFacadeRemote) context.lookup(jndiName);
        FXRouter.goTo("QuizInfo", quizFacadeProxy.findAll().get(0));
    }

    public AnchorPane getCentralAnchorPane() {
        return centralAnchorPane;
    }

    public void setCentralAnchorPane(AnchorPane centralAnchorPane) {
        this.centralAnchorPane = centralAnchorPane;
    
    }
    @FXML
    private void profileButtonClicked(ActionEvent event) throws IOException {
        FXRouter.goTo("Profile");
    }

    @FXML
    private void contactsButtonClicked(MouseEvent event) throws IOException {
       FXRouter.when("CandidateListView", "CandidateList.fxml","Candidate List", 889, 543);
                 FXRouter.setRouteContainer("CandidateListView", generalAnchorPane);
        FXRouter.goTo("CandidateListView");
    }

}
