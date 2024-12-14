package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Stack;

/**
 * Invader for level 2
 * It has 2 lives
 * Type is enemy
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
    private boolean left = false;
    private boolean right= false;
    private boolean up = false;
    private boolean down = false;
    private int lives = 2;


    public MediumInvader(double x, double y, double width, double height, String type, Image image, StackPane stackPane) {
        super(x, y, width, height, type, image);
        this.stackPane = stackPane;
        this.type = type;
        setFitWidth(width);
        setFitHeight(height);
        this.sprite = new Sprite(x, y, width, height, type, image);
    }

    public void moveUp() {
        if (up) {
            if (sprite.getLayoutY() > 100) sprite.setLayoutY(sprite.getLayoutY() - (Math.random()*5 + 10));
        }
    }

    public void moveDown() {
        if (down) {
            if (sprite.getLayoutY() < stackPane.getHeight() - 200) sprite.setLayoutY(sprite.getLayoutY() + (Math.random()*20 + 10));
            else moveUp();
        }
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

    public Sprite getSprite(){
        return sprite;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public void setDown(boolean down) {
        this.down = down;
    }
    public void reset(){
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
    }
    public int getLives(){
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
