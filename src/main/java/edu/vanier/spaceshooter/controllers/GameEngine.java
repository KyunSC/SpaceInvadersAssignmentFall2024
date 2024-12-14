package edu.vanier.spaceshooter.controllers;

import edu.vanier.spaceshooter.entities.*;
import edu.vanier.spaceshooter.models.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * elapsed time used in the timing of the movement and shooting of the invaders
 * gameloop for the updates in real time
 * shootdelay that adds a certain amount of time before next shot
 * Arraylists to store the invaders, MediumInvaders, LargeInvaders and the Projectiles
 * Boss which starts null
 * shooting boolean to make the automatic shooting
 * labels that are updated in real time
 * all audio and images are initiated to be used at specific moments
 *
 */
public class GameEngine {
    private final static Logger logger = LoggerFactory.getLogger(MainAppFXMLController.class);
    private double elapsedTime = 0;
    private Player spaceShip;
    private Scene mainScene;
    private Stage primaryStage;
    AnimationTimer gameLoop;
    AnimationTimer delay;
    private boolean shootDelay = false;
    private double shootDelayTime = 0;
    private boolean shooting = false;
    private ArrayList<Projectile> projectileArrayList = new ArrayList<>();
    private ArrayList<Invader> invaderArrayList = new ArrayList<>();
    private ArrayList<MediumInvader> mediumInvaderArrayList = new ArrayList<>();
    private ArrayList<LargeInvader> largeInvaderArraylist = new ArrayList<>();
    private ArrayList<Sprite> explosionArrayList = new ArrayList<>();
    private Boss boss;
    Pane animationPanel;
    StackPane stackPane;
    Label levelLabel;
    Label scoreLabel;
    Label livesLabel;
    Button restartButton;
    Image spaceship2 = new Image((Objects.requireNonNull(getClass().getResource("/images/ship2.png")).toExternalForm()));
    Image spaceship3 = new Image((Objects.requireNonNull(getClass().getResource("/images/ship3.png")).toExternalForm()));
    Image invaderImage = new Image((Objects.requireNonNull(getClass().getResource("/images/invader.png"))).toExternalForm());
    Image invaderInvertedImage = new Image((Objects.requireNonNull(getClass().getResource("/images/invaderInverted.png"))).toExternalForm());
    Image mediumInvaderImage = new Image((Objects.requireNonNull(getClass().getResource("/images/mediumInvader.png"))).toExternalForm());
    Image mediumInvaderInvertedImage = new Image((Objects.requireNonNull(getClass().getResource("/images/mediumInvaderInverted.png"))).toExternalForm());
    Image largeInvaderImage = new Image((Objects.requireNonNull(getClass().getResource("/images/largeInvader.png"))).toExternalForm());
    Image largeInvaderInvertedImage = new Image((Objects.requireNonNull(getClass().getResource("/images/largeInvaderInverted.png"))).toExternalForm());
    Image bossImage = new Image((Objects.requireNonNull(getClass().getResource("/images/boss.png"))).toExternalForm());
    Image explosion = new Image((Objects.requireNonNull(getClass().getResource("/images/explosionGIF.gif"))).toExternalForm());
    Image invaderBullet = new Image(Objects.requireNonNull(getClass().getResource("/images/invaderBullet.png")).toExternalForm());
    Image playerBullet = new Image(Objects.requireNonNull(getClass().getResource("/images/playerBullet.png")).toExternalForm());
    Image playerBulletPurple = new Image(Objects.requireNonNull(getClass().getResource("/images/playerBulletPurple.png")).toExternalForm());
    Image playerBulletGreen = new Image(Objects.requireNonNull(getClass().getResource("/images/playerBulletGreen.png")).toExternalForm());
    AudioClip playerShoot = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/blaster.mp3")).toExternalForm());
    AudioClip blasterSound2 = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/blaster2.mp3")).toExternalForm());
    AudioClip blasterSound3 = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/shoot.wav")).toExternalForm());
    AudioClip explosionSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/explosion.mp3")).toExternalForm());
    AudioClip mediumExplosionSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/mediumExplosion.mp3")).toExternalForm());
    AudioClip largeExplosionSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/largeExplosion.mp3")).toExternalForm());
    AudioClip backgroundMusic = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/backgroundMusic.mp3")).toExternalForm());
    AudioClip backgroundMusic2 = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/backgroundMusic2.mp3")).toExternalForm());
    AudioClip backgroundMusic3 = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/backgroundMusic3.mp3")).toExternalForm());
    AudioClip endMusic = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/end.mp3")).toExternalForm());
    AudioClip mediumHit = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/mediumHit.mp3")).toExternalForm());
    AudioClip largeHit = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/largeHit.mp3")).toExternalForm());
    AudioClip bossHit = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/bossHit.mp3")).toExternalForm());
    AudioClip playerHit = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/playerHit.mp3")).toExternalForm());
    AudioClip nextLevelSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/nextLevel.mp3")).toExternalForm());
    VBox gameOverVBox;
    VBox winBox;
    VBox hud;
    int level = 1;
    int firingMode;
    int previousScore = 0;
    String gameState = "playing";

