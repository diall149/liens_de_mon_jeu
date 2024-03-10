package com.mygdx.game.BonusMalus.Life;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.Entite.PJ.Ship1;

public class Life extends BonusMalus {

    //class qui gère les augmentation / baisse de points de vie du joueur
    public Life(int x, int y, float value){
        super(x,y,value);
        if(value>=1){
            this.setSprite(new Texture(Gdx.files.internal(Sprite_directory + "bonus/life_bonus.png")));
        }
        else{
            this.setSprite(new Texture(Gdx.files.internal(Sprite_directory + "malus/life_malus.png")));
        }
    }

    // set up la vie du joueur
    @Override
    public void Add(Ship1 ship) {
        ship.setLife((int)(ship.getLife() + this.getValue()));
    }
}
