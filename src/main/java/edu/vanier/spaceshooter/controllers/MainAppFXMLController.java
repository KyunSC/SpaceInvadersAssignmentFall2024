package edu.vanier.spaceshooter.controllers;

import edu.vanier.spaceshooter.entities.Invader;
import edu.vanier.spaceshooter.entities.Player;
import edu.vanier.spaceshooter.entities.Projectile;
import edu.vanier.spaceshooter.models.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    public void initialize() {

    }

    public void setupGameWorld() {
        GameEngine gameEngine = new GameEngine(animationPanel, HUD, levelLabel, scoreLabel, mainScene, livesLabel, restartButton, stackPane);
    }

    /*private void initShootingDelay(){
        AnimationTimer delay = new AnimationTimer() {
            @Override
            public void handle(long now) {
                shootingDelay();
            }
        };
        delay.start();
    }

    private void shootingDelay(){
        if (shootDelayTime <= 0.48 && shootDelay) shootDelayTime += 0.016;
        else {
            shootDelayTime = 0;
            shootDelay = false;
        }
    }

    *//**
     * Sets up the key press event handler for the main scene.
     * <p>
     * This handler listens for specific key presses and executes corresponding
     * actions:
     * <ul>
     * <li>Pressing 'A' moves the spaceship to the left.</li>
     * <li>Pressing 'D' moves the spaceship to the right.</li>
     * <li>Pressing the SPACE key triggers the spaceship to shoot.</li>
     * </ul>
     * </p>
     *//*
    private void setupKeyPressHandlers() {
        // e the key event containing information about the key pressed.
        mainScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case KeyCode.A: spaceShip.setLeft(true);break;
                case KeyCode.D: spaceShip.setRight(true);break;
                case KeyCode.W: spaceShip.setUp(true);break;
                case KeyCode.S: spaceShip.setDown(true);break;
                case KeyCode.SPACE: shooting = true;
            }
        });

        mainScene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            switch (event.getCode()) {
                case KeyCode.A: spaceShip.setLeft(false); break;
                case KeyCode.D: spaceShip.setRight(false); break;
                case KeyCode.W: spaceShip.setUp(false); break;
                case KeyCode.S: spaceShip.setDown(false); break;
                case KeyCode.SPACE: shooting = false;
            }
        });
    }

    *//**
     * Retrieves a list of all sprites currently in the animation panel.
     * <p>
     * This method iterates through the children of the animation panel and
     * collects those that are instances of {@link Sprite} into a list.
     * </p>
     *
     * @return A list of {@link Sprite} objects found in the animation panel.
     *//*
    private List<Sprite> getSprites() {
        List<Sprite> spriteList = new ArrayList<>();
        for (Node n : animationPanel.getChildren()) {
            if (n instanceof Sprite sprite) {
                // We should add to the list any node that is a Sprite object.
                spriteList.add(sprite);
            }
        }
        return spriteList;
    }

    private void processProjectiles(){
        for (int i = 0; i < projectileArrayList.size(); i++) {
            if (Objects.equals(projectileArrayList.get(i).getType(), "enemybullet")) {
                handleEnemyBullet(projectileArrayList.get(i));
            }
            else if (Objects.equals(projectileArrayList.get(i).getType(), "playerbullet")) handlePlayerBullet(projectileArrayList.get(i));
        }
    }

    private void handleEnemyBullet(Projectile projectile) {
        projectile.moveDown();
        // Check for collision with the spaceship
        if (projectile.getSprite().getBoundsInParent().intersects(spaceShip.getSprite().getBoundsInParent())) {
            spaceShip.getSprite().setDead(true);
            projectile.getSprite().setDead(true);
            spaceShip.setDead(true);
        }
    }

    private void handlePlayerBullet(Projectile projectile) {
        projectile.moveUp();
        for (int i = 0; i < invaderArrayList.size(); i++) {
            if (projectile.getSprite().getBoundsInParent().intersects(invaderArrayList.get(i).getBoundsInParent())) {
                invaderArrayList.get(i).getSprite().setDead(true);
                invaderArrayList.get(i).setDead(true);
                invaderArrayList.remove(i);
                projectile.getSprite().setDead(true);
                score++;
                scoreLabel.setText("Score: " + score);
            }
        }
    }

    *//**
     * Removes all dead sprites from the animation panel.
     * <p>
     * This method iterates through the children of the animation panel and
     * removes any sprite that is marked as dead. It utilizes a lambda
     * expression to filter out the dead sprites efficiently.
     * </p>
     *//*
    private void removeDeadSprites() {
        animationPanel.getChildren().removeIf(n -> {
            Sprite sprite = (Sprite) n;
            return sprite.isDead();
        });
    }*/

    public void setScene(Scene scene) {
        mainScene = scene;
    }

    public void stopAnimation() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }
}
