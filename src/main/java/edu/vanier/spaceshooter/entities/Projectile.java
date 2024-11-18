package edu.vanier.spaceshooter.entities;

import edu.vanier.spaceshooter.models.Sprite;
import javafx.scene.paint.Color;

public class Projectile extends Sprite {

    public Projectile(Sprite sprite){
        super((int) sprite.getX(), (int) sprite.getY(), (int) sprite.getWidth(), (int) sprite.getHeight(), sprite.getType(), sprite.getColor());
    }

    @Override
    public void moveUp() {
        setTranslateY(getTranslateY() - 10);
    }
}
