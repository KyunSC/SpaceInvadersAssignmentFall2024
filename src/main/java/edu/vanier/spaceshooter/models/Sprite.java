package edu.vanier.spaceshooter.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * General Sprite class used for the objects in the animation pane
 * Gets the parameters from the invader, player of projectile
 * extends imageview since all sprites are images
 */
public class Sprite extends ImageView {

    private boolean dead = false;
    private final String type;
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     *
     * @param x position of the sprite
     * @param y position of the sprite
     * @param width fit width of the imageView
     * @param height fit height of the imageView
     * @param type returns type
     * @param image image of the sprite needed
     *              Sets the image size and preserves ratio of the image
     *              Sets the sprite to the right position
     */
    public Sprite(double x, double y, double width, double height, String type, Image image) {
        setImage(image);
        setFitWidth(width);
        setFitHeight(height);
        setPreserveRatio(true);
        this.type = type;
        setLayoutX(x);
        setLayoutY(y);
    }

    /**
     * Overriden methods
     */
    public void moveLeft() {
        setLayoutX(getLayoutX() - 5);
    }

    public void moveRight() {
        setLayoutX(getLayoutX() + 5);
    }

    public void moveUp() {
        setLayoutY(getLayoutY() - 5);
    }

    public void moveDown() {
        setLayoutY(getLayoutY() + 5);
    }

    public boolean isDead() {
        return dead;
    }

    public String getType() {
        return type;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
