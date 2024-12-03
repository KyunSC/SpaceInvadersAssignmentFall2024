package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public class Player extends ImageView {
    Sprite sprite;
    private boolean dead = false;
    private final String type;
    boolean left;
    boolean right;
    boolean up;
    boolean down;
    int lives = 3;
    Image spaceShipImage = new Image(String.valueOf(getClass().getResource("/assets/ship.png")));

    public Player(int x, int y, int width, int height, String type) {
        this.type = type;
        this.sprite = new Sprite(x, y, width, height, type, spaceShipImage);
        this.lives = 3;
    }

    public void setLeft(boolean left) {this.left = left;}

    public void setRight(boolean right) {this.right = right;}

    public void setUp(boolean up) {this.up = up;}

    public void setDown(boolean down) {this.down = down;}

    public void moveLeft() {
        if (left && up || left && down) sprite.setLayoutX((getLayoutX() - 5 / Math.sqrt(2)));
        else if (left) sprite.setLayoutX(getLayoutX() - 5);
    }

    public void moveRight() {
        if (right && up || right && down) sprite.setLayoutX((getLayoutX() + 5 / Math.sqrt(2)));
        else if (right) sprite.setLayoutX(getLayoutX() + 5);
    }

    public void moveUp() {
        if (left && up || right && up) sprite.setLayoutY((getLayoutY() - 5 / Math.sqrt(2)));
        else if (up) {sprite.setLayoutY(getLayoutY() - 5);}
    }

    public void moveDown() {
        if (left && down || right && down) sprite.setLayoutY((getLayoutY() + 5 / Math.sqrt(2)));
        else if (down) sprite.setLayoutY(getLayoutY() + 5);
    }

    public Sprite getSprite() {
        return sprite;
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

    public void setLives(int lives) {this.lives = lives;}

    public int getLives(){return lives;}
}
