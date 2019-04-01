/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.overpowered.byusforus.entities.reclamation.EtatReclamation;
import tn.esprit.overpowered.byusforus.entities.reclamation.Reclamation;
import tn.esprit.overpowered.byusforus.entities.reclamation.TypeReclamation;
import tn.esprit.overpowered.byusforus.services.Reclamation.ReclamationRemote;
import util.proxies.claim.Claim;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class CreateClaimController implements Initializable {

    @FXML
    private Button addButton;
    @FXML
    private JFXTextField nameLabel;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            // TODO// let me give u and advice. its better to do the jndi thing else where. cause i dont like being obliged to use the try and catch thing



        }

        @FXML
        private void onAddButtonClicked  (ActionEvent event) throws NamingException {
                Reclamation r = new Reclamation();
            r.setFichier_a_joindre("fichier");
            r.setDescription(nameLabel.getText());
            r.setDateReclamation(new Date());
            r.setEtat(EtatReclamation.Traite);
            r.setType(TypeReclamation.Autre);
            Claim.createClaim(r);
        }
        
        

}
