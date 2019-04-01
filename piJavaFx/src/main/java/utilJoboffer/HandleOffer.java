/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilJoboffer;

import java.util.List;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.HRManager;
import tn.esprit.overpowered.byusforus.entities.util.ExpertiseLevel;
import tn.esprit.overpowered.byusforus.entities.util.OfferStatus;
import tn.esprit.overpowered.byusforus.entities.util.Skill;
import tn.esprit.overpowered.byusforus.services.users.HRManagerFacadeRemote;
import util.authentication.Authenticator;

/**
 *
 * @author pc
 */
public class HandleOffer {

    public static void createJobOffer(String title,
            String location, ExpertiseLevel expertiseLevel,
            Set<Skill> skills,int nberOfpeopleNeeded) throws NamingException{
   String jndiName = "piJEE-ejb-1.0/HRManagerFacade!tn.esprit.overpowered.byusforus.services.users.HRManagerFacadeRemote";
    Context context = new InitialContext();
    HRManagerFacadeRemote hrManagerProxy = (HRManagerFacadeRemote)context.lookup(jndiName);
    
    JobOffer jobOffer = new JobOffer();
    jobOffer.setTitle(title);
    jobOffer.setCity(location);
    jobOffer.setExpertiseLevel(expertiseLevel);
    jobOffer.setPeopleNeeded(nberOfpeopleNeeded);
    jobOffer.setSkills(skills);
    jobOffer.setOfferStatus(OfferStatus.AVAILABLE);
    Long hrId = Authenticator.currentUser.getId();
    hrManagerProxy.createOffer(hrId, jobOffer);
    }
    
}
