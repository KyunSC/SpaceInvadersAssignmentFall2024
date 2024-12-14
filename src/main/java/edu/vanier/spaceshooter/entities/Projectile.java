package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Class for the projectiles
 * It has a sprite
 * type is bullet
 */
public class Projectile extends Sprite {

    Sprite sprite;
    String type;

    /**
     *
     * @param x position of the sprite
     * @param y position of the sprite
     * @param width fit width of the imageView
     * @param height fit height of the imageView
     * @param type bullet
     * @param image image of the projectile that changes depending on the firing mode
     *              Creates a sprite used in the animation pane to display the projectile
     */
    public Projectile(int x, int y, int width, int height, String type, Image image) {
        super(x, y, width, height, type, image);
        this.sprite = new Sprite(x, y, width, height, type, image);
        this.type = type;
    }

    /**
     * Basic projectile from firingmode 1
     * It moves up 10
     */
    public void moveUp() {
        sprite.setLayoutY(sprite.getLayoutY() - 10);
    }

    /**
     * Rotates the projectile to the appropriate angle
     * Moves in x and y using vectors to make sure the net total speed remains the same for all angles
     * Seprate x and y to make the right x and y vectors
     */
    public void moveUpLeft30(){
        sprite.setRotate(-45);
        sprite.setLayoutY(sprite.getLayoutY() - 5);
        sprite.setLayoutX(sprite.getLayoutX() - (5 * Math.sqrt(3)) );
    }

    public void moveUpLeft45(){
        sprite.setRotate(-45);
        sprite.setLayoutY(sprite.getLayoutY() - (5 * Math.sqrt(2)) );
        sprite.setLayoutX(sprite.getLayoutX() - (5 * Math.sqrt(2)) );
    }

    public void moveUpLeft60(){
        sprite.setRotate(-45);
        sprite.setLayoutY(sprite.getLayoutY() - (5 * Math.sqrt(3)) );
        sprite.setLayoutX(sprite.getLayoutX() - 5);
    }

    public void moveUpRight30(){
        sprite.setRotate(45);
        sprite.setLayoutY(sprite.getLayoutY() - 5);
        sprite.setLayoutX(sprite.getLayoutX() + (5 * Math.sqrt(3)) );
    }

    public void moveUpRight45(){
        sprite.setRotate(45);
        sprite.setLayoutY(sprite.getLayoutY() - (5 * Math.sqrt(2)) );
        sprite.setLayoutX(sprite.getLayoutX() + (5 * Math.sqrt(2)) );
    }

    public void moveUpRight60(){
        sprite.setRotate(45);
        sprite.setLayoutY(sprite.getLayoutY() - (5 * Math.sqrt(3)) );
        sprite.setLayoutX(sprite.getLayoutX() + 5);
    }

    /**
     * Projectile used by invader, moves down 10
     */
    public void moveDown() {
        sprite.setLayoutY(sprite.getLayoutY() + 10);
    }

    /**
     *
     * @return sprite involved with the projectile
     */
    public Sprite getSprite(){
        return sprite;
    }

    /**
     *
     * @return type from player or invader
     */
    public String getType(){
        return type;
    }
}
