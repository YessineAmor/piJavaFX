/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.overpowered.byusforus.entities.quiz.Question;
import tn.esprit.overpowered.byusforus.entities.quiz.Quiz;
import tn.esprit.overpowered.byusforus.services.quiz.ChoiceFacadeRemote;
import tn.esprit.overpowered.byusforus.services.quiz.QuizFacadeRemote;
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
    private Button createQuizBtn;

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
        FXRouter.setRouteContainer("QuizInfo", centralAnchorPane);
        
    }

    @FXML
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

}
