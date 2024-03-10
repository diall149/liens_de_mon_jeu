package com.mygdx.game.Entite.Enemi.Objets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.BonusMalus.Damage.Damage;
import com.mygdx.game.BonusMalus.Life.Life;
import com.mygdx.game.BonusMalus.Speed.Speed;
import com.mygdx.game.BonusMalus.Tir.Tir;
import com.mygdx.game.Entite.Entite;

public class Asteroid extends Entite {

    private static int WIDTH = 40;
    private  static int HEIGHT = 60;
    private static int LIFE = 100;
    private static int DAMAGE = 80;

    public Asteroid(int x, int y, int speed) {
        super(x,y,WIDTH,HEIGHT,LIFE);
        this.setSpeed(speed);
        this.setSprite(new Texture(Gdx.files.internal( "assets/entite/asteroid.png")));
    }
    @Override
    public void move(){
        setY(getY() - getSpeed());
    }

    @Override
    public void shootBullets() {
    }

    public int getDamage(){
        return DAMAGE;
    }


    //renvoie un drop aléatoirement
    public BonusMalus Drop(){
        BonusMalus Dropped;
        int Type = r.nextInt(100);
        int BM = r.nextInt(20);
        if(BM == 0){
            if(Type <= 33){
                Dropped = new Damage(getX(),getY(),0.5f);
            }
            else if(Type > 33 && Type <= 66){
                Dropped = new Speed(getX(),getY(),0.5f);
            }
            else{
                Dropped = new Life(getX(),getY(),-25);
            }
        }
        else{
            if(Type <= 25){
                Dropped = new Tir(getX(),getY());
            }
            else if(Type > 25 && Type <= 50){
                Dropped = new Damage(getX(),getY(),2);
            }
            else if(Type > 50 && Type <= 75){
                Dropped = new Speed(getX(),getY(),2);
            }
            else{
                Dropped = new Life(getX(),getY(),50);
            }
        }
        return Dropped;
    }

    public void setUpInvisibleSprite(){
    }
}
