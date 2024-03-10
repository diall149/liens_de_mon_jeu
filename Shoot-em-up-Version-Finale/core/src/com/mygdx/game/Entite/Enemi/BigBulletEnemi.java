package com.mygdx.game.Entite.Enemi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.BonusMalus.Damage.Damage;
import com.mygdx.game.BonusMalus.Life.Life;
import com.mygdx.game.BonusMalus.Speed.Speed;
import com.mygdx.game.BonusMalus.Tir.Tir;
import com.mygdx.game.Weapon.BigBullet;
import com.mygdx.game.menu.GameScreen;

public class BigBulletEnemi extends Enemi{

    public static int  WIDTH = 75;
    public static int  HEIGHT = 100;

    public BigBulletEnemi(int x, int y, int speed){
        super(x, y, speed, 100, WIDTH, HEIGHT);
        this.setSpeed(speed);
        this.setSprite(new Texture(Gdx.files.internal( Sprite_directory + "big.png")));
        this.setScore(400);
    }


    @Override
    //déplace l'entitee en aleatoirement en x en fonction de la valeur de pattern
    public void move(){
        if(getX() <= GameScreen.screenGameWidthStart){
            if(pattern < 0){
                setPattern(-pattern);
            }
        }
        else if(getX() >= GameScreen.screenGameWidthEnd - getWidth()){
            if (pattern > 0) {
                setPattern(-pattern);
            }
        }
        setX(getX() + pattern);
        setY(getY() - getSpeed());
    }

    @Override
    //ajoute le tir associé à l'entité dans le tableau de balles
    public void shootBullets() {
        this.getTirs().add(new BigBullet(this.getX() + ((getWidth() + BigBullet.WIDTH) /2)-BigBullet.WIDTH, this.getY() - BigBullet.HEIGHT));
    }

    //renvoie un drop aléatoirement
    public BonusMalus Drop(){
        BonusMalus Dropped;
        int Type = r.nextInt(100);
        int BM = r.nextInt(20);
        if(BM == 0){
            if(Type <= 5){
                Dropped = new Damage(getX(),getY(),0.5f);
            }
            else if(Type > 5 && Type <= 37){
                Dropped = new Damage(getX(),getY(),0.5f);
            }
            else if(Type > 42 && Type <= 67){
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
}
