/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class OffersController implements Initializable {

    @FXML
    private AnchorPane parentAnchorPane;
    @FXML
    private AnchorPane rightMenuAnchorPane;
    @FXML
    private Button profileButton;
    @FXML
    private AnchorPane topMenuAnchorPane;
    @FXML
    private MenuBar topMenu;
    @FXML
    private TableView<?> candidateView;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> lastname;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> recommendations;
    @FXML
    private TableColumn<?, ?> visits;
    @FXML
    private JFXButton viewProfile;
    @FXML
    private Label status;
    @FXML
    private Button searchButton;
    @FXML
    private Button newOfferButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void profileButtonClicked(MouseEvent event) {
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

    @FXML
    private void newOfferButtonOnclicked(MouseEvent event) {
    }
    
}
