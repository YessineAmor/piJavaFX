/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
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
        // TODO
        FXRouter.setBaseCenterAnchorPane(centralAnchorPane);
    }

    @FXML
    private void onCreateQuizBtnClicked(ActionEvent event) throws IOException {
        FXRouter.goTo("CreateQuiz");
    }

    public AnchorPane getCentralAnchorPane() {
        return centralAnchorPane;
    }

    public void setCentralAnchorPane(AnchorPane centralAnchorPane) {
        this.centralAnchorPane = centralAnchorPane;
    }

}
