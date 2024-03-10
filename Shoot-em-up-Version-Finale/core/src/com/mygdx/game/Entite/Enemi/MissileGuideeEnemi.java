package com.mygdx.game.Entite.Enemi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.BonusMalus.Damage.Damage;
import com.mygdx.game.BonusMalus.Life.Life;
import com.mygdx.game.BonusMalus.Speed.Speed;
import com.mygdx.game.BonusMalus.Tir.Tir;
import com.mygdx.game.Entite.Enemi.Objets.MissileGuide;
import com.mygdx.game.Weapon.BigBullet;
import com.mygdx.game.menu.GameScreen;

public class MissileGuideeEnemi extends Enemi{
    public static int  WIDTH = 90;
    public static int  HEIGHT = 110;

    public MissileGuideeEnemi(int x, int y, int speed){
        super(x, y, speed, 200, WIDTH, HEIGHT);
        this.setSpeed(speed);
        this.setSprite(new Texture(Gdx.files.internal( Sprite_directory + "lance_missile.png")));
        this.setScore(800);
    }

    @Override
    //déplace l'entitee en aleatoirement en x en fonctin de la valeur de pattern
    public void move(){
        if(this.getY() > GameScreen.screenHeight - GameScreen.maxYShip){
            setY(getY() - getSpeed());
        }
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
    }

    @Override
    public void shootBullets() {
    }

    //renvoie une nouvelle instance de missile
    public MissileGuide ShootMissile(){
        return new MissileGuide(this.getX() + ((getWidth() + BigBullet.WIDTH) /2)-BigBullet.WIDTH, this.getY() - MissileGuide.HEIGHT, 6);
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
}
