package com.mygdx.game.BonusMalus.Speed;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.Entite.PJ.Ship1;

import java.util.Date;

public class Speed extends BonusMalus {

    //class qui gère les augmentation / baisse de la vitesse des balles du joueur
    public Speed(int x, int y, float value){
        super(x,y,value);
        if(value>=1){
            this.setSprite(new Texture(Gdx.files.internal(Sprite_directory + "bonus/speed_bonus.png")));
        }
        else{
            this.setSprite(new Texture(Gdx.files.internal(Sprite_directory + "malus/speed_malus.png")));
        }
    }

    @Override
    // set up la vitesse des balles du vaisseau
    public void Add(Ship1 ship) {
        ship.setCoeffSpeed(this.getValue());
        ship.setTimerDropSpeed((int)new Date().getTime()/1000);
    }

}
