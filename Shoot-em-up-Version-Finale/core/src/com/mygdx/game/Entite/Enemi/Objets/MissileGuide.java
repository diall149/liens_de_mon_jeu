package com.mygdx.game.Entite.Enemi.Objets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.Entite.Entite;

import java.util.Date;

public class MissileGuide extends Entite{
    private static int WIDTH = 30;
    public static int HEIGHT = 60;
    private static int LIFE = 100;
    private static int DAMAGE = 100;
    private int missile_time = (int)new Date().getTime()/100;
    private int direction = 0;

    public MissileGuide(int x, int y, int speed) {
        super(x,y,WIDTH,HEIGHT,LIFE);
        this.setSpeed(speed);
        this.setSprite(new Texture(Gdx.files.internal( "assets/entite/missile.png")));
    }

    public void move(){

    }
    // suit le joueurs en x toutes les 1s et demi
    public void move(Entite ship){
        if(this.missile_time + 5 == (int)new Date().getTime()/100){
            if(ship.getX() > this.getX()){
                direction = 3;
            }
            else if(ship.getX() < this.getX()){
                direction = -3;
            }
        }
        else if(this.missile_time + 5 < (int)new Date().getTime()/100){
            missile_time = (int)new Date().getTime()/100;
        }
        setX(getX() + direction);
        setY(getY() - getSpeed());
    }


    @Override
    public void shootBullets() {
    }

    public int getDamage(){
        return DAMAGE;
    }

    public BonusMalus Drop(){
        return null;
    }

    public void setUpInvisibleSprite(){

    }
}
