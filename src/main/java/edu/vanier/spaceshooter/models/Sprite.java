package edu.vanier.spaceshooter.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sprite extends ImageView {

    private boolean dead = false;
    private final String type;
    private int x;
    private int y;
    private int width;
    private int height;

    public Sprite(double x, double y, double width, double height, String type, Image image) {
        setImage(image);
        setFitWidth(width);
        setFitHeight(height);
        this.type = type;
        setLayoutX(x);
        setLayoutY(y);
    }

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
