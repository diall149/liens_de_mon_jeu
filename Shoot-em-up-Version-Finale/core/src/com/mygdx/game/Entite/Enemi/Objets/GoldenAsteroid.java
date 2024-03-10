package com.mygdx.game.Entite.Enemi.Objets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.BonusMalus.Damage.Damage;
import com.mygdx.game.BonusMalus.Life.Life;
import com.mygdx.game.BonusMalus.Speed.Speed;
import com.mygdx.game.BonusMalus.Tir.Tir;

public class GoldenAsteroid extends Asteroid{
    public GoldenAsteroid(int x, int y, int speed){
        super(x,y,speed);
        this.setSprite(new Texture(Gdx.files.internal( "assets/entite/asteroid_dore.png")));
    }

    //renvoie un drop aléatoirement
    public BonusMalus Drop(){
        BonusMalus Dropped;
        int Type = r.nextInt(100);
            if(Type <= 50){
                Dropped = new Tir(getX(),getY());
            }
            else if(Type > 50 && Type <= 65){
                Dropped = new Damage(getX(),getY(),2);
            }
            else if(Type > 65 && Type <= 80){
                Dropped = new Speed(getX(),getY(),2);
            }
            else{
                Dropped = new Life(getX(),getY(),50);
            }
        return Dropped;
    }
}
