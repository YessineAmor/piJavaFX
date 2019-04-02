/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.overpowered.byusforus.entities.candidat.CandidateApplication;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.services.candidat.CandidateApplicationFacadeRemote;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            JobOffer jobOffer = (JobOffer) FXRouter.getData();
            Candidate cdt = (Candidate) Authenticator.currentUser;
            name.setText(cdt.getFirstName() + " " + cdt.getLastName());
            email.setText(cdt.getEmail());
            resume.setText(cdt.getCurriculumVitaes());
            CandidateApplication cApp = new CandidateApplication();
            cApp.setCandidate(cdt);
            cApp.setJobOffer(jobOffer);
            cApp.setResume(resume.getText());
            cApp.setMotivationLetter(motivationLetter.getText());
            String jndiName = "piJEE-ejb-1.0/CandidateApplicationFacade!tn.esprit.overpowered.byusforus.services.candidat.CandidateApplicationFacadeRemote";
            CandidateApplicationFacadeRemote candidateApplicationFacade = (CandidateApplicationFacadeRemote) context.lookup(jndiName);
            candidateApplicationFacade.create(cApp);
        } catch (NamingException ex) {
            Logger.getLogger(JobApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
