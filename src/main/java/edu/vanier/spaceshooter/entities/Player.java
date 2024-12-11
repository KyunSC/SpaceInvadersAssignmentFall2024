package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class Player extends ImageView {
    Sprite sprite;
    private boolean dead = false;
    boolean left;
    boolean right;
    boolean up;
    boolean down;
    int lives;
    int score = 0;
    Image spaceShipImage = new Image(String.valueOf(getClass().getResource("/images/ship.png")));
    StackPane stackPane;
    double speedUp = 1;


    public Player(double x, double y, double width, double height, String type) {
        this.sprite = new Sprite(x, y, width, height, type, spaceShipImage);
        this.lives = 3;
        this.score = 0;
    }

    public void setLeft(boolean left) {this.left = left;}

    public void setRight(boolean right) {this.right = right;}

    public void setUp(boolean up) {this.up = up;}

    public void setDown(boolean down) {this.down = down;}

    public void moveLeft() {
        if (sprite.getLayoutX() > 0) {
            if (left && up || left && down) sprite.setLayoutX((sprite.getLayoutX() - (5*speedUp) / Math.sqrt(2)));
            else if (left) sprite.setLayoutX(sprite.getLayoutX() - (5*speedUp));
        }
    }

    public void moveRight() {
        if (sprite.getLayoutX() < stackPane.getWidth() - 40) {
            if (right && up || right && down) sprite.setLayoutX((sprite.getLayoutX() + (5*speedUp) / Math.sqrt(2)));
            else if (right) sprite.setLayoutX(sprite.getLayoutX() + (5*speedUp));
        }
    }

    public void moveUp() {
        if (sprite.getLayoutY() > 0) {
            if (left && up || right && up) sprite.setLayoutY((sprite.getLayoutY() - (5*speedUp) / Math.sqrt(2)));
            else if (up) sprite.setLayoutY(sprite.getLayoutY() - (5*speedUp));
        }
    }

    public void moveDown() {
        if (sprite.getLayoutY() < stackPane.getHeight() - 40) {
            if (left && down || right && down) sprite.setLayoutY((sprite.getLayoutY() + 5 / Math.sqrt(2)));
            else if (down) sprite.setLayoutY(sprite.getLayoutY() + (5*speedUp));
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isDead() {
        return dead;
    }

    public String getType() {
        String type = "player";
        return type;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setLives(int lives) {this.lives = lives;}

    public int getLives(){return lives;}

    public int getScore(){return score;}

    public void setScore(int score){this.score = score;}

    public void setStackPane(StackPane stackPane){
        this.stackPane = stackPane;
    }

    public void setSpeedUp(double speedUp) {
        this.speedUp = speedUp;
    }
}
