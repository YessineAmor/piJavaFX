/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dataAbstraction.messaging;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;
import tn.esprit.overpowered.byusforus.entities.messaging.Message;

/**
 *
 * @author aminos
 */
public class Conversation {
    private ArrayList<Message> messages;
    
    public void addMessage(Message m) {
        this.messages.add(m);
    }
    
    public void addAll(Collection<Message> msgC) {
        this.messages.addAll(msgC);
    }
    
    public void sort(Comparator<Message> c) {
        messages.sort(c);
    }
    
    public void orderBySentDate() {
        sort((Message m1, Message m2)-> m1.getSentTime().compareTo( m2.getSentTime()));
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
