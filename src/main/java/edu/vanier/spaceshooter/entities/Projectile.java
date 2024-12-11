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
    }

    public void moveUp() {
        sprite.setLayoutY(sprite.getLayoutY() - 10);
    }

    public void moveUpLeft45(){
        sprite.setRotate(135);
        sprite.setLayoutY(sprite.getLayoutY() - (5 * Math.sqrt(2)) );
        sprite.setLayoutX(sprite.getLayoutX() - (5 * Math.sqrt(2)) );
    }

    public void moveUpRight45(){
        sprite.setRotate(45);
        sprite.setLayoutY(sprite.getLayoutY() - (5 * Math.sqrt(2)) );
        sprite.setLayoutX(sprite.getLayoutX() + (5 * Math.sqrt(2)) );
    }

    public void moveDown() {
        sprite.setLayoutY(sprite.getLayoutY() + 10);
    }

    public Sprite getSprite(){
        return sprite;
    }

    public String getType(){
        return type;
    }
}
