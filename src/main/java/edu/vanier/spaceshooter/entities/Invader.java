package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Stack;

/**
 * Basic invader with 1 HP
 * All parameters used to create invader and the sprite associated
 * Type is enemy
 * Stack pane for dimensions of the window
 * Boolean left, right, up, down are used to move around the sprite
 *
 */
public class Invader extends Sprite {
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

    /**
     *
     * @param x position x of the sprite
     * @param y position y of the sprite
     * @param width fit width of the imageView
     * @param height fit height of the imageView
     * @param type enemy
     * @param image image of the invader
     * @param stackPane dimensions of the window
     *                  Creates a sprite used in the animation pane
     */
    public Invader(double x, double y, double width, double height, String type, Image image, StackPane stackPane) {
        super(x, y, width, height, type, image);
        this.stackPane = stackPane;
        this.type = type;
        setFitWidth(width);
        setFitHeight(height);
        this.sprite = new Sprite(x, y, width, height, type, image);
    }

    /**
     * If left is true then,
     * Checks if it is not 100 pixels away from the left side of the window,
     * If not, then it will move 5 towards the left
     */
    public void moveLeft() {
        if (left) {
            if (sprite.getLayoutX() > 100) sprite.setLayoutX(sprite.getLayoutX() - 5);
        }
    }

    /**
     * If right is true then,
     * Checks if it is not 100 pixels away from the right side of the window,
     * If not, then it will move 5 towards the right
     */
    public void moveRight() {
        if (right) {
            if (sprite.getLayoutX() < stackPane.getWidth() - 100 || sprite.getLayoutX() < 1800) sprite.setLayoutX(sprite.getLayoutX() + 5);
        }
    }

    /**
     * If up is true then,
     * Checks if it is not 100 pixels away from the top of the window,
     * If not, then it will move 5 towards the top
     */
    public void moveUp() {
        if (up) {
            if (sprite.getLayoutY() > 100) sprite.setLayoutY(sprite.getLayoutY() - 5);
        }
    }

    /**
     * If down is true then,
     * Checks if it is not 100 pixels away from the bottom of the window,
     * If not, then it will move 5 towards the bottom
     */
    public void moveDown() {
        if (down) {
            if (sprite.getLayoutY() < stackPane.getHeight() - 100 || sprite.getLayoutY() < 900) sprite.setLayoutY(sprite.getLayoutY() + 5);
        }
    }

    /**
     *
     * @return dead or not
     */
    public boolean isDead() {
        return dead;
    }

    /**
     *
     * @return enemy as a string
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param dead sets to dead
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     *
     * @return the sprite related to the invader used in the animation pane
     */
    public Sprite getSprite(){
        return sprite;
    }

    /**
     *
     * @param moving sets whether the sprite can move or not using the move methods
     */
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     *
     * @param stackPane gives the sprite the dimensions of the window
     */
    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    /**
     *
     * @param left allows the sprite to move left
     */
    public void setLeft(boolean left) {
        this.left = left;
    }
    /**
     *
     * @param right allows the sprite to move left
     */
    public void setRight(boolean right) {
        this.right = right;
    }
    /**
     *
     * @param up allows the sprite to move left
     */
    public void setUp(boolean up) {
        this.up = up;
    }
    /**
     *
     * @param down allows the sprite to move left
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * sets the boolean up, down, left, right to false
     */
    public void reset(){
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
    }
}
