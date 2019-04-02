/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.messsages;

import tn.esprit.overpowered.byusforus.entities.messaging.Message;
import tn.esprit.overpowered.byusforus.services.messaging.MessagingRemote;
import util.cache.ContextCache;

/**
 *
 * @author aminos
 */
public class MessageDelegate {

    public static MessagingRemote getRemote() {
        return (MessagingRemote) ContextCache
                .getInstance()
                .getProxy("piJEE-ejb-1.0/Messaging!tn.esprit.overpowered.byusforus.services.messaging.MessagingRemote");
    }
    
    public static void sendMessage(Message m) {
        getRemote().sendMessage(m);
    }
}
