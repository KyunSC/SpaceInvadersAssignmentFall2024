package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Projectile extends Sprite {

    Sprite sprite;
    String type;

    public Projectile(int x, int y, int width, int height, String type, Image image) {
        super(x, y, width, height, type, image);
        this.sprite = new Sprite(x, y, width, height, type, image);
        this.type = type;
        setLayoutX(x);
        setLayoutY(y);
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
