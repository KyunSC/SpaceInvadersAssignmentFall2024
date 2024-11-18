package edu.vanier.spaceshooter.controllers;

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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
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
    private double elapsedTime = 0;
    private Player spaceShip;
    private Scene mainScene;
    AnimationTimer gameLoop;
    private boolean shootDelay = false;
    private double shootDelayTime = 0;
    private ArrayList<Projectile> projectileArrayList = new ArrayList<>();
    private boolean shooting = false;

    @FXML
    public void initialize() {
        logger.info("Initializing MainAppController...");
        spaceShip = new Player(new Sprite(300, 750, 40, 40, "player", Color.BLUE));
        animationPanel.setPrefSize(600, 800);
        animationPanel.getChildren().add(spaceShip.getSprite());
    }

    public void setupGameWorld() {
        initGameLoop();
        setupKeyPressHandlers();
        generateInvaders();
        initShootingDelay();
    }

    private void initGameLoop() {
        // Create the game loop.
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                spaceShip.moveUp();
                spaceShip.moveDown();
                spaceShip.moveLeft();
                spaceShip.moveRight();
                shoot(spaceShip.getSprite());
            }
        };
        gameLoop.start();
    }

    private void initShootingDelay(){
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

    /**
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
     */
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

    private void generateInvaders() {
        for (int i = 0; i < 5; i++) {
            Sprite invader = new Sprite(
                    90 + i * 100,
                    150, 30, 30, "enemy",
                    Color.RED);
            animationPanel.getChildren().add(invader);
        }
    }

    /**
     * Retrieves a list of all sprites currently in the animation panel.
     * <p>
     * This method iterates through the children of the animation panel and
     * collects those that are instances of {@link Sprite} into a list.
     * </p>
     *
     * @return A list of {@link Sprite} objects found in the animation panel.
     */
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

    /**
     * Updates the game state for each frame.
     * <p>
     * This method increments the elapsed time and processes each sprite based
     * on its type. It handles the movement and collision detection for enemy
     * bullets and player bullets, as well as the shooting behavior for enemies.
     * Dead sprites are removed from the animation panel, and the elapsed time
     * is reset after a certain threshold.
     * </p>
     */
    private void update() {
        elapsedTime += 0.016;
        // Actions to be performed during each frame of the animation.
        getSprites().forEach(this::processSprite);
        processProjectiles();
        removeDeadSprites();

        // Reset the elapsed time.
        if (elapsedTime > 2) {
            elapsedTime = 0;
        }
    }

    private void processSprite(Sprite sprite) {
        switch (sprite.getType()) {
            /*case "enemybullet" ->
                handleEnemyBullet(sprite);
            case "playerbullet" ->
                handlePlayerBullet(sprite);*/
            case "enemy" ->
                handleEnemyFiring(sprite);
        }
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
        if (projectile.getSprite().getBoundsInParent().intersects(spaceShip.getBoundsInParent())) {
            spaceShip.setDead(true);
            projectile.getSprite().setDead(true);
        }
    }

    private void handlePlayerBullet(Projectile projectile) {
        projectile.moveUp();
        for (Sprite enemy : getSprites()) {
            if (enemy.getType().equals("enemy")) {
                // Check for collision with an enemy
                if (projectile.getSprite().getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                    enemy.setDead(true);
                    projectile.getSprite().setDead(true);
                }
            }
        }
    }

    private void handleEnemyFiring(Sprite sprite) {
        if (elapsedTime > 2) {
            if (Math.random() < 0.3) {
                shoot(sprite);
            }
        }
    }

    /**
     * Removes all dead sprites from the animation panel.
     * <p>
     * This method iterates through the children of the animation panel and
     * removes any sprite that is marked as dead. It utilizes a lambda
     * expression to filter out the dead sprites efficiently.
     * </p>
     */
    private void removeDeadSprites() {
        animationPanel.getChildren().removeIf(n -> {
            Sprite sprite = (Sprite) n;
            return sprite.isDead();
        });
    }

    /**
     * Creates and adds a bullet sprite fired by the specified entity.
     * <p>
     * The firing entity can be either an enemy or the spaceship. A bullet is
     * created at the position of the firing entity with a slight offset to the
     * right. The bullet's dimensions are set, and it is given a type based on
     * the firing entity's type.
     * </p>
     *
     * @param firingEntity The entity that is firing the bullet, which can be
     * either an enemy or the spaceship.
     */
    private void shoot(Sprite firingEntity) {
        if (!shootDelay && !spaceShip.isDead()){
            // The firing entity can be either an enemy or the spaceship.
            if (Objects.equals(firingEntity.getType(), "enemy")) {
                Projectile bullet = new Projectile(new Sprite(
                        (int) firingEntity.getTranslateX() + 20,
                        (int) firingEntity.getTranslateY(),
                        5, 20,
                        firingEntity.getType() + "bullet", Color.BLACK));
                projectileArrayList.add(bullet);
                animationPanel.getChildren().add(bullet.getSprite());
                shootDelay = true;
            }
            else if (Objects.equals(firingEntity.getType(), "player") && shooting){
                Projectile bullet = new Projectile(new Sprite(
                        (int) firingEntity.getTranslateX() + 20,
                        (int) firingEntity.getTranslateY(),
                        5, 20,
                        firingEntity.getType() + "bullet", Color.BLACK));
                projectileArrayList.add(bullet);
                animationPanel.getChildren().add(bullet.getSprite());
                shootDelay = true;
            }
        }
    }

    public void setScene(Scene scene) {
        mainScene = scene;
    }

    public void stopAnimation() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }
}