    /**
     *
     * @param primaryStage stage of the window
     * @param animationPanel animating pane
     * @param HUD hud
     * @param levelLabel displays level number dynamically
     * @param scoreLabel displays the score dynamically updated every time invader is killed or boss is hit
     * @param mainScene scene used
     * @param livesLabel displays number of lives left
     * @param restartButton restart button that calls restart()
     * @param stackPane stackPane used by the HUD and UI
     *                  Creates the player
     *                  Calls setUpHUD and setUpGameWorld
     */
    public GameEngine(Stage primaryStage, Pane animationPanel, VBox HUD, Label levelLabel, Label scoreLabel, Scene mainScene, Label livesLabel, Button restartButton, StackPane stackPane) {
        logger.info("Initializing MainAppController...");
        this.animationPanel = animationPanel;
        this.stackPane = stackPane;
        this.mainScene = mainScene;
        this.hud = HUD;
        this.levelLabel = levelLabel;
        this.scoreLabel = scoreLabel;
        this.livesLabel = livesLabel;
        this.restartButton = restartButton;
        this.primaryStage = primaryStage;
        animationPanel.setPrefSize(1280, 720);
        spaceShip = new Player(stackPane.getWidth()/2, stackPane.getHeight() * 3 / 4, 40, 40, "player");
        animationPanel.getChildren().add(spaceShip.getSprite());
        setUpHud();

        setupGameWorld();
    }

    
    public void setupGameWorld() {
        generateInvaders();
        initGameLoop();
        setupKeyPressHandlers();
        initShootingDelay();
        initRestartButton();
        gameState = "playing";
        backgroundMusic.setCycleCount(Animation.INDEFINITE);
        backgroundMusic2.setCycleCount(Animation.INDEFINITE);
        backgroundMusic3.setCycleCount(Animation.INDEFINITE);
        backgroundMusic.stop();
        backgroundMusic2.stop();
        backgroundMusic3.stop();
        switch (level){
            case 1 -> backgroundMusic.play();
            case 2 -> backgroundMusic2.play();
            case 3 -> backgroundMusic3.play();
        }
    }

