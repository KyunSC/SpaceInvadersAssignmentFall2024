package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;

public class Projectile extends Sprite {

    Sprite sprite;
    String type;

    public Projectile(Sprite sprite){
        super((int) sprite.getX(), (int) sprite.getY(), (int) sprite.getWidth(), (int) sprite.getHeight(), sprite.getType(), sprite.getColor());
        this.sprite = sprite;
        this.type = sprite.getType();
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
