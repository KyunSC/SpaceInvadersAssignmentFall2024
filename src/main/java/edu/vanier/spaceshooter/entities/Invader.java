package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Invader extends Sprite {
    private boolean dead = false;
    private final String type;
    private Color color;
    private int x;
    private int y;
    private int width;
    private int height;
    private Sprite sprite;

    public Invader(int x, int y, int width, int height, String type, Image image) {
        super(x, y, width, height, type, image);
        this.type = type;
        setFitWidth(width);
        setFitHeight(height);
        this.sprite = new Sprite(x, y, width, height, type, image);
    }

    public void moveLeft() {
        sprite.setLayoutX(getLayoutX() - 5);
    }

    public void moveRight() {
        sprite.setLayoutX(getLayoutX() + 5);
    }

    public void moveUp() {
        sprite.setLayoutY(getLayoutY() - 5);
    }

    public void moveDown() {
        sprite.setLayoutY(getLayoutY() + 5);
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

    public Color getColor(){
        return color;
    }

    public Sprite getSprite(){
        return sprite;
    }
}
