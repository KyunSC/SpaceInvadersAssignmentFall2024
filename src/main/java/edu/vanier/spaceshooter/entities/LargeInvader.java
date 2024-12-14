package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Stack;

/**
 * Final level invader
 * It has 3 lives
 * All parameters are the same as the mediumInvader except for the number of lives
 *
 */
public class LargeInvader extends Sprite {
    private boolean dead = false;
    private final String type;
    private Color color;
    private double x;
    private double y;
    private double width;
    private double height;
    private Sprite sprite;
    private StackPane stackPane;
    private boolean moving = false;
    private boolean left = false;
    private boolean right= false;
    private boolean up = false;
    private boolean down = false;
    private int lives = 3;

    /**
     *
     * @param x position x of the sprite
     * @param y position y of the sprite
     * @param width fit width of the image view
     * @param height fit height of the image view
     * @param type enemy
     * @param image image of the sprite
     * @param stackPane dimensions of the window
     */
    public LargeInvader(double x, double y, double width, double height, String type, Image image, StackPane stackPane) {
        super(x, y, width, height, type, image);
        this.stackPane = stackPane;
        this.type = type;
        setFitWidth(width);
        setFitHeight(height);
        this.sprite = new Sprite(x, y, width, height, type, image);
    }

    /**
     * If left is true,
     * Check it the sprite is at least 100 pixels away from the window borders, then
     * Moves left 10
     * Moves down 10
     * Moves Left 10
     */
    public void moveLeft() {
        if (left) {
            if (sprite.getLayoutX() > stackPane.getWidth() - 100) sprite.setLayoutX(sprite.getLayoutX() - 10);
            if (sprite.getLayoutY() < stackPane.getHeight() - 100) sprite.setLayoutY(sprite.getLayoutY() + 10);
            if (sprite.getLayoutX() > stackPane.getWidth() - 100) sprite.setLayoutX(sprite.getLayoutX() - 10);
        }
    }
    /**
     * If right is true,
     * Check it the sprite is at least 100 pixels away from the window borders, then
     * Moves right 10
     * Moves down 10
     * Moves right 10
     */
    public void moveRight() {
        if (right) {
            if (sprite.getLayoutX() < stackPane.getWidth() - 100) sprite.setLayoutX(sprite.getLayoutX() + 10);
            if (sprite.getLayoutY() < stackPane.getHeight() - 100) sprite.setLayoutY(sprite.getLayoutY() + 10);
            if (sprite.getLayoutX() > 200) sprite.setLayoutX(sprite.getLayoutX() - 10);
            if (sprite.getLayoutX() < stackPane.getWidth() - 100) sprite.setLayoutX(sprite.getLayoutX() + 10);
        }
    }

    /**
     * Checks if up is true, then
     * Checks if it is at least 100 pixels from the top of the window,
     * If so, then move the sprite up
     */
    public void moveUp() {
        if (up) {
            if (sprite.getLayoutY() > 100) sprite.setLayoutY(sprite.getLayoutY() - 5);
        }
    }

    /**
     * Checks if down is true, then
     * Checks if it is at least 100 pixels from the bottom of the window,
     * If so, then move the sprite down
     */
    public void moveDown() {
        if (down) {
            if (sprite.getLayoutY() < stackPane.getHeight() - 100) sprite.setLayoutY(sprite.getLayoutY() + 5);
        }
    }

    /**
     *
     * @return if the sprite is still alive
     */
    public boolean isDead() {
        return dead;
    }

    /**
     *
     * @return enemy
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param dead usually used for set dead to true used in the deletion of the sprites
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     *
     * @return get the sprite associated with the invader
     */
    public Sprite getSprite(){
        return sprite;
    }

    /**
     *
     * @param moving sets moving to true or false
     *               if true, it allows the invader to move using the move methods which checks if left, right, up ot down are true or not
     */
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     *
     * @param stackPane sets the dimension of the window so that the invader doesnt excape
     */
    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    /**
     *
     * @param left true or false which pauses or allows the invader to moveLeft()
     */
    public void setLeft(boolean left) {
        this.left = left;
    }
    /**
     *
     * @param right true or false which pauses or allows the invader to moveRight()
     */
    public void setRight(boolean right) {
        this.right = right;
    }
    /**
     *
     * @param up true or false which pauses or allows the invader to moveUp()
     */
    public void setUp(boolean up) {
        this.up = up;
    }
    /**
     *
     * @param down true or false which pauses or allows the invader to moveDown()
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * Sets up, down, left, right to false
     */
    public void reset(){
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
    }

    /**
     *
     * @return number of lives left for the specific invader
     */
    public int getLives(){
        return lives;
    }

    /**
     *
     * @param lives sets the number of lives left to the invader
     */
    public void setLives(int lives) {
        this.lives = lives;
    }
}
