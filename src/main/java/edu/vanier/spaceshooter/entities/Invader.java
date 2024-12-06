package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Stack;

public class Invader extends Sprite {
    private boolean dead = false;
    private final String type;
    private Color color;
    private int x;
    private int y;
    private int width;
    private int height;
    private Sprite sprite;
    private boolean moving = false;
    private StackPane stackPane;

    public Invader(int x, int y, int width, int height, String type, Image image, StackPane stackPane) {
        super(x, y, width, height, type, image);
        this.stackPane = stackPane;
        this.type = type;
        setFitWidth(width);
        setFitHeight(height);
        this.sprite = new Sprite(x, y, width, height, type, image);
    }

    public void movementPattern(){
        double random = Math.ceil(Math.random()*4);
        if (random == 1) moveUp();
        else if (random == 2) moveDown();
        else if (random == 3) moveLeft();
        else if (random == 4) moveRight();
    }

    public void moveLeft() {
        if (moving) {
            if (sprite.getLayoutX() > 0) sprite.setLayoutX(sprite.getLayoutX() - 40);
        }
    }

    public void moveRight() {
        if (moving) {
            if (sprite.getLayoutY() < stackPane.getWidth())sprite.setLayoutX(sprite.getLayoutX() + 40);
        }
    }

    public void moveUp() {
        if (moving) {
            if (sprite.getLayoutY() > 0)sprite.setLayoutY(sprite.getLayoutY() - 40);
        }
    }

    public void moveDown() {
        if (moving) {
            if (sprite.getLayoutY() < stackPane.getHeight())sprite.setLayoutY(sprite.getLayoutY() + 40);
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
}
