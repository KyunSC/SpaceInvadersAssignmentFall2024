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
 */
public class MainAppFXMLController {

    private final static Logger logger = LoggerFactory.getLogger(MainAppFXMLController.class);
    @FXML
    private Pane animationPanel;
    @FXML
    private StackPane stackPane;

    private Stage primaryStage;
    private double elapsedTime = 0;
    private Player spaceShip;
    private Scene mainScene;
    AnimationTimer gameLoop;
    private boolean shootDelay = false;
    private double shootDelayTime = 0;
    private boolean shooting = false;
    private ArrayList<Projectile> projectileArrayList = new ArrayList<>();
    private ArrayList<Invader> invaderArrayList = new ArrayList<>();
    private int score = 0;
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
    public void initialize() {
        introBox.setStyle("-fx-background-color: black");
        mainMenuMusic.setCycleCount(Animation.INDEFINITE);
        mainMenuMusic.play();
        start.setOnAction(event -> setupGameWorld());
    }

    public void setupGameWorld() {
        stackPane.getChildren().remove(introBox);
        mainMenuMusic.stop();
        GameEngine gameEngine = new GameEngine(primaryStage, animationPanel, HUD, levelLabel, scoreLabel, mainScene, livesLabel, restartButton, stackPane);
    }

    public void setScene(Scene scene) {
        mainScene = scene;
    }

    public void stopAnimation() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
}
