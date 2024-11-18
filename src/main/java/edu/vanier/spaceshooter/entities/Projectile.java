package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.paint.Color;

public class Projectile extends Sprite {

    Sprite sprite;
    String type;

    public Projectile(int x, int y, int width, int height, String type, Color color) {
        super(x, y, width, height, type, color);
        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
        this.sprite = new Sprite(x, y, width, height, type, color);
    }

    public void moveUp() {
        sprite.setTranslateY(sprite.getTranslateY() - 10);
    }

    public void moveDown() {
        sprite.setTranslateY(sprite.getTranslateY() + 10);
    }

    public Sprite getSprite(){
        return sprite;
    }

    public String getType(){
        return type;
    }
}
