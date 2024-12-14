package edu.vanier.spaceshooter.controllers;

import edu.vanier.spaceshooter.entities.Invader;
import edu.vanier.spaceshooter.entities.Player;
import edu.vanier.spaceshooter.entities.Projectile;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class of the MainApp UI.
 * Animation pane is used for the sprites
 * StackPane is used for UI/HUD
 * AudioClip mainMenuMusic is played indefinitely until the start button is pressed
 * Start Button
 * Stop Button
 *
 */
public class MainAppFXMLController {

    private final static Logger logger = LoggerFactory.getLogger(MainAppFXMLController.class);
    @FXML
    private Pane animationPanel;
    @FXML
    private StackPane stackPane;

    private Stage primaryStage;
    private Scene mainScene;
    AnimationTimer gameLoop;
    private AudioClip mainMenuMusic = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/mainMenuMusic.mp3")).toExternalForm());

    @FXML
    private Label levelLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label livesLabel;
    @FXML
    private Button restartButton;
    @FXML
    private VBox HUD;
    @FXML
    private VBox introBox;
    @FXML
    private Button start;
    @FXML
    private Button exit;


    /**
     * Background color to black
     * main music plays indefinitely until play or exit button is pressed
     * SetFocusTraversable to prevent space bar from activating the button
     * Start button calls setUpGameWorld() Method
     * Exit closes the application
     *
     */
    @FXML
    public void initialize() {
        introBox.setStyle("-fx-background-color: black");
        mainMenuMusic.setCycleCount(Animation.INDEFINITE);
        mainMenuMusic.play();
        start.setOnAction(event -> setupGameWorld());
        start.setFocusTraversable(false);
        exit.setOnAction(event -> primaryStage.hide());
        exit.setFocusTraversable(false);
    }

    /**
     *
     * When start is pressed, it removes the introBox VBox
     * Then it stops the main menu music
     * THen it creates a new gameEngine which starts the game
     */
    public void setupGameWorld() {
        stackPane.getChildren().remove(introBox);
        mainMenuMusic.stop();
        GameEngine gameEngine = new GameEngine(primaryStage, animationPanel, HUD, levelLabel, scoreLabel, mainScene, livesLabel, restartButton, stackPane);
    }

    /**
     *
     * @param scene
     * sets the scene
     */
    public void setScene(Scene scene) {
        mainScene = scene;
    }

    /**
     * Stops the gameLoop
     */
    public void stopAnimation() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }

    /**
     * Sets the stage
     * @param primaryStage
     */
    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
}
