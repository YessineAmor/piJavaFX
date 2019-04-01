/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.util.ExpertiseLevel;
import tn.esprit.overpowered.byusforus.entities.util.Skill;
import tn.esprit.overpowered.byusforus.services.entrepriseprofile.JobOfferFacadeRemote;
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
    private JFXButton messagesButton;
    @FXML
    private JFXButton messagesButton1;
    @FXML
    private JFXButton notificationsButton;
    @FXML
    private JFXButton createQuizBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // First register a new RouteScene
        // Then bind that RouteScene to its container
        FXRouter.when("CreateQuiz", "CreateQuiz.fxml");
        FXRouter.when("CreateQuestions", "CreateQuestions.fxml");
        FXRouter.when("QuizInfo", "QuizInfo.fxml");
        FXRouter.when("QuizResults", "QuizResults.fxml");
        FXRouter.when("ListJobOfferCandidates", "ListJobOfferCandidates.fxml");
        FXRouter.when("JobOfferCandidateDetails", "JobOfferCandidateDetails.fxml");
        FXRouter.when("ProfileView", "Profile.fxml");
        FXRouter.setRouteContainer("ProfileView", generalAnchorPane);
        FXRouter.setRouteContainer("QuizInfo", centralAnchorPane);
        FXRouter.setRouteContainer("QuizResults", centralAnchorPane);
        FXRouter.setRouteContainer("CreateQuiz", centralAnchorPane);
        FXRouter.setRouteContainer("CreateQuestions", centralAnchorPane);

        FXRouter.setRouteContainer("ListJobOfferCandidates", centralAnchorPane);
        FXRouter.setRouteContainer("JobOfferCandidateDetails", centralAnchorPane);
        // registering listeners for resizehttps://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
        ChangeDimensionsFactory cFactory = new ChangeDimensionsFactory();
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

    @FXML

    private void goToInbox(ActionEvent event) throws IOException {

        FXRouter.goTo("inbox");
        FXRouter.when("inboxView", "Inbox.fxml");
        FXRouter.setRouteContainer("inboxView", centralAnchorPane);
        FXRouter.goTo("inboxView");
    }

    private void onManageCandidacyBtnClicked(ActionEvent event) throws NamingException, IOException, NamingException, NoSuchAlgorithmException {
        String jndiName = "piJEE-ejb-1.0/JobOfferFacade!tn.esprit.overpowered.byusforus.services."
                + "entrepriseprofile.JobOfferFacadeRemote";
        Context context = new InitialContext();
        JobOfferFacadeRemote jobOfferFacade = (JobOfferFacadeRemote) context.lookup(jndiName);

        CompanyProfile company = new CompanyProfile();
        company.setName("Facebook");
        List<Candidate> registeredCandidates = new ArrayList<>();
        Candidate c = new Candidate();
        c.setFirstName("Yassine");
        c.setLastName("Amor");
        c.setSkills(new HashSet<Skill>(Arrays.asList(Skill.JAVA)));
        c.setPassword("123456".getBytes());
        c.setUsername("pidevcandidate");
        registeredCandidates.add(c);
        registeredCandidates.add(c);
        JobOffer jobOffer = new JobOffer();
        jobOffer.setTitle("Développeur JAVA");
        Set<Skill> skillSet = new HashSet<>();
        skillSet.add(Skill.JAVA);
        skillSet.add(Skill.PYTHON);
        jobOffer.setSkills(skillSet);
        jobOffer.setPeopleNeeded(3);
        jobOffer.setExpertiseLevel(ExpertiseLevel.JUNIOR);
        jobOffer.setDescription("The candidate will help us work on JavaEE projects.");
        jobOffer.setCity("Tunis");
        jobOffer.setCompany(company);
        jobOffer.setRegisteredCandidates(registeredCandidates);
        jobOfferFacade.create(jobOffer);

        FXRouter.goTo("ListJobOfferCandidates", jobOffer);
    }

    private void contactsButtonClicked(MouseEvent event) throws IOException {
        FXRouter.when("CandidateListView", "CandidateList.fxml", "Candidate List", 889, 543);
        FXRouter.setRouteContainer("CandidateListView", generalAnchorPane);
        FXRouter.goTo("CandidateListView");
    }

    @FXML
    private void profileButtonClicked(MouseEvent event) throws IOException {
        FXRouter.goTo("ProfileView");
    }

}
