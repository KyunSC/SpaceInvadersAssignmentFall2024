package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Stack;

/**
 * Invader for level 2
 * The invader is like a diver focusing on going downwards towards the bottom
 * It has 2 lives
 * Type is enemy
 * All parameters are the same as LargeInvader except for number of lives
 *
 */
public class MediumInvader extends Sprite {
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
    private boolean up = false;
    private boolean down = false;
    private int lives = 2;


    /**
     *
     * @param x position of the sprite
     * @param y position of the sprite
     * @param width fit width of the imageView
     * @param height fit height of the imageView
     * @param type enemy
     * @param image image of the imageview
     * @param stackPane dimensions of the window so that the invader doesnt go out
     *                  Creates a sprite using x, y, width, height, type and image used in the animation pane
     */
    public MediumInvader(double x, double y, double width, double height, String type, Image image, StackPane stackPane) {
        super(x, y, width, height, type, image);
        this.stackPane = stackPane;
        this.type = type;
        setFitWidth(width);
        setFitHeight(height);
        this.sprite = new Sprite(x, y, width, height, type, image);
    }

    /**
     * Check if up is true and if the sprite is at least 100 pixels away from the window borders
     */
    public void moveUp() {
        if (up) {
            if (sprite.getLayoutY() > 100) sprite.setLayoutY(sprite.getLayoutY() - (Math.random()*5 + 10));
        }
    }

    /**
     * Check if down is true and if the sprite is at least 100 pixels away from the window borders
     */
    public void moveDown() {
        if (down) {
            if (sprite.getLayoutY() < stackPane.getHeight() - 200) sprite.setLayoutY(sprite.getLayoutY() + (Math.random()*20 + 10));
            else moveUp();
        }
    }

    /**
     *
     * @return if the sprite is alive or not
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
     * @param dead used to set to dead
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     *
     * @return gets the sprite of the mediumInvader
     */
    public Sprite getSprite(){
        return sprite;
    }

    /**
     *
     * @param moving true or false, which allows or denies the invaders ability to moveUp() or moveDown()
     */
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     *
     * @param stackPane sets dimension of the pane
     */
    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    /**
     *
     * @param up sets to true or false either allowing or denying moveUp()
     */
    public void setUp(boolean up) {
        this.up = up;
    }
    /**
     *
     * @param down sets to true or false either allowing or denying moveDown()
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * Set up and down to false stopping the movement
     */
    public void reset(){
        this.up = false;
        this.down = false;
    }

    /**
     *
     * @return remaining lives left
     */
    public int getLives(){
        return lives;
    }

    /**
     *
     * @param lives set lives left usually for minus one when hit
     */
    public void setLives(int lives) {
        this.lives = lives;
    }
}
