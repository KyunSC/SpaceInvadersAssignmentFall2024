package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Stack;

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


    public Boss(double x, double y, double width, double height, String type, Image image, StackPane stackPane) {
        super(x, y, width, height, type, image);
        this.stackPane = stackPane;
        this.type = type;
        setFitWidth(width);
        setFitHeight(height);
        this.sprite = new Sprite(x, y, width, height, type, image);
    }

    public void moveLeft() {
        if (left) {
            if (sprite.getLayoutX() > 200) sprite.setLayoutX(sprite.getLayoutX() - (Math.random()*30 + 20));
        }
    }

    public void moveRight() {
        if (right) {
            if (sprite.getLayoutX() < stackPane.getWidth() - 200 || sprite.getLayoutX() < 1680) sprite.setLayoutX(sprite.getLayoutX() + (Math.random()*30 + 20));
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
