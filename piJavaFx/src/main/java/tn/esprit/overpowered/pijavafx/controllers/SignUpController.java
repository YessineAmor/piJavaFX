package tn.esprit.overpowered.pijavafx.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.CompanyAdmin;
import tn.esprit.overpowered.byusforus.entities.users.HRManager;
import tn.esprit.overpowered.byusforus.entities.users.User;
import tn.esprit.overpowered.byusforus.services.candidat.CandidateFacadeRemote;
import tn.esprit.overpowered.byusforus.services.users.UserFacadeRemote;
import util.authentication.SignUp;
import util.routers.FXRouter;

/**
 * FXML Controller class
 *
 * @author EliteBook
 */
public class SignUpController implements Initializable {

    @FXML
    private AnchorPane parentAnchorPane;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField lastname;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXRadioButton radioCandidate;
    @FXML
    private ToggleGroup userType;
    @FXML
    private JFXRadioButton radioEmployee;
    @FXML
    private JFXRadioButton radioCompanyAdmin;
    @FXML
    private Button continueAs;
    @FXML
    private Label signUpLabel;
    @FXML
    private JFXPasswordField password_prime;
    @FXML
    private Button goback;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FXRouter.when("EmailConfirmView", "ConfirmEmail.fxml");
        FXRouter.setRouteContainer("EmailConfirmView", parentAnchorPane);
    }

    @FXML
    private void continueAs(MouseEvent event) throws NoSuchAlgorithmException, NamingException, IOException {

        JFXRadioButton rb = (JFXRadioButton) userType.getSelectedToggle();
        String decision;
        System.out.println("this is the email " + email.getText());
        if (SignUp.isAlphanumeric(username.getText())) {
            if (!email.getText().isEmpty() && SignUp.emailInputSanitization(email.getText())) {
                if (!password.getText().isEmpty()) {
                    if (password.getText().equals(password_prime.getText())) {
                        decision = SignUp.CheckSignUp(email.getText(), username.getText());
                        if (decision.equals("OK") && rb.getText().equals(radioCandidate.getText())) {
                            String myData = SignUp.ContinueAsCandidate(email.getText());
                            Candidate cdt = new Candidate();
                            cdt.setUsername(username.getText());
                            cdt.setFirstName(firstname.getText());
                            cdt.setLastName(lastname.getText());
                            cdt.setPassword(password.getText().getBytes());
                            cdt.setEmail(email.getText());
                            //!!!!!!!!!WATCH OUT!!!!!!!!!!!
                            //!!!!!THIS IS A DANGEROUS TRICK TO COLLECT CONFIRMATION CODE SENT TO THE CANDIDATE!!!!
                            cdt.setIntroduction(myData);
                            FXRouter.goTo("EmailConfirmView", cdt);

                        } else if (decision.equals("OK") && rb.getText().equals(radioCompanyAdmin.getText())) {
                            FXRouter.when("CompanyAdminVerifyView", "AdminEntrepriseVerif.fxml");
                            FXRouter.setRouteContainer("CompanyAdminVerifyView", parentAnchorPane);
                            CompanyAdmin companyAdmin = SignUp.collectInfoAsCompanyAdmin(username.getText(),
                                    email.getText(), password.getText(),
                                    firstname.getText(), lastname.getText());
                            FXRouter.goTo("CompanyAdminVerifyView", companyAdmin);
                        } else if (decision.equals("OK") && rb.getText().equals(radioEmployee.getText())) {
                            FXRouter.when("HRVerifyView", "HREntrepriseVerif.fxml");
                            FXRouter.setRouteContainer("HRVerifyView", parentAnchorPane);
                            HRManager hrManager = SignUp.collectInfoHRManager(username.getText(),
                                    email.getText(), password.getText(),
                                    firstname.getText(), lastname.getText());
                            FXRouter.goTo("HRVerifyView", hrManager);
                        }

                    } else {
                        signUpLabel.setText("Passwords do not match");
                    }

                } else {
                    signUpLabel.setText("The password field is mandatory");
                }
            } else {
                signUpLabel.setText("The Email field is mandatory");
            }
        } else {
            signUpLabel.setText("The username must be alplanumeric");
        }

    }

    @FXML
    private void gobackClicked(MouseEvent event) throws IOException {
        FXRouter.when("LoginView", "Login.fxml", "Login");
        FXRouter.setRouteContainer("LoginView", parentAnchorPane);
        FXRouter.goTo("LoginView", parentAnchorPane);
    }
}
