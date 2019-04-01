/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.authentication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.overpowered.byusforus.entities.authentication.Session;
import tn.esprit.overpowered.byusforus.entities.users.User;
import tn.esprit.overpowered.byusforus.services.authentication.AuthenticationFacadeRemote;

/**
 *
 * @author aminos
 */
public class Authenticator {

    public static User currentUser;
    public static Session currentSession;

    public static String authenticate(String username, String password) throws NamingException, NoSuchAlgorithmException, IOException {
        String jndiName = "piJEE-ejb-1.0/AuthenticationFacade!tn.esprit.overpowered.byusforus.services.authentication.AuthenticationFacadeRemote";
        Context context = new InitialContext();
        AuthenticationFacadeRemote authenticator = (AuthenticationFacadeRemote) context.lookup(jndiName);
        return authenticator.login(username, password);
    }

    public static Session finalizeAuth(String uid, String token) throws NamingException {
        String jndiName = "piJEE-ejb-1.0/AuthenticationFacade!tn.esprit.overpowered.byusforus.services.authentication.AuthenticationFacadeRemote";
        Context context = new InitialContext();
        AuthenticationFacadeRemote authenticator = (AuthenticationFacadeRemote) context.lookup(jndiName);
        return authenticator.finalizeLogin(uid, token);
    }
}
