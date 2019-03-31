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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            String jndiName = "piJEE-ejb-1.0/ReclamationService!tn.esprit.overpowered.byusforus.services.Reclamation.ReclamationRemote";
            Context context = null;
            try {
                context = new InitialContext();
            } catch (NamingException ex) {
                Logger.getLogger(CreateClaimController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ReclamationRemote reclamationRemote = (ReclamationRemote) context.lookup(jndiName);
            addButton.setOnAction((event) -> {
                Reclamation r = new Reclamation();
                r.setFichier_a_joindre("fichier");
                r.setDescription(nameLabel.getText());
                r.setDateReclamation(new Date());
                r.setEtat(EtatReclamation.Traité);
                r.setType(TypeReclamation.Autre);
                reclamationRemote.addReclamation(r);
            });
        } catch (NamingException ex) {
            Logger.getLogger(CreateClaimController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onAddButtonClicked(ActionEvent event) {
    }

}
