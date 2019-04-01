package tn.esprit.overpowered.pijavafx.app;

import LicenceManager.LicenceFacadeRemote;
import Licenses.Licence;
import java.nio.charset.Charset;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.User;
import tn.esprit.overpowered.byusforus.services.candidat.CandidateFacadeRemote;
import tn.esprit.overpowered.byusforus.services.users.UserFacadeRemote;
import util.routers.FXRouter;

// *** User as ref ****
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import tn.esprit.overpowered.byusforus.entities.Choice;
//import tn.esprit.overpowered.byusforus.services.ChoiceFacadeRemote;
public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    private static final double WIN_WIDTH = 600;
    private static final double WIN_HEIGHT = 400;
    private static final String FXML_PATH = "/fxml/";

    public static void main(String[] args) throws Exception {
        /*
       //String jndiName = "piJEE-ejb-1.0/CandidateFacade!tn.esprit.overpowered.byusforus.services.candidat.CandidateFacadeRemote";
       String jndiName = "piJEE-ejb-1.0/UserFacade!tn.esprit.overpowered.byusforus.services.users.UserFacadeRemote";
      Context context = new InitialContext();
       UserFacadeRemote userProxy = (UserFacadeRemote) context.lookup(jndiName);
       System.out.println("Starting choice creation...");
       User cdt = new User();
       cdt.setEmail("motaz@esprit.tn");
       //cdt.setIntroduction("Intro");
       //cdt.setPassword("password".getBytes());
       cdt.setUsername("Skeez");
        System.out.println("your response "+userProxy.checkExistence(cdt.getEmail(), cdt.getUsername()));
       
       //Long userId = userProxy.create(cdt);
       /*cdt.setId(candidateId);
       
       Experience exp = new Experience();
       exp.setPosition("Ingenieur");
       Long expId = candidateProxy.createExperience(exp);
       exp.setId(expId);
       candidateProxy.affecterExperienceCandidate(expId, candidateId);*/
      /*
        int length = 5;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        System.out.println(generatedString);
*//*
      String jndiName = "piJEE-ejb-1.0/CandidateFacade!tn.esprit.overpowered.byusforus.services.candidat.CandidateFacadeRemote";
            Context context = new InitialContext();
            CandidateFacadeRemote candidateProxy = (CandidateFacadeRemote) context.lookup(jndiName);
            ObservableList<Candidate> list = candidateProxy.getObservableCandidate();
      */
        //TESTING LICENCE SERVICE
       /* String jndiName = "piJEE-ejb-1.0/LicenceFacade!LicenceManager.LicenceFacadeRemote";
        Context context = new InitialContext();
        LicenceFacadeRemote licenceProxy = (LicenceFacadeRemote) context.lookup(jndiName);
        /*String licenceID = RandomStringUtils.random(length, useLetters, useNumbers);
        String adminuserPass = RandomStringUtils.random(length, useLetters, useNumbers);
        String hruserPass = RandomStringUtils.random(length, useLetters, useNumbers);
        Licence licence = new Licence();
        licence.setCompanyLicenceId(licenceID);
        licence.setCompanyName("BUFU");
        licence.setUserPass(adminuserPass);
        licence.setUserRole(Role.ADMIN);
        licenceProxy.createLicence(licence);
        licence.setUserPass(hruserPass);
        licence.setUserRole(Role.HR);
        licenceProxy.createLicence(licence);
        String licenceID2 = RandomStringUtils.random(length, useLetters, useNumbers);
        String adminuserPass2 = RandomStringUtils.random(length, useLetters, useNumbers);
        String hruserPass2 = RandomStringUtils.random(length, useLetters, useNumbers);
        Licence licence2 = new Licence();
        licence2.setCompanyLicenceId(licenceID2);
        licence2.setCompanyName("ESPRIT");
        licence2.setUserPass(adminuserPass2);
        licence2.setUserRole(Role.ADMIN);
        licenceProxy.createLicence(licence2);
        licence2.setUserPass(hruserPass2);
        licence2.setUserRole(Role.HR);
        licenceProxy.createLicence(licence2);*/
       /*
       String jndiName1 = "piJEE-ejb-1.0/CompanyAdminFacade!tn.esprit.overpowered.byusforus.services.users.CompanyAdminFacadeRemote";
       String jndiName = "piJEE-ejb-1.0/HRManagerFacade!tn.esprit.overpowered.byusforus.services.users.HRManagerFacadeRemote";
        Context context = new InitialContext();
       CompanyAdminFacadeRemote companyProxy = (CompanyAdminFacadeRemote) context.lookup(jndiName1);
       CompanyProfile comp = new CompanyProfile();
       comp.setName("bufuuu");
       Long companyId = companyProxy.createCompanyProfile(comp);
        HRManager hrm = new HRManager();
        hrm.setIntroduction("intro");
        hrm.setUsername("kebouuuuuuu");
        hrm.setPassword("password".getBytes());
        hrm.setEmail("moaaaaatez.souid@esprit.tn");
        hrm.setLastName("souid");
        
       //hrm.setCompanyProfile(comp);
        HRManagerFacadeRemote hrManagerProxy = (HRManagerFacadeRemote) context.lookup(jndiName);
        
        Long hrmId = hrManagerProxy.createHRManager(hrm);
        //hrManagerProxy.affecterHRtoCompany(hrmId, companyId);
*/
       
       //TRYING TO GET DISCRIMINATOR VALUE COLUMN FROM THE DATABASE
      /* 
       User user = new User();
       Candidate cdt = new Candidate();
       String jndiName = "piJEE-ejb-1.0/UserFacade!tn.esprit.overpowered.byusforus.services.users.UserFacadeRemote";
       Context context = new InitialContext();
       UserFacadeRemote userProxy = (UserFacadeRemote) context.lookup(jndiName);
        System.out.println(userProxy.getUserDiscriminatorValue(1L));
        System.out.println(user.getDiscriminatorValue());
        System.out.println(cdt.getDiscriminatorValue()); */
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        /*log.info("Starting Hello JavaFX and Maven demonstration application");
        String fxmlFile = "/fxml/Base.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        final Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add("/styles/styles.css");

        stage.sizeToScene();
        stage.setTitle("Hello JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(400);
        stage.setMinWidth(600);

        FXRouter.bind(this, stage, "By Us For Us", WIN_WIDTH, WIN_HEIGHT);


         */
        String signUpFxmlFile = "/fxml/Login.fxml";
        FXMLLoader loader = new FXMLLoader();
        final Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(signUpFxmlFile));
        Scene scene = new Scene(rootNode);
        FXRouter.scene = scene;
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(430);
        stage.setMinWidth(600);

        // Destroy everything on close requestp
        stage.setOnCloseRequest((event) -> {
            Platform.exit();
            System.exit(0);
        });

        //Testing candidate registration 
        /*String loginFxmlFile = "/fxml/Login.fxml";
        FXMLLoader loader = new FXMLLoader();
        final Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(loginFxmlFile));
        Scene scene = new Scene(rootNode);
        FXRouter.scene = scene;
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(430);
        stage.setMinWidth(600);

        // Destroy everything on close requestp
        stage.setOnCloseRequest((event) -> {
            Platform.exit();
            System.exit(0);
            });*/
    }
}
