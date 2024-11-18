package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
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

    public Invader(int x, int y, int width, int height, String type, Color color) {
        super(x, y, width, height, type, color);
        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
        this.sprite = new Sprite(x, y, width, height, type, color);
    }

    public void moveLeft() {
        setTranslateX(getTranslateX() - 5);
    }

    public void moveRight() {
        setTranslateX(getTranslateX() + 5);
    }

    public void moveUp() {
        setTranslateY(getTranslateY() - 5);
    }

    public void moveDown() {
        setTranslateY(getTranslateY() + 5);
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
