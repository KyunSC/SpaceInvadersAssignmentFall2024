package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

/**
 * Controllable player that can move using keyCode
 * Has a sprite used in the animation pane
 * boolean left, right, up, down to allow or deny the move functions used to move smoothly
 * Number of lives is 3
 * score is stored in the player
 * image is set
 * Speedup is a multiplier for the movement speed of the player
 */
public class Player extends ImageView {
    Sprite sprite;
    private boolean dead = false;
    boolean left;
    boolean right;
    boolean up;
    boolean down;
    int lives;
    int score = 0;
    Image spaceShipImage = new Image(String.valueOf(getClass().getResource("/images/ship1.png")));
    StackPane stackPane;
    double speedUp = 1;


    /**
     *
     * @param x position x of the sprite
     * @param y position y of the sprite
     * @param width fit width of the imageView
     * @param height fit height of the imageView
     * @param type player
     *             Creates a sprite used in the animation pane
     */
    public Player(double x, double y, double width, double height, String type) {
        this.sprite = new Sprite(x, y, width, height, type, spaceShipImage);
        this.lives = 3;
        this.score = 0;
    }

    /**
     *
     * @param left true or false pausing or playing the movement
     */
    public void setLeft(boolean left) {this.left = left;}
    /**
     *
     * @param right true or false pausing or playing the movement
     */
    public void setRight(boolean right) {this.right = right;}
    /**
     *
     * @param up true or false pausing or playing the movement
     */
    public void setUp(boolean up) {this.up = up;}
    /**
     *
     * @param down true or false pausing or playing the movement
     */
    public void setDown(boolean down) {this.down = down;}

    /**
     * Checks if the sprite is not touching the left side of the window
     * Then, check if it is left only that is true or left and up or left and down
     * Using Vectors if it is a combination set the velocity for x and y so that the net vector is equal to 5*speedUp multiplier
     */
    public void moveLeft() {
        if (sprite.getLayoutX() > 0) {
            if (left && up || left && down) sprite.setLayoutX((sprite.getLayoutX() - (5*speedUp) / Math.sqrt(2)));
            else if (left) sprite.setLayoutX(sprite.getLayoutX() - (5*speedUp));
        }
    }
    /**
     * Checks if the sprite is not touching the right side of the window
     * Then, check if it is right only that is true or right and up or right and down
     * Using Vectors if it is a combination set the velocity for x and y so that the net vector is equal to 5*speedUp multiplier
     */
    public void moveRight() {
        if (sprite.getLayoutX() < stackPane.getWidth() - 40) {
            if (right && up || right && down) sprite.setLayoutX((sprite.getLayoutX() + (5*speedUp) / Math.sqrt(2)));
            else if (right) sprite.setLayoutX(sprite.getLayoutX() + (5*speedUp));
        }
    }

    /**
     * Checks if the sprite is not touching the top side of the window
     * Then, check if it is up only that is true or right and up or right and down
     * Using Vectors if it is a combination set the velocity for x and y so that the net vector is equal to 5*speedUp multiplier
     */
    public void moveUp() {
        if (sprite.getLayoutY() > 0) {
            if (left && up || right && up) sprite.setLayoutY((sprite.getLayoutY() - (5*speedUp) / Math.sqrt(2)));
            else if (up) sprite.setLayoutY(sprite.getLayoutY() - (5*speedUp));
        }
    }

    /**
     * Checks if the sprite is not touching the bottom of the window
     * Then, check if it is down only that is true or left and down or right and down
     * Using Vectors if it is a combination set the velocity for x and y so that the net vector is equal to 5*speedUp multiplier
     */
    public void moveDown() {
        if (sprite.getLayoutY() < stackPane.getHeight() - 40) {
            if (left && down || right && down) sprite.setLayoutY((sprite.getLayoutY() + 5 / Math.sqrt(2)));
            else if (down) sprite.setLayoutY(sprite.getLayoutY() + (5*speedUp));
        }
    }

    /**
     *
     * @return gets sprite associated with the player used in the animation pane
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     *
     * @return if the player is dead
     */
    public boolean isDead() {
        return dead;
    }

    /**
     *
     * @param dead set to dead
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     *
     * @param lives set number of lives usually to remove 1
     */
    public void setLives(int lives) {this.lives = lives;}

    /**
     *
     * @return remaining lives
     */
    public int getLives(){return lives;}

    /**
     *
     * @return score
     */
    public int getScore(){return score;}

    /**
     *
     * @param score adding or removing score
     */
    public void setScore(int score){this.score = score;}

    /**
     *
     * @param stackPane sets dimension of the window used to make the boundaries for the player movement
     */
    public void setStackPane(StackPane stackPane){
        this.stackPane = stackPane;
    }

    /**
     *
     * @param speedUp sets to 1x or 1.5x used when moving
     */
    public void setSpeedUp(double speedUp) {
        this.speedUp = speedUp;
    }
}
