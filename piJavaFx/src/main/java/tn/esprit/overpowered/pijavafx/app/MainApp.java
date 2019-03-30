package tn.esprit.overpowered.pijavafx.app;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
// **** Use as ref ****
//        String jndiName = "piJEE-ejb-1.0/ChoiceFacade!tn.esprit.overpowered.byusforus.services.ChoiceFacadeRemote";
//        Context context = new InitialContext();
//        ChoiceFacadeRemote choiceFacadeProxy = (ChoiceFacadeRemote) context.lookup(jndiName);
//        System.out.println("Starting choice creation...");
//        Choice choice1 = new Choice();
//        choice1.setChoicePoints(1);
//        choice1.setChoiceText("Your choice");
//        choice1.setIsCorrectChoice(Boolean.TRUE);
//        choiceFacadeProxy.create(choice1);
//        System.out.println("Completed choice creation");
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
        String loginFxmlFile = "/fxml/Login.fxml";
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
        });

    }
}
