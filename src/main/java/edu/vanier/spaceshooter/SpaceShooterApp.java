package edu.vanier.spaceshooter;

import edu.vanier.spaceshooter.controllers.MainAppFXMLController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Controller that handles the main menu
 */
public class SpaceShooterApp extends Application {

    private final static Logger logger = LoggerFactory.getLogger(SpaceShooterApp.class);
    MainAppFXMLController controller;

    /**
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * Loads the FXML file
     * Creates a new Scene which is 1568 x 1080
     * Sets the scene and shows it
     * Scene has a listener where if F is pressed then it sets to full screen
     *
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            logger.info("Bootstrapping the application...");
            //-- 1) Load the scene graph from the specified FXML file and 
            // associate it with its FXML controller.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainApp_layout.fxml"));
            controller = new MainAppFXMLController();
            loader.setController(controller);
            StackPane root = loader.load();
            //-- 2) Create and set the scene to the stage.
            Scene scene = new Scene(root, 1568, 1080);
            controller.setScene(scene);
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Space Invaders!");
            primaryStage.sizeToScene();
            primaryStage.show();
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.F) primaryStage.setFullScreen(true);
            });

        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @throws Exception
     * Calls stop animation in the mainAppFXMLController
     */
    @Override
    public void stop() throws Exception {
        // Stop the animation timer upon closing the main stage.
        controller.stopAnimation();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
