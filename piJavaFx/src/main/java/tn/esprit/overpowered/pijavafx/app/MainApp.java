package tn.esprit.overpowered.pijavafx.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.routers.FXRouter;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    private static final double WIN_WIDTH = 800;
    private static final double WIN_HEIGHT = 600;
    private static final String FXML_PATH = "/fxml/";

    public static void main(String[] args) throws Exception {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        //Testing candidate registration 
        String loginFxmlFile = "/fxml/Login.fxml";
        FXMLLoader loader = new FXMLLoader();
        final Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(loginFxmlFile));
        Scene scene = new Scene(rootNode);
        FXRouter.scene = scene;
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(400);
        stage.setMinWidth(600);

        // Destroy everything on close requestp
        stage.setOnCloseRequest((event) -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
