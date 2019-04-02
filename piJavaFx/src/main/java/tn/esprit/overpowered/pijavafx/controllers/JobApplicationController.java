/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javax.naming.Context;
import tn.esprit.overpowered.byusforus.entities.candidat.CandidateApplication;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.util.Skill;
import util.authentication.Authenticator;
import util.routers.FXRouter;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class JobApplicationController implements Initializable {

    @FXML
    private Label jobAppTitle;
    @FXML
    private TextArea motivationLetter;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label resume;
    private Context context;
    @FXML
    private JFXButton goBack;
    @FXML
    private JFXButton submitApp;
    private JobOffer jobOffer;
    private Candidate cdt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<Context, JobOffer> dataMap = (HashMap) FXRouter.getData();
        context = dataMap.keySet().stream().findFirst().get();
        jobOffer = dataMap.values().stream().findFirst().get();
        cdt = (Candidate) Authenticator.currentUser;
        name.setText(cdt.getFirstName() + " " + cdt.getLastName());
        email.setText(cdt.getEmail());
        resume.setText(cdt.getCurriculumVitaes());

    }

    @FXML
    private void onGoBackBtnClicked(ActionEvent event) {
    }

    @FXML
    private void onSubmitAppBtnClicked(ActionEvent event) throws IOException {
        CandidateApplication cApp = new CandidateApplication();
        List<Long> recommendedIdList = new ArrayList<>();
        recommendedIdList.add(2L);
        cdt.setRecommendedIdList(recommendedIdList);
        cdt.setExperiences(new ArrayList<>());
        cdt.setSubscribedCompanies(new ArrayList<>());
        cdt.setActivities(new ArrayList<>());
        cdt.setCertificates(new ArrayList<>());
        cdt.setContacts(new HashSet<>());
        cdt.setCursus(new ArrayList<>());
        cdt.setRegisteredOffers(new ArrayList<>());
        cdt.setSubscribedCompanies(new ArrayList<>());
        Set<Skill> skillSet = new HashSet<>();
        skillSet.add(Skill.JAVA);
        cdt.setSkills(skillSet);
        jobOffer.setSkills(new HashSet<>());
        cApp.setCandidate(cdt);
        cApp.setJobOffer(jobOffer);
        cApp.setResume(resume.getText());
        cApp.setMotivationLetter(motivationLetter.getText());
        cApp.setId(jobOffer.getId().intValue() + Authenticator.currentUser.getId().intValue());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(JsonParser.Feature.ALLOW_MISSING_VALUES, true);
        mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        String fileName = "candidate_app_" + jobOffer.getId() + "_" + Authenticator.currentUser.getEmail();
        mapper.writeValue(new File(fileName + ".json"), cApp);
        CandidateApplication cApp2 = mapper.readValue(new File(fileName + ".json"), CandidateApplication.class);
        System.out.println("this is quiz try from json : \n " + cApp2);

//        String jndiName = "piJEE-ejb-1.0/CandidateApplicationFacade!tn.esprit.overpowered.byusforus.services.candidat.CandidateApplicationFacadeRemote";
//        CandidateApplicationFacadeRemote candidateApplicationFacade = (CandidateApplicationFacadeRemote) context.lookup(jndiName);
//        candidateApplicationFacade.create(cApp);
    }

}
