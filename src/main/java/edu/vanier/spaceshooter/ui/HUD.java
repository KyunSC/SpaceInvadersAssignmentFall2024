package edu.vanier.spaceshooter.ui;

import edu.vanier.spaceshooter.entities.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HUD {
    VBox HUD;
    Label levelLabel;
    Label scoreLabel;
    Label livesLabel;

    public HUD(VBox HUD, Label levelLabel, Label scoreLabel, Label livesLabel){
        this.HUD = HUD;
        this.levelLabel = levelLabel;
        this.scoreLabel = scoreLabel;
        this.livesLabel = livesLabel;
    }

    public VBox getHUD() {return HUD;}

    public Label getLevelLabel() {return levelLabel;}

    public Label getLivesLabel() {return livesLabel;}

    public Label setScorelabel(int score) {return new Label("Score: " + score);}
}
