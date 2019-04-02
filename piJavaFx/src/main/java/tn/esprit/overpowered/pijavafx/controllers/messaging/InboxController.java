/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers.messaging;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.controlsfx.control.textfield.TextFields;
import tn.esprit.overpowered.byusforus.entities.messaging.Message;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.User;
import tn.esprit.overpowered.byusforus.services.messaging.MessagingRemote;
import util.authentication.Authenticator;
import util.cache.ContextCache;
import util.messsages.MessageDelegate;
import util.messsages.MessageSender;

/**
 * FXML Controller class
 *
 * @author aminos
 */
public class InboxController implements Initializable {

    @FXML
    private JFXButton newMessageButton;
    @FXML
    private VBox conversationList;
    @FXML
    private VBox messageList;
    @FXML
    private JFXTextArea enterMessageArea;
    @FXML
    private JFXButton sendButton;
    @FXML
    private JFXTextField userNameText;

    Context messageContext;
    HashMap<String, User> contacts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Message> myMessages = MessageDelegate.getMyMessages(Authenticator.currentUser.getId());
        updateMessageList(myMessages);

    }

    private void updateMessageList(ArrayList<Message> myMessages) {

    }

    @FXML
    private void newMessage(ActionEvent event) {
        messageList.getChildren().clear();
        userNameText.setStyle("-fx-text-outer-color: red;");
        if (Authenticator.currentUser.getDiscriminatorValue().equals("CANDIDATE")) {
            ((Candidate) Authenticator.currentUser).getContacts().forEach((e) -> {
                contacts.put(e.getFirstName() + " " + e.getLastName() + "@" + e.getUsername(), e);
            });
            TextFields.bindAutoCompletion(userNameText, contacts.keySet());
        }
    }

    @FXML
    private void sendMessage(ActionEvent event) throws NamingException {
        Message m = new Message();
        m.setText(enterMessageArea.getText());
        m.setTo(contacts.get(userNameText.getText()));
        MessageDelegate.sendMessage(m);
    }

    @FXML
    private void sendRandomMessage(ActionEvent event) throws NamingException {
        MessageSender.send();
    }

}
