package com.mygdx.game.Entite.Enemi;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.BonusMalus.Damage.Damage;
import com.mygdx.game.BonusMalus.Life.Life;
import com.mygdx.game.BonusMalus.Speed.Speed;
import com.mygdx.game.BonusMalus.Tir.Tir;
import com.mygdx.game.Entite.Entite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Weapon.Laser;
import com.mygdx.game.menu.GameScreen;

public class Enemi extends Entite {

    public static int  WIDTH = 80;
    public static int  HEIGHT = 60;

    public Enemi(int x, int y, int speed, int life, int width, int height){
        super(x, y, width,height, life);
        this.setSpeed(speed);
        this.setSprite(new Texture(Gdx.files.internal( Sprite_directory + "enemi.png")));
        this.setScore(100);
    }

    public Enemi(int x, int y, int speed, int life){
        this(x,y,speed,life,WIDTH,HEIGHT);
    }
    public Enemi(int x, int y, int speed){
        this(x,y,speed,50);
    }

    @Override
    //déplace l'entitee en aleatoirement en x en fonctin de la valeur de pattern
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
        this.getTirs().add(new Laser(this.getX() + ((getWidth() + Laser.WIDTH) /2)-Laser.WIDTH, this.getY() - Laser.HEIGHT));
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
