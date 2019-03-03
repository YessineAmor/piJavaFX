package tn.esprit.overpowered.pijavafx.app;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.factories.ChangeDimensions;
import util.factories.ChangeDimensionsFactory;

// *** User as ref ****
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import tn.esprit.overpowered.byusforus.entities.Choice;
//import tn.esprit.overpowered.byusforus.services.ChoiceFacadeRemote;
public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

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

    public void start(Stage stage) throws Exception {

        log.info("Starting Hello JavaFX and Maven demonstration application");

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

        // registering listeners for window resizing
        ChangeDimensionsFactory cFactory = new ChangeDimensionsFactory();
        ChangeListener<Number> sideMenuChangeListener = cFactory.createListener(
                rootNode, "#rightMenuAnchorPane", 1, ChangeDimensions.HEIGHT);
        ChangeListener<Number> topMenuAnchorPaneListener = cFactory.createListener(
                rootNode, "#topMenuAnchorPane", 1, ChangeDimensions.WIDTH);
        ChangeListener<Number> topMenuChangeListener = cFactory.createListener(
                rootNode, "#topMenu", 1, ChangeDimensions.WIDTH);
        stage.heightProperty().addListener(sideMenuChangeListener);
        stage.widthProperty().addListener(topMenuChangeListener);
        stage.widthProperty().addListener(topMenuAnchorPaneListener);

        
    }
}
