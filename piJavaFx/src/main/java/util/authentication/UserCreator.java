/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.authentication;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.overpowered.byusforus.entities.users.User;
import tn.esprit.overpowered.byusforus.services.users.UserFacadeRemote;
import util.cache.ContextCache;

/**
 *
 * @author aminos
 */
public class UserCreator {

    public static void main(String[] args) throws NamingException, NoSuchAlgorithmException {
        User u = new User();
        u.setEmail("Amine.skhiri@protonmail.com");
        u.setPassword("1234@".getBytes(StandardCharsets.UTF_8));
        u.setUsername("amine");
        getRemote().create(u);
        System.out.println("Done");
    }
    
        public static UserFacadeRemote getRemote() {
        return (UserFacadeRemote) ContextCache
                .getInstance()
                .getProxy("piJEE-ejb-1.0/UserFacade!tn.esprit.overpowered.byusforus.services.users.UserFacadeRemote");
    }
}
