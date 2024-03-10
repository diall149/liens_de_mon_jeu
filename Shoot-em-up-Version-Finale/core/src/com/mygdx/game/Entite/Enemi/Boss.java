package com.mygdx.game.Entite.Enemi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.BonusMalus.Damage.Damage;
import com.mygdx.game.BonusMalus.Life.Life;
import com.mygdx.game.BonusMalus.Speed.Speed;
import com.mygdx.game.BonusMalus.Tir.Tir;
import com.mygdx.game.Weapon.Boss.*;
import com.mygdx.game.menu.GameScreen;

import java.util.Date;
import java.util.Random;


public class Boss extends Enemi{

    private Random rand = new Random();
    protected int pattern_Boss;
    protected int pattern_BossTime = (int)new Date().getTime()/1000;
    public static int  WIDTH = 800;
    public static int  HEIGHT = 500;

    public Boss(int x, int y, int speed, int life){
        super(x, y, speed, life, WIDTH, HEIGHT);
        this.setSpeed(speed);
        this.setSprite(new Texture(Gdx.files.internal( Sprite_directory + "boss.png")));
        this.setScore(2000);
    }

    public void setPattern_Boss(int pattern_Boss) {
        this.pattern_Boss = pattern_Boss;
    }

    public int getPattern_BossTime() {
        return pattern_BossTime;
    }
    public void setPattern_BossTime(int pattern_BossTime) {
        this.pattern_BossTime = pattern_BossTime;
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
    //ajoute les tirs aléatoirement puis chosiit aléatoirement une des 3 possibilitées (de position des balles)/(nombre de balles) tirées dans le tableau de balles
    public void shootBullets() {
        int rand = r.nextInt(3);
        if(pattern_Boss == 0){
            if(rand == 0){
                this.getTirs().add(new BossBigBullet(this.getX() + ((getWidth() + BossBigBullet.WIDTH) /2)-BossBigBullet.WIDTH + getWidth()/3, this.getY() - BossBigBullet.HEIGHT));
                this.getTirs().add(new BossBigBullet(this.getX() + ((getWidth() + BossBigBullet.WIDTH) /2)-BossBigBullet.WIDTH - getWidth()/3, this.getY() - BossBigBullet.HEIGHT));
            }
            else if(rand == 1){
                this.getTirs().add(new BossBigBullet(this.getX() + ((getWidth() + BossBigBullet.WIDTH) /2)-BossBigBullet.WIDTH + getWidth()/3, this.getY() - BossBigBullet.HEIGHT));
                this.getTirs().add(new BossBigBullet(this.getX() + ((getWidth() + BossBigBullet.WIDTH) /2)-BossBigBullet.WIDTH - getWidth()/5, this.getY() - BossBigBullet.HEIGHT));
                this.getTirs().add(new BossBigBullet(this.getX() + ((getWidth() + BossBigBullet.WIDTH) /2)-BossBigBullet.WIDTH + getWidth()/5, this.getY() - BossBigBullet.HEIGHT));
                this.getTirs().add(new BossBigBullet(this.getX() + ((getWidth() + BossBigBullet.WIDTH) /2)-BossBigBullet.WIDTH - getWidth()/3, this.getY() - BossBigBullet.HEIGHT));
            }
            else{
                this.getTirs().add(new BossBigBullet(this.getX() + ((getWidth() + BossBigBullet.WIDTH) /2)-BossBigBullet.WIDTH - getWidth()/6, this.getY() - BossBigBullet.HEIGHT));
                this.getTirs().add(new BossBigBullet(this.getX() + ((getWidth() + BossBigBullet.WIDTH) /2)-BossBigBullet.WIDTH + getWidth()/6, this.getY() - BossBigBullet.HEIGHT));
            }
        }
        else if(pattern_Boss == 1){
            if(rand == 0){
                this.getTirs().add(new BossExploBullet(this.getX() + ((getWidth() + BossExploBullet.WIDTH) /2)-BossExploBullet.WIDTH + getWidth()/3, this.getY() - BossExploBullet.HEIGHT));
                this.getTirs().add(new BossExploBullet(this.getX() + ((getWidth() + BossExploBullet.WIDTH) /2)-BossExploBullet.WIDTH - getWidth()/3, this.getY() - BossExploBullet.HEIGHT));
            }
            else if(rand == 1){
                this.getTirs().add(new BossExploBullet(this.getX() + ((getWidth() + BossExploBullet.WIDTH) /2)-BossExploBullet.WIDTH + getWidth()/3, this.getY() - BossExploBullet.HEIGHT));
                this.getTirs().add(new BossExploBullet(this.getX() + ((getWidth() + BossExploBullet.WIDTH) /2)-BossExploBullet.WIDTH - getWidth()/6, this.getY() - BossExploBullet.HEIGHT));
                this.getTirs().add(new BossExploBullet(this.getX() + ((getWidth() + BossExploBullet.WIDTH) /2)-BossExploBullet.WIDTH + getWidth()/6, this.getY() - BossExploBullet.HEIGHT));
                this.getTirs().add(new BossExploBullet(this.getX() + ((getWidth() + BossExploBullet.WIDTH) /2)-BossExploBullet.WIDTH - getWidth()/3, this.getY() - BossExploBullet.HEIGHT));
            }
            else{
                this.getTirs().add(new BossExploBullet(this.getX() + ((getWidth() + BossExploBullet.WIDTH) /2)-BossExploBullet.WIDTH - getWidth()/6, this.getY() - BossExploBullet.HEIGHT));
                this.getTirs().add(new BossExploBullet(this.getX() + ((getWidth() + BossExploBullet.WIDTH) /2)-BossExploBullet.WIDTH + getWidth()/6, this.getY() - BossExploBullet.HEIGHT));
                this.getTirs().add(new BossExploBullet(this.getX() + ((getWidth() + BossExploBullet.WIDTH) /2)-BossExploBullet.WIDTH - getWidth()/3, this.getY() - BossExploBullet.HEIGHT));
            }

        }
        else if(pattern_Boss == 2){
            if(rand == 0){
                this.getTirs().add(new BossLargeBullet(this.getX() + ((getWidth() + BossLargeBullet.WIDTH) /2)-BossLargeBullet.WIDTH + getWidth()/3, this.getY() - BossLargeBullet.HEIGHT));
                this.getTirs().add(new BossLargeBullet(this.getX() + ((getWidth() + BossLargeBullet.WIDTH) /2)-BossLargeBullet.WIDTH - getWidth()/3, this.getY() - BossLargeBullet.HEIGHT));
            }
            else if(rand == 1){
                this.getTirs().add(new BossLargeBullet(this.getX() + ((getWidth() + BossLargeBullet.WIDTH) /2)-BossLargeBullet.WIDTH + getWidth()/4, this.getY() - BossLargeBullet.HEIGHT));
                this.getTirs().add(new BossLargeBullet(this.getX() + ((getWidth() + BossLargeBullet.WIDTH) /2)-BossLargeBullet.WIDTH + getWidth()/2 - 30, this.getY() - BossLargeBullet.HEIGHT));
                this.getTirs().add(new BossLargeBullet(this.getX() + ((getWidth() + BossLargeBullet.WIDTH) /2)-BossLargeBullet.WIDTH - getWidth()/4, this.getY() - BossLargeBullet.HEIGHT));
            }
            else{
                this.getTirs().add(new BossLargeBullet(this.getX() + ((getWidth() + BossLargeBullet.WIDTH) /2)-BossLargeBullet.WIDTH + getWidth()/2 - 30, this.getY() - BossLargeBullet.HEIGHT));
                this.getTirs().add(new BossLargeBullet(this.getX() + ((getWidth() + BossLargeBullet.WIDTH) /2)-BossLargeBullet.WIDTH - getWidth()/4, this.getY() - BossLargeBullet.HEIGHT));
            }

        }
        else if(pattern_Boss < 5){
            if(rand == 0){
                this.getTirs().add(new BossMultiBullet(this.getX() + ((getWidth() + BossMultiBullet.WIDTH) /2)-BossMultiBullet.WIDTH , this.getY() - BossLargeBullet.HEIGHT, 0));
                this.getTirs().add(new BossMultiBullet(this.getX() + ((getWidth() + BossMultiBullet.WIDTH) /2)-BossMultiBullet.WIDTH , this.getY() - BossLargeBullet.HEIGHT, -4f));
                this.getTirs().add(new BossMultiBullet(this.getX() + ((getWidth() + BossMultiBullet.WIDTH) /2)-BossMultiBullet.WIDTH , this.getY() - BossLargeBullet.HEIGHT, 4f));
            }
            else if(rand == 1){
                this.getTirs().add(new BossMultiBullet(this.getX() + ((getWidth() + BossMultiBullet.WIDTH) /2)-BossMultiBullet.WIDTH + getWidth()/2 , this.getY() - BossLargeBullet.HEIGHT, 0));
                this.getTirs().add(new BossMultiBullet(this.getX() + ((getWidth() + BossMultiBullet.WIDTH) /2)-BossMultiBullet.WIDTH + getWidth()/2 , this.getY() - BossLargeBullet.HEIGHT, -4f));
                this.getTirs().add(new BossMultiBullet(this.getX() + ((getWidth() + BossMultiBullet.WIDTH) /2)-BossMultiBullet.WIDTH + getWidth()/2 , this.getY() - BossLargeBullet.HEIGHT, 4f));
            }
            else{
                this.getTirs().add(new BossMultiBullet(this.getX() + ((getWidth() + BossMultiBullet.WIDTH) /2)-BossMultiBullet.WIDTH - getWidth()/2 , this.getY() - BossLargeBullet.HEIGHT, 0));
                this.getTirs().add(new BossMultiBullet(this.getX() + ((getWidth() + BossMultiBullet.WIDTH) /2)-BossMultiBullet.WIDTH - getWidth()/2 , this.getY() - BossLargeBullet.HEIGHT, -4f));
                this.getTirs().add(new BossMultiBullet(this.getX() + ((getWidth() + BossMultiBullet.WIDTH) /2)-BossMultiBullet.WIDTH - getWidth()/2 , this.getY() - BossLargeBullet.HEIGHT, 4f));
            }

        }
        else if(pattern_Boss <= 7){
            if(rand == 0){
                this.getTirs().add(new BossLaser(this.getX() + ((getWidth() + BossLaser.WIDTH) /2)-BossLaser.WIDTH - getWidth()/3, this.getY() - BossLaser.HEIGHT));
                this.getTirs().add(new BossLaser(this.getX() + ((getWidth() + BossLaser.WIDTH) /2)-BossLaser.WIDTH + getWidth()/3, this.getY() - BossLaser.HEIGHT));
                this.getTirs().add(new BossLaser(this.getX() + ((getWidth() + BossLaser.WIDTH) /2)-BossLaser.WIDTH + getWidth()/5, this.getY() - BossLaser.HEIGHT));
                this.getTirs().add(new BossLaser(this.getX() + ((getWidth() + BossLaser.WIDTH) /2)-BossLaser.WIDTH - getWidth()/5, this.getY() - BossLaser.HEIGHT));
            }
            else if(rand == 1){
                this.getTirs().add(new BossLaser(this.getX() + ((getWidth() + BossLaser.WIDTH) /2)-BossLaser.WIDTH - getWidth()/3, this.getY() - BossLaser.HEIGHT));
                this.getTirs().add(new BossLaser(this.getX() + ((getWidth() + BossLaser.WIDTH) /2)-BossLaser.WIDTH + getWidth()/3, this.getY() - BossLaser.HEIGHT));
                this.getTirs().add(new BossLaser(this.getX() + ((getWidth() + BossLaser.WIDTH) /2)-BossLaser.WIDTH + getWidth()/5, this.getY() - BossLaser.HEIGHT));
                this.getTirs().add(new BossLaser(this.getX() + ((getWidth() + BossLaser.WIDTH) /2)-BossLaser.WIDTH - getWidth()/5, this.getY() - BossLaser.HEIGHT));
                this.getTirs().add(new BossLaser(this.getX() + ((getWidth() + BossLaser.WIDTH) /2)-BossLaser.WIDTH + getWidth()/2, this.getY() - BossLaser.HEIGHT));
            }
            else{
                this.getTirs().add(new BossLaser(this.getX() + ((getWidth() + BossLaser.WIDTH) /2)-BossLaser.WIDTH + getWidth()/5, this.getY() - BossLaser.HEIGHT));
                this.getTirs().add(new BossLaser(this.getX() + ((getWidth() + BossLaser.WIDTH) /2)-BossLaser.WIDTH - getWidth()/5, this.getY() - BossLaser.HEIGHT));
            }

        }
        else if (pattern_Boss <= 9){
            if(rand == 0){
                this.getTirs().add(new BossBouncingBullet(this.getX() + ((getWidth() + BossBouncingBullet.WIDTH) /2)-BossBouncingBullet.WIDTH , this.getY() - BossBouncingBullet.HEIGHT));
                BossBouncingBullet bBullet = new BossBouncingBullet(this.getX() + ((getWidth() + BossBouncingBullet.WIDTH) /2)-BossBouncingBullet.WIDTH , this.getY() - BossBouncingBullet.HEIGHT);
                bBullet.setBouncingBullet(1);
                this.getTirs().add(bBullet);
            }
            else if(rand == 1){
                this.getTirs().add(new BossBouncingBullet(this.getX() + ((getWidth() + BossBouncingBullet.WIDTH) /2)-BossBouncingBullet.WIDTH , this.getY() - BossBouncingBullet.HEIGHT));
                BossBouncingBullet bBullet = new BossBouncingBullet(this.getX() + ((getWidth() + BossBouncingBullet.WIDTH) /2)-BossBouncingBullet.WIDTH , this.getY() - BossBouncingBullet.HEIGHT);
                bBullet.setBouncingBullet(1);
                this.getTirs().add(bBullet);
                this.getTirs().add(new BossBouncingBullet(this.getX() + ((getWidth() + BossBouncingBullet.WIDTH) /2)-BossBouncingBullet.WIDTH + getWidth()/3, this.getY() - BossBouncingBullet.HEIGHT));
                BossBouncingBullet bBullet2 = new BossBouncingBullet(this.getX() + ((getWidth() + BossBouncingBullet.WIDTH) /2)-BossBouncingBullet.WIDTH - getWidth()/3, this.getY() - BossBouncingBullet.HEIGHT);
                bBullet2.setBouncingBullet(1);
                this.getTirs().add(bBullet2);
            }
            else{
                this.getTirs().add(new BossBouncingBullet(this.getX() + ((getWidth() + BossBouncingBullet.WIDTH) /2)-BossBouncingBullet.WIDTH + getWidth()/3, this.getY() - BossBouncingBullet.HEIGHT));
                BossBouncingBullet bBullet2 = new BossBouncingBullet(this.getX() + ((getWidth() + BossBouncingBullet.WIDTH) /2)-BossBouncingBullet.WIDTH - getWidth()/3, this.getY() - BossBouncingBullet.HEIGHT);
                bBullet2.setBouncingBullet(1);
                this.getTirs().add(bBullet2);
            }

        }
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
                Dropped = new Speed(getX() + (WIDTH/2),getY(),0.5f);
            }
            else{
                Dropped = new Life(getX() + (WIDTH/2),getY(),-25);
            }
        }
        else{
            if(Type <= 25){
                Dropped = new Tir(getX() + (WIDTH/2),getY());
            }
            else if(Type > 25 && Type <= 50){
                Dropped = new Damage(getX() + (WIDTH/2),getY(),2);
            }
            else if(Type > 50 && Type <= 75){
                Dropped = new Speed(getX() + (WIDTH/2),getY(),2);
            }
            else{
                Dropped = new Life(getX() + (WIDTH/2),getY(),50);
            }
        }
        return Dropped;
    }
}
