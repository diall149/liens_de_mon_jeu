package com.mygdx.game.BonusMalus.Damage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.Entite.PJ.Ship1;

import java.util.Date;

public class Damage extends BonusMalus {

    //class qui gère les augmentation / baisse de dégâts des balles du joueur
    public Damage(int x, int y, float value){
        super(x,y,value);
        if(value>=1){
            this.setSprite(new Texture(Gdx.files.internal(Sprite_directory + "bonus/damage_bonus.png")));
        }
        else{
            this.setSprite(new Texture(Gdx.files.internal(Sprite_directory + "malus/damage_malus.png")));
        }
    }

    @Override
    // set up les damages des balles du vaisseau
    public void Add(Ship1 ship) {
        ship.setCoeffDamage(this.getValue());
        ship.setTimerDropDamage((int)new Date().getTime()/100);
    }
}
