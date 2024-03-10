package com.mygdx.game.BonusMalus.Tir;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.Entite.PJ.Ship1;

import java.util.Date;

public class Tir extends BonusMalus {

    //class qui gère les changements de tirs du joueur
    public Tir(int x, int y){
        super(x,y,10);
        this.setSprite(new Texture(Gdx.files.internal(Sprite_directory + "bonus/special_tir.png")));
    }

    @Override

    //set up un tir aléatoirement parmis les balles explosifs, rebondissantes, triple, large et big
    public void Add(Ship1 ship) {
        int rTir = random.nextInt((int)getValue());
        if(rTir == 0){
            ship.setWeapon("ExplosifBullet");
        }
        else if(rTir <= 4){
            ship.setWeapon("BouncingBullet");
        }
        else if(rTir <= 6){
            ship.setWeapon("MultiBullet");
        }
        else if(rTir <= 8){
            ship.setWeapon("LargeBullet");
        }
        else {
            ship.setWeapon("BigBullet");
        }
        ship.setTimerDropTir((int)new Date().getTime()/1000);
    }
}
