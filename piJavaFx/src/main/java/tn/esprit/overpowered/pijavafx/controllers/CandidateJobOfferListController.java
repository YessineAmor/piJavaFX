/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.services.entrepriseprofile.JobOfferFacadeRemote;
import util.routers.FXRouter;

/**
 * FXML Controller class
 *
 * @author EliteBook
 */
public class CandidateJobOfferListController implements Initializable {

    @FXML
    private AnchorPane rightMenuAnchorPane;
    @FXML
    private Button homeButton;
    @FXML
    private Button profileButton;
    @FXML
    private AnchorPane topMenuAnchorPane;
    @FXML
    private MenuBar topMenu;
    @FXML
    private TableView<JobOffer> jobsView;
    @FXML
    private TableColumn<?, ?> title;
    @FXML
    private TableColumn<?, ?> offerStatus;
    @FXML
    private TableColumn<?, ?> dateOfCreation;
    @FXML
    private TableColumn<?, ?> city;
    @FXML
    private TableColumn<?, ?> dateOfArchive;
    @FXML
    private TableColumn<?, ?> peopleNeeded;
    @FXML
    private JFXButton viewProfile;
    @FXML
    private Label status;
    @FXML
    private Button searchButton;
    @FXML
    private AnchorPane parentAnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXRouter.when("CreateJobOfferView", "CreateJobOffer.fxml", "JobOffer", 640, 425);
        FXRouter.setRouteContainer("CreateJobOfferView", parentAnchorPane);
        FXRouter.when("CompanyHRProfileView", "CompanyHRProfile.fxml", "Profile", 600, 400);
        FXRouter.setRouteContainer("CompanyHRProfileView", parentAnchorPane);
        FXRouter.when("CompanyPMProfileView", "CompanyPMProfile.fxml", "Profile", 600, 400);
        FXRouter.setRouteContainer("CompanyPMProfileView", parentAnchorPane);
        FXRouter.when("BaseView", "Base.fxml", "HOME", 800, 600);
        FXRouter.setRouteContainer("BaseView", parentAnchorPane);
        try {
            String jndiName = "piJEE-ejb-1.0/JobOfferFacade!tn.esprit.overpowered.byusforus.services.entrepriseprofile.JobOfferFacadeRemote";
            Context context = new InitialContext();
            JobOfferFacadeRemote jobOfferProxy = (JobOfferFacadeRemote) context.lookup(jndiName);
            List<JobOffer> list = jobOfferProxy.viewAllOffers();
            if (list.isEmpty()) {
                System.out.println("EMPTY");
            }
            //System.out.println("THE LOCATION ISSSSSSSSSS: " + list.get(0).getCity());
            ObservableList<JobOffer> offerObs = FXCollections.observableArrayList();

            for (JobOffer o : list) {
                offerObs.add(o);
            }
            title.setCellValueFactory(new PropertyValueFactory<>("Title"));
            offerStatus.setCellValueFactory(new PropertyValueFactory<>("offerStatus"));
            dateOfCreation.setCellValueFactory(new PropertyValueFactory<>("dateOfCreation"));
            city.setCellValueFactory(new PropertyValueFactory<>("city"));
            dateOfArchive.setCellValueFactory(new PropertyValueFactory<>("dateOfArchive"));
            peopleNeeded.setCellValueFactory(new PropertyValueFactory<>("peopleNeeded"));
            System.out.println("Still working at this point");
            jobsView.setItems(offerObs);

        } catch (NamingException ex) {
            Logger.getLogger(CandidateListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void homeButtonOnClicked(MouseEvent event) throws IOException {
        FXRouter.when("BaseView", "Base.fxml");
        FXRouter.setRouteContainer("BaseView", parentAnchorPane);
        FXRouter.goTo("BaseView");
    }

    @FXML
    private void profileButtonClicked(MouseEvent event) throws IOException {
        FXRouter.when("ProfileViews", "Profile.fxml");
        FXRouter.setRouteContainer("ProfileView", parentAnchorPane);
        FXRouter.goTo("ProfileView");
    }

    @FXML
    private void selected(MouseEvent event) {
    }

    @FXML
    private void viewProfileAction(MouseEvent event) {
    }

    @FXML
    private void searchButtonClicked(MouseEvent event) {
    }

}