    private void setUpHud(){
        scoreLabel.setText("Score: " + spaceShip.getScore());
        livesLabel.setText("Lives: " + spaceShip.getLives());
        spaceShip.setStackPane(stackPane);
        stackPane.widthProperty().addListener((_, _, _) -> {
            spaceShip.setStackPane(stackPane);
            for (int i = 0; i < invaderArrayList.size(); i++) invaderArrayList.get(i).setStackPane(stackPane);
            for (int i = 0; i < mediumInvaderArrayList.size(); i++) mediumInvaderArrayList.get(i).setStackPane(stackPane);
            for (int i = 0; i < largeInvaderArraylist.size(); i++) largeInvaderArraylist.get(i).setStackPane(stackPane);
            if (boss!=null) boss.setStackPane(stackPane);
        });
        mainScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (Objects.requireNonNull(event.getCode()) == KeyCode.R) {
                restart();
            }
        });
    }

    private void initRestartButton() {
        restartButton.setFocusTraversable(false);
        restartButton.setOnAction(event -> restart());
    }

    private void restart(){
        gameState = "playing";
        backgroundMusic.stop();
        backgroundMusic2.stop();
        backgroundMusic3.stop();
        switch (level){
            case 1 -> backgroundMusic.play();
            case 2 -> backgroundMusic2.play();
            case 3 -> backgroundMusic3.play();
        }
        invaderArrayList.clear();
        mediumInvaderArrayList.clear();
        largeInvaderArraylist.clear();
        projectileArrayList.clear();
        animationPanel.getChildren().clear();
        stackPane.getChildren().remove(gameOverVBox);
        gameLoop.stop();
        delay.stop();
        spaceShip = new Player(stackPane.getWidth()/2, stackPane.getHeight() * 3 / 4, 40, 40, "player");
        spaceShip.setScore(previousScore);
        spaceShip.setStackPane(stackPane);
        animationPanel.getChildren().addAll(spaceShip.getSprite());
        hud.getChildren().remove(restartButton);
        hud.getChildren().add(restartButton);
        livesLabel.setText("Lives: " + spaceShip.getLives());
        scoreLabel.setText("Score: " + spaceShip.getScore());
        stopAnimation();
        setupGameWorld();
    }

    private void initGameLoop() {
        // Create the game loop.
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                shoot(spaceShip.getSprite());
                update();
                spaceShip.moveUp();
                spaceShip.moveDown();
                spaceShip.moveLeft();
                spaceShip.moveRight();
            }
        };
        gameLoop.start();
    }

    private void initShootingDelay(){
        delay = new AnimationTimer() {
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
     * <li>Pressing 'W' moves the spaceship up.</li>
     * <li>Pressing 'S' moves the spaceship down.</li>
     * <li>Pressing the SPACE key triggers the spaceship to shoot.</li>
     * </ul>
     * </p>
     */
    private void setupKeyPressHandlers() {
        // e the key event containing information about the key pressed.
        if (!Objects.equals(gameState, "paused")) {
            mainScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                switch (event.getCode()) {
                    case KeyCode.A:
                        spaceShip.setLeft(true);
                        break;
                    case KeyCode.D:
                        spaceShip.setRight(true);
                        break;
                    case KeyCode.W:
                        spaceShip.setUp(true);
                        break;
                    case KeyCode.S:
                        spaceShip.setDown(true);
                        break;
                    case KeyCode.SHIFT:
                        spaceShip.setSpeedUp(1.5);
                        break;
                    case KeyCode.E:
                        firingMode = changeFiringMode();
                        break;
                    case KeyCode.F:
                        for (int i = 0; i < invaderArrayList.size(); i++) {
                            invaderArrayList.get(i).setStackPane(stackPane);
                            spaceShip.setStackPane(stackPane);
                        }
                        break;
                    case KeyCode.SPACE: {
                        shooting = true;
                        break;
                    }
                }
            });

            mainScene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
                switch (event.getCode()) {
                    case KeyCode.A:
                        spaceShip.setLeft(false);
                        break;
                    case KeyCode.D:
                        spaceShip.setRight(false);
                        break;
                    case KeyCode.W:
                        spaceShip.setUp(false);
                        break;
                    case KeyCode.S:
                        spaceShip.setDown(false);
                        break;
                    case KeyCode.SHIFT:
                        spaceShip.setSpeedUp(1);
                        break;
                    case KeyCode.SPACE:
                        shooting = false;
                }
            });
        }
    }

    private int changeFiringMode() {
        if (level > 1) firingMode++;
        if (level == 2 && firingMode == 3) firingMode = 1;
        if (level > 2 && firingMode == 4) firingMode = 1;
        return firingMode;
    }

    private void generateInvaders() {
        if (level == 1) {
            for (int i = 0; i < 15; i++) {
                Image image;
                if (i % 2 == 0) image = invaderImage;
                else image = invaderInvertedImage;
                Invader invader = new Invader(
                        Math.random()*(stackPane.getWidth() - 200) + 100,
                        Math.random()*300 + 100, 30, 30, "enemy",
                        image, stackPane);
                invaderArrayList.add(invader);
                animationPanel.getChildren().add(invader.getSprite());
            }
        }
        else if (level == 2){
            for (int i = 0; i < 20; i++) {
                Image image;
                if (i % 2 == 0) image = mediumInvaderImage;
                else image = mediumInvaderInvertedImage;
                MediumInvader invader = new MediumInvader(
                        Math.random()*(stackPane.getWidth() - 200) + 100,
                        Math.random()*300 + 100, 40, 40, "enemy",
                        image, stackPane);
                mediumInvaderArrayList.add(invader);
                animationPanel.getChildren().add(invader.getSprite());
            }
        }
        else {
            for (int i = 0; i < 30; i++) {
                Image image;
                if (i % 2 == 0) image = largeInvaderImage;
                else image = largeInvaderInvertedImage;
                LargeInvader invader = new LargeInvader(
                        (int) (Math.random()*(stackPane.getWidth() - 100)),
                        (int) (Math.random()*500), 50, 50, "enemy",
                        image, stackPane);
                largeInvaderArraylist.add(invader);
                animationPanel.getChildren().add(invader.getSprite());
            }
            boss = new Boss(stackPane.getWidth()/2 - 100, stackPane.getHeight()/2 - 100, 100, 100, "boss", bossImage, stackPane);
            animationPanel.getChildren().add(boss.getSprite());
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
        // Actions to be performed during each frame of the animation.
        if (!Objects.equals(gameState, "paused")) {
            elapsedTime += 0.016;

            for (int i = 0; i < invaderArrayList.size(); i++) {
                handleEnemyFiring(invaderArrayList.get(i));
                invaderArrayList.get(i).setMoving(true);
                invaderArrayList.get(i).moveUp();
                invaderArrayList.get(i).moveDown();
                invaderArrayList.get(i).moveLeft();
                invaderArrayList.get(i).moveRight();
            }
            for (int i = 0; i < mediumInvaderArrayList.size(); i++) {
                handleMediumEnemyFiring(mediumInvaderArrayList.get(i));
                mediumInvaderArrayList.get(i).setMoving(true);
                mediumInvaderArrayList.get(i).moveUp();
                mediumInvaderArrayList.get(i).moveDown();
            }
            for (int i = 0; i < largeInvaderArraylist.size(); i++) {
                handleLargeEnemyFiring(largeInvaderArraylist.get(i));
                largeInvaderArraylist.get(i).setMoving(true);
                largeInvaderArraylist.get(i).moveUp();
                largeInvaderArraylist.get(i).moveDown();
                largeInvaderArraylist.get(i).moveLeft();
                largeInvaderArraylist.get(i).moveRight();
            }
            if (boss != null){
                handleBossFiring(boss);
                boss.moveLeft();
                boss.moveRight();
            }

            processProjectiles();
            removeDeadSprites();
            spaceShipCollisions();

            if (elapsedTime == 0.016) {
                for (int i = 0; i < invaderArrayList.size(); i++) {
                    invaderArrayList.get(i).setMoving(true);
                    int random = (int) Math.ceil(Math.random() * 4);
                    switch (random){
                        case 1 -> invaderArrayList.get(i).setUp(true);
                        case 2 -> invaderArrayList.get(i).setDown(true);
                        case 3 -> invaderArrayList.get(i).setLeft(true);
                        case 4 -> invaderArrayList.get(i).setRight(true);
                    }
                }
                for (int i = 0; i < mediumInvaderArrayList.size(); i++) {
                    mediumInvaderArrayList.get(i).setMoving(true);
                    int random = (int) Math.ceil(Math.random() * 4);
                    switch (random){
                        case 1 -> mediumInvaderArrayList.get(i).setUp(true);
                        case 2, 3, 4 -> mediumInvaderArrayList.get(i).setDown(true);
                    }
                }
                for (int i = 0; i < largeInvaderArraylist.size(); i++) {
                    largeInvaderArraylist.get(i).setMoving(true);
                    int random = (int) Math.ceil(Math.random() * 4);
                    switch (random){
                        case 1 -> largeInvaderArraylist.get(i).setUp(true);
                        case 2 -> largeInvaderArraylist.get(i).setDown(true);
                        case 3 -> largeInvaderArraylist.get(i).setLeft(true);
                        case 4 -> largeInvaderArraylist.get(i).setRight(true);
                    }
                }
                if (boss != null){
                    boss.setMoving(true);
                    int random = (int) Math.ceil(Math.random()*2);
                    switch (random){
                        case 1 -> boss.setLeft(true);
                        case 2 -> boss.setRight(true);
                    }
                }
            }

            if (elapsedTime == 0.096) {
                for (int i = 0; i < invaderArrayList.size(); i++) {
                    invaderArrayList.get(i).setMoving(false);
                    invaderArrayList.get(i).reset();
                }
                for (int i = 0; i < mediumInvaderArrayList.size(); i++) {
                    mediumInvaderArrayList.get(i).setMoving(false);
                    mediumInvaderArrayList.get(i).reset();
                }
                for (int i = 0; i < largeInvaderArraylist.size(); i++) {
                    largeInvaderArraylist.get(i).setMoving(false);
                    largeInvaderArraylist.get(i).reset();
                }
                if (boss != null) {
                    boss.setMoving(false);
                    boss.reset();
                }
            }

            // Reset the elapsed time.
            if (elapsedTime > 2) {
                elapsedTime = 0;
                if (!animationPanel.getChildren().contains(spaceShip.getSprite())) gameState = "paused";
            }
        }
    }

    private void processProjectiles(){
        for (int i = 0; i < projectileArrayList.size(); i++) {
            if (Objects.equals(projectileArrayList.get(i).getType(), "enemybullet")) handleEnemyBullet(projectileArrayList.get(i));
            else if (Objects.equals(projectileArrayList.get(i).getType(), "bossbullet")) handleEnemyBullet(projectileArrayList.get(i));
            else if (Objects.equals(projectileArrayList.get(i).getType().substring(0,12), "playerbullet")) {
                handlePlayerBullet(projectileArrayList.get(i));
            }
        }
    }

    private void handleEnemyBullet(Projectile projectile) {
        projectile.moveDown();
        // Check for collision with the spaceship
        if (projectile.getSprite().getBoundsInParent().intersects(spaceShip.getSprite().getBoundsInParent()) && !projectile.isDead()) {
            projectile.getSprite().setDead(true);
            projectile.setDead(true);
            playerGetsHit();
        }
    }

    private void handlePlayerBullet(Projectile projectile) {
        switch (projectile.getType()){
            case "playerbullet": projectile.moveUp(); break;
            case "playerbulletLeft30": projectile.moveUpLeft30(); break;
            case "playerbulletLeft45": projectile.moveUpLeft45(); break;
            case "playerbulletLeft60": projectile.moveUpLeft60(); break;
            case "playerbulletRight30": projectile.moveUpRight30(); break;
            case "playerbulletRight45": projectile.moveUpRight45(); break;
            case "playerbulletRight60": projectile.moveUpRight60(); break;
        }

        for (int i = 0; i < invaderArrayList.size(); i++) {
            if (invaderArrayList.get(i).getSprite().getBoundsInParent().intersects(projectile.getSprite().getBoundsInParent()) && !projectile.isDead() && !invaderArrayList.get(i).isDead()) {
                projectile.setDead(true);
                projectile.getSprite().setDead(true);
                projectileArrayList.remove(projectile);
                invaderGetsHit(invaderArrayList.get(i));
            }
        }
        for (int i = 0; i < mediumInvaderArrayList.size(); i++) {
            if (mediumInvaderArrayList.get(i).getSprite().getBoundsInParent().intersects(projectile.getSprite().getBoundsInParent()) && !projectile.isDead() && !mediumInvaderArrayList.get(i).isDead()) {
                projectile.setDead(true);
                projectile.getSprite().setDead(true);
                projectileArrayList.remove(projectile);
                mediumInvaderGetsHit(mediumInvaderArrayList.get(i));
            }
        }
        for (int i = 0; i < largeInvaderArraylist.size(); i++) {
            if (largeInvaderArraylist.get(i).getSprite().getBoundsInParent().intersects(projectile.getSprite().getBoundsInParent()) && !projectile.isDead() && !largeInvaderArraylist.get(i).isDead()) {
                projectile.setDead(true);
                projectile.getSprite().setDead(true);
                projectileArrayList.remove(projectile);
                largeInvaderGetsHit(largeInvaderArraylist.get(i));
            }
        }
        if (boss != null && !boss.isDead() && !projectile.isDead()) {
            if (projectile.getSprite().getBoundsInParent().intersects(boss.getSprite().getBoundsInParent())) {
                projectile.setDead(true);
                projectile.getSprite().setDead(true);
                projectileArrayList.remove(projectile);
                bossGetsHit();
            }
        }
        for (int i = 0; i < projectileArrayList.size(); i++) {
            if (projectile.getSprite().getBoundsInParent().intersects(projectileArrayList.get(i).getSprite().getBoundsInParent()) && !projectile.isDead() && projectileArrayList.get(i) != projectile && !projectileArrayList.get(i).getType().startsWith("player")){
                animationPanel.getChildren().removeAll(projectile.getSprite(), projectileArrayList.get(i).getSprite());
                projectileArrayList.get(i).setDead(true);
                projectileArrayList.get(i).getSprite().setDead(true);
                projectile.setDead(true);
                projectileArrayList.remove(projectileArrayList.get(i));
                projectileArrayList.remove(projectile);
            }
        }
    }

    private void bossGetsHit() {
        boss.setLives(boss.getLives() - 1);
        updateScorePlus1();
        if (boss.getLives() == 0 && invaderArrayList.isEmpty()){
            animationPanel.getChildren().remove(boss.getSprite());
            boss.setDead(true);
            handleExplosion(boss.getSprite());
            nextLevel();
        } else if (boss.getLives() == 0) {
            animationPanel.getChildren().remove(boss.getSprite());
            handleExplosion(boss.getSprite());
            boss.setDead(true);
        }
        else bossHit.play();
    }

    private void handleEnemyFiring(Invader invader) {
        if (elapsedTime > 2) {
            if (Math.random() < 0.3) {
                shoot(invader.getSprite());
            }
        }
    }
    private void handleMediumEnemyFiring(MediumInvader invader) {
        if (elapsedTime > 2) {
            if (Math.random() < 0.4) {
                shoot(invader.getSprite());
            }
        }
    }
    private void handleLargeEnemyFiring(LargeInvader invader) {
        if (elapsedTime > 2) {
            if (Math.random() < 0.5) {
                shoot(invader.getSprite());
            }
        }
    }
    private void handleBossFiring(Boss boss) {
        if (elapsedTime > 2 || elapsedTime % 0.032 == 0) shoot(boss.getSprite());
    }

    private void spaceShipCollisions(){
        for (int i = 0; i < invaderArrayList.size(); i++) {
            if (spaceShip.getSprite().getBoundsInParent().intersects(invaderArrayList.get(i).getSprite().getBoundsInParent()) && !invaderArrayList.get(i).isDead() && !spaceShip.isDead()) {
                invaderGetsHit(invaderArrayList.get(i));
                playerGetsHit();
            }
        }
        for (int i = 0; i < mediumInvaderArrayList.size(); i++) {
            if (spaceShip.getSprite().getBoundsInParent().intersects(mediumInvaderArrayList.get(i).getSprite().getBoundsInParent()) && !mediumInvaderArrayList.get(i).isDead() && !spaceShip.isDead()) {
                mediumInvaderGetsHit(mediumInvaderArrayList.get(i));
                playerGetsHit();
            }
        }
        for (int i = 0; i < largeInvaderArraylist.size(); i++) {
            if (spaceShip.getSprite().getBoundsInParent().intersects(largeInvaderArraylist.get(i).getSprite().getBoundsInParent()) && !largeInvaderArraylist.get(i).isDead() && !spaceShip.isDead()) {
                largeInvaderGetsHit(largeInvaderArraylist.get(i));
                playerGetsHit();
            }
        }
        if (boss != null) {
            if (spaceShip.getSprite().getBoundsInParent().intersects(boss.getSprite().getBoundsInParent()) && !spaceShip.isDead()) {
                spaceShip.setLives(1);
                playerGetsHit();
                bossGetsHit();
            }
        }
    }

    private void playerGetsHit(){
        if (spaceShip.getLives() <= 1 && !spaceShip.isDead()) {
            explosionSound.play();
            spaceShip.setDead(true);
            animationPanel.getChildren().remove(spaceShip);
            gameOver();
        }
        else {
            playerHit.play();
        }
        spaceShip.setLives(spaceShip.getLives() - 1);
        livesLabel.setText("Lives: " + spaceShip.getLives());
    }

    private void invaderGetsHit(Invader invader){
        updateScorePlus1();
        handleExplosion(invader.getSprite());
        invader.setDead(true);
        invader.getSprite().setDead(true);
        invaderArrayList.remove(invader);
        explosionSound.play();
        checkNextLevel();
    }

    private void mediumInvaderGetsHit(MediumInvader invader){
        invader.setLives(invader.getLives() - 1);
        if (invader.getLives() == 0) {
            updateScorePlus1();
            handleExplosion(invader.getSprite());
            invader.setDead(true);
            invader.getSprite().setDead(true);
            mediumInvaderArrayList.remove(invader);
            mediumExplosionSound.play();
            checkNextLevel();
        }
        else mediumHit.play();
    }

    private void largeInvaderGetsHit(LargeInvader invader){
        invader.setLives(invader.getLives() - 1);
        if (invader.getLives() == 0) {
            updateScorePlus1();
            handleExplosion(invader.getSprite());
            invader.setDead(true);
            invader.getSprite().setDead(true);
            largeInvaderArraylist.remove(invader);
            largeExplosionSound.play();
            checkNextLevel();
        } else largeHit.play();
    }

    private void updateScorePlus1(){
        spaceShip.setScore(spaceShip.getScore() + 1);
        scoreLabel.setText("Score: " + spaceShip.getScore());
    }

    private void checkNextLevel(){
        if (invaderArrayList.isEmpty() && mediumInvaderArrayList.isEmpty() && largeInvaderArraylist.isEmpty()) {
            if (boss != null && boss.isDead()) nextLevel();
            else if (boss == null) nextLevel();
        }
    }

    private void nextLevel(){
        if (level < 3) {
            previousScore = spaceShip.getScore();
            gameOverVBox = new VBox();
            Label dead = new Label("Next Level");
            dead.setStyle("-fx-font-size: 40; -fx-text-fill: #0380ff");
            Button nextLevel = new Button("Continue");
            nextLevel.setStyle("-fx-background-color: green; -fx-text-fill: white");
            nextLevel.setFocusTraversable(false);
            gameOverVBox.getChildren().addAll(dead, nextLevel);
            stackPane.getChildren().add(gameOverVBox);
            gameOverVBox.setStyle("-fx-alignment: center");

            nextLevel.setOnAction(event -> {
                nextLevelSound.play();
                for (Projectile projectile : projectileArrayList) animationPanel.getChildren().remove(projectile.getSprite());
                projectileArrayList.clear();
                stackPane.getChildren().remove(gameOverVBox);
                level++;
                if (level == 2) {
                    backgroundMusic.stop();
                    backgroundMusic2.play();
                    stackPane.setStyle("-fx-background-image: url(/images/singularity.jpg); -fx-background-size: 1920 1080");
                    firingMode = 2;
                    spaceShip.getSprite().setImage(spaceship2);
                } else {
                    backgroundMusic2.stop();
                    backgroundMusic3.play();
                    stackPane.setStyle("-fx-background-image: url(/images/spaceTime.jpg); -fx-background-size: 1920 1080");
                    firingMode = 3;
                    spaceShip.getSprite().setImage(spaceship3);
                }
                levelLabel.setText("Level " + level);
                spaceShip.getSprite().setLayoutY(stackPane.getHeight() * 3 / 4);
                spaceShip.getSprite().setLayoutX(stackPane.getWidth() / 2);
                generateInvaders();
            });
        }
        else {
            gameState = "paused";
            backgroundMusic3.stop();
            endMusic.play();
            winBox = new VBox();
            winBox.setStyle("-fx-background-color: black");
            winBox.setAlignment(Pos.CENTER);
            winBox.setSpacing(10);
            Label winLabel = new Label("Congratulations, you have defeated the invaders!");
            winLabel.setStyle("-fx-text-fill: white; -fx-font-size: 64");
            Label finalScore = new Label("Final Score: " + spaceShip.getScore());
            finalScore.setStyle("-fx-text-fill: white");
            Button back = new Button("Back to Level 1");
            back.setFocusTraversable(false);
            back.setOnAction(event -> backToLevel1());
            Button exit = new Button("Exit");
            exit.setFocusTraversable(false);
            exit.setOnAction(event -> primaryStage.hide());
            winBox.getChildren().addAll(winLabel, finalScore, back, exit);
            stackPane.getChildren().add(winBox);
        }
    }

    private void backToLevel1() {
        gameState = "playing";
        backgroundMusic.stop();
        backgroundMusic2.stop();
        backgroundMusic3.stop();
        endMusic.stop();
        backgroundMusic.play();
        stackPane.getChildren().remove(winBox);
        animationPanel.getChildren().clear();
        projectileArrayList.clear();
        spaceShip = new Player(stackPane.getWidth()/2, stackPane.getHeight() * 3 / 4, 40, 40, "player");
        spaceShip.setStackPane(stackPane);
        animationPanel.getChildren().add(spaceShip.getSprite());
        level = 1;
        firingMode = 1;
        previousScore = 0;
        if (boss != null) boss = null;
        hud.getChildren().remove(restartButton);
        hud.getChildren().add(restartButton);
        scoreLabel.setText("Score: " + spaceShip.getScore());
        livesLabel.setText("Lives: " + spaceShip.getLives());
        levelLabel.setText("Level: " + level);
        stackPane.setStyle("-fx-background-image: url(/images/stars.png); -fx-background-size: 1920 1080");
        generateInvaders();
    }

    private void handleExplosion(Sprite target){
        explosionArrayList.addLast(new Sprite(target.getLayoutX(), target.getLayoutY(), 40, 40, "" + spaceShip.getScore(), explosion));
        animationPanel.getChildren().add(explosionArrayList.getLast());
        Timeline gifTime = new Timeline(new KeyFrame(Duration.millis(500)));
        gifTime.setOnFinished(event -> {
            animationPanel.getChildren().remove(explosionArrayList.getFirst());
            explosionArrayList.removeFirst();
        });
        gifTime.setCycleCount(1);
        gifTime.play();
    }

    private void gameOver(){
        handleExplosion(spaceShip.getSprite());
        spaceShip.getSprite().setDead(true);
        spaceShip.setDead(true);
        animationPanel.getChildren().remove(spaceShip.getSprite());
        for (int i = 0; i < projectileArrayList.size(); i++) animationPanel.getChildren().remove(projectileArrayList.get(i));
        gameOverVBox = new VBox();
        gameOverVBox.setSpacing(10);
        Label dead = new Label("GAME OVER");
        dead.setStyle("-fx-font-size: 40; -fx-text-fill: red");
        Label deadScore = new Label("Score: " + spaceShip.getScore());
        deadScore.setStyle("-fx-font-size: 30; -fx-text-fill: white");
        Button restartOver = restartButton;
        gameOverVBox.getChildren().addAll(dead, deadScore, restartOver);
        if (level > 1){
            Button returnToLevel1 = getLevel1Button();
            returnToLevel1.setFocusTraversable(false);
            gameOverVBox.getChildren().add(returnToLevel1);
        }
        stackPane.getChildren().add(gameOverVBox);
        gameOverVBox.setStyle("-fx-alignment: center");
        handleExplosion(spaceShip.getSprite());
    }

    private Button getLevel1Button() {
        Button returnToLevel1 = new Button("Return to Level 1");
        returnToLevel1.setOnAction(event -> {
            stackPane.getChildren().remove(gameOverVBox);
            projectileArrayList.clear();
            invaderArrayList.clear();
            mediumInvaderArrayList.clear();
            largeInvaderArraylist.clear();
            if (boss != null) boss = null;
            animationPanel.getChildren().clear();
            spaceShip = new Player(stackPane.getWidth()/2, stackPane.getHeight() * 3 / 4, 40, 40, "player");
            animationPanel.getChildren().add(spaceShip.getSprite());
            spaceShip.setStackPane(stackPane);
            backToLevel1();
        });
        return returnToLevel1;
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
        if (!spaceShip.isDead()){
            // The firing entity can be either an enemy or the spaceship.
            if (Objects.equals(firingEntity.getType(), "player") && shooting && !Objects.equals(gameState, "paused") && !shootDelay){
                Image bulletImage;
                switch (firingMode){
                    case 2 -> {
                        blasterSound2.play();
                        bulletImage = playerBulletPurple;
                    }
                    case 3 -> {
                        blasterSound3.play();
                        bulletImage = playerBulletGreen;
                    }
                    default-> {
                        playerShoot.play();
                        bulletImage = playerBullet;
                    }
                }
                Projectile bullet = new Projectile((int) firingEntity.getLayoutX() + 20, (int) firingEntity.getLayoutY(), 20, 40, firingEntity.getType() + "bullet", bulletImage);
                projectileArrayList.add(bullet);
                animationPanel.getChildren().add(bullet.getSprite());
                if (firingMode == 2) {
                    Projectile bullet2 = new Projectile((int) firingEntity.getLayoutX() + 20, (int) firingEntity.getLayoutY(), 20, 40, firingEntity.getType() + "bulletLeft45", bulletImage);
                    Projectile bullet3 = new Projectile((int) firingEntity.getLayoutX() + 20, (int) firingEntity.getLayoutY(), 20, 40, firingEntity.getType() + "bulletRight45", bulletImage);
                    projectileArrayList.add(bullet2);
                    projectileArrayList.add(bullet3);
                    animationPanel.getChildren().addAll(bullet2.getSprite(), bullet3.getSprite());
                }
                else if (firingMode == 3) {
                    Projectile bullet2 = new Projectile((int) firingEntity.getLayoutX() + 20, (int) firingEntity.getLayoutY(), 20, 40, firingEntity.getType() + "bulletLeft30", bulletImage);
                    Projectile bullet3 = new Projectile((int) firingEntity.getLayoutX() + 20, (int) firingEntity.getLayoutY(), 20, 40, firingEntity.getType() + "bulletRight30", bulletImage);
                    Projectile bullet4 = new Projectile((int) firingEntity.getLayoutX() + 20, (int) firingEntity.getLayoutY(), 20, 40, firingEntity.getType() + "bulletLeft60", bulletImage);
                    Projectile bullet5 = new Projectile((int) firingEntity.getLayoutX() + 20, (int) firingEntity.getLayoutY(), 20, 40, firingEntity.getType() + "bulletRight60", bulletImage);
                    projectileArrayList.add(bullet2);
                    projectileArrayList.add(bullet3);
                    projectileArrayList.add(bullet4);
                    projectileArrayList.add(bullet5);
                    animationPanel.getChildren().addAll(bullet2.getSprite(), bullet3.getSprite(), bullet4.getSprite(), bullet5.getSprite());
                }
                shootDelay = true;
            }
            else if (Objects.equals(firingEntity.getType(), "enemy")) {
                Projectile bullet = new Projectile((int) firingEntity.getLayoutX() + 20, (int) firingEntity.getLayoutY(), 20, 40, firingEntity.getType() + "bullet", invaderBullet);
                projectileArrayList.add(bullet);
                animationPanel.getChildren().add(bullet.getSprite());
            }
            else if (Objects.equals(firingEntity.getType(), "boss")) {
                Projectile bullet = new Projectile((int) firingEntity.getLayoutX() + 20, (int) firingEntity.getLayoutY(), 20, 40, firingEntity.getType() + "bullet", invaderBullet);
                projectileArrayList.add(bullet);
                animationPanel.getChildren().add(bullet.getSprite());
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
