package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Stack;

/**
 *
 * All parameters
 * It has 50 lives/HP, therefore 50 hits to kill
 * It has a stackPane which indicates the boundaries where it can move
 *
 */
public class Boss extends Sprite {
    private boolean dead = false;
    private final String type;
    private Color color;
    private double x;
    private double y;
    private double width;
    private double height;
    private int lives = 50;
    private Sprite sprite;
    private StackPane stackPane;
    private boolean moving = false;
    private boolean left = false;
    private boolean right= false;
    private boolean up = false;
    private boolean down = false;

    /**
     *
     * @param x position x on the animation pane
     * @param y position y on the animation pane
     * @param width fit width of the imageView
     * @param height fit height of the imageView
     * @param type boss
     * @param image image of the boss
     * @param stackPane size of the window
     *                  Uses the parameters to create the boss
     *                  Sprite is then created from the parameters x, y, width, height, type and image
     *                  Sprite is used in the animationPane
     */
    public Boss(double x, double y, double width, double height, String type, Image image, StackPane stackPane) {
        super(x, y, width, height, type, image);
        this.stackPane = stackPane;
        this.type = type;
        setFitWidth(width);
        setFitHeight(height);
        this.sprite = new Sprite(x, y, width, height, type, image);
    }

    /**
     *
     * If boolean left is true then setLayoutX to getLayoutX - random number between 20 and 50
     *
     */
    public void moveLeft() {
        if (left) {
            if (sprite.getLayoutX() > 200) sprite.setLayoutX(sprite.getLayoutX() - (Math.random()*30 + 20));
        }
    }

    /**
     *
     * If boolean right is true then setLayoutX to getLayoutX + random number between 20 and 50
     *
     */
    public void moveRight() {
        if (right) {
            if (sprite.getLayoutX() < stackPane.getWidth() - 200 || sprite.getLayoutX() < 1680) sprite.setLayoutX(sprite.getLayoutX() + (Math.random()*30 + 20));
        }
    }

    /**
     *
     * @return if it is dead or not
     * Used for confirmation
     */
    public boolean isDead() {
        return dead;
    }

    /**
     *
     * @return boss
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param dead sets
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     *
     * @return gets the sprite related to the boss which is used inside the animation pane
     */
    public Sprite getSprite(){
        return sprite;
    }

    /**
     *
     * @param moving sets whether it is moving or not
     *               Prevents or allows move methods to work
     */
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     *
     * @param stackPane gives the dimension of the window
     */
    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    /**
     *
     * @param left allows the moveLeft() to work
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     *
     * @param right allows the moveRight() to work
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     *
     * @param up allows the moveUp() to work
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * sets up, left, right, down booleans to false
     */
    public void reset(){
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
    }

    /**
     *
     * @return number of hits left for the player to kill
     */
    public int getLives(){
        return lives;
    }

    /**
     *
     * @param lives sets lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }
}
