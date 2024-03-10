package com.mygdx.game.Entite.PJ;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.Entite.Entite;
import com.mygdx.game.Weapon.*;
import com.mygdx.game.menu.GameScreen;

import java.util.Date;

public class Ship1 extends Entite{
    
    public Ship1(){
        // définir le constructeur du vaisseau en fonction des characteristiques souhaitées 
        super(20, 20, 50, 65, 150);
        this.setSprite(new Texture(Gdx.files.internal(Sprite_directory + "blueships1_small.png")));
    }

    @Override
    //définition de la fonction move pour le joueur en fonction du mouvement à attribuer
    public void move(){

        if(Gdx.input.getX() - getWidth() / 2 < GameScreen.screenGameWidthStart) setX(GameScreen.screenGameWidthStart);
        else if(Gdx.input.getX() + getWidth() / 2 > GameScreen.screenGameWidthEnd) setX(GameScreen.screenGameWidthEnd - getWidth());
        else setX(Gdx.input.getX() - getWidth() / 2);


        if(Gdx.graphics.getHeight() - Gdx.input.getY() - getHeight() / 2 < 0) setY(0);
        else if( Gdx.graphics.getHeight() - Gdx.input.getY() - getHeight() / 2 > Gdx.graphics.getHeight() / 4) setY(Gdx.graphics.getHeight() / 4);
        else setY(Gdx.graphics.getHeight() - Gdx.input.getY() - getHeight() / 2);

    }


    //en fonction de l'arme du joueur (String weapon), ajoute le tir correspondant
    public void shootBullets(){
        if(weapon == "Laser"){
            this.getTirs().add(TypeWeapon());
        }
        if(weapon == "BouncingBullet"){
            DoubleBouncingBullet bullet = new DoubleBouncingBullet(this.getX() + ((getWidth() + DoubleBouncingBullet.WIDTH) /2)-DoubleBouncingBullet.WIDTH, this.getY() + this.getHeight(),getCoeffSpeed()*DoubleBouncingBullet.SPEED,getCoeffDamage()*DoubleBouncingBullet.DAMAGE);
            bullet.setBouncingBullet(1);
            this.getTirs().add(TypeWeapon(bullet));
            this.getTirs().add(TypeWeapon(new DoubleBouncingBullet(this.getX() + ((getWidth() + DoubleBouncingBullet.WIDTH) /2)-DoubleBouncingBullet.WIDTH, this.getY() + this.getHeight(),getCoeffSpeed()*DoubleBouncingBullet.SPEED,getCoeffDamage()*DoubleBouncingBullet.DAMAGE)));
        }
        else if(weapon == "ExplosifBullet"){
            this.getTirs().add(TypeWeapon(new ExplosifBullet(this.getX() + ((getWidth() + ExplosifBullet.WIDTH) /2)-ExplosifBullet.WIDTH, this.getY() + this.getHeight(),getCoeffSpeed()*ExplosifBullet.SPEED,getCoeffDamage()*ExplosifBullet.DAMAGE)));
        }
        else if(weapon == "MultiBullet"){
            this.getTirs().add(TypeWeapon(new MultiBullet(this.getX() + ((getWidth() + MultiBullet.WIDTH) /2)-MultiBullet.WIDTH, this.getY() + this.getHeight(),0,getCoeffSpeed()* MultiBullet.SPEED,getCoeffDamage()*MultiBullet.DAMAGE)));
            this.getTirs().add(TypeWeapon(new MultiBullet(this.getX() + ((getWidth() + MultiBullet.WIDTH) /2)-MultiBullet.WIDTH, this.getY() + this.getHeight(),-4f,getCoeffSpeed()* MultiBullet.SPEED,getCoeffDamage()*MultiBullet.DAMAGE)));
            this.getTirs().add(TypeWeapon(new MultiBullet(this.getX() + ((getWidth() + MultiBullet.WIDTH) /2)-MultiBullet.WIDTH, this.getY() + this.getHeight(),4f,getCoeffSpeed()* MultiBullet.SPEED,getCoeffDamage()*MultiBullet.DAMAGE)));
        }
        else if(weapon == "LargeBullet"){
            this.getTirs().add(TypeWeapon(new LargeBullet(this.getX() + ((getWidth() + LargeBullet.WIDTH) /2)-LargeBullet.WIDTH, this.getY() + this.getHeight(),getCoeffSpeed()*LargeBullet.SPEED,getCoeffDamage()*LargeBullet.DAMAGE)));
        }
        else if(weapon == "BigBullet"){
            this.getTirs().add(TypeWeapon(new BigBullet(this.getX() + ((getWidth() + BigBullet.WIDTH) /2)-BigBullet.WIDTH, this.getY() + this.getHeight(),getCoeffSpeed()*BigBullet.SPEED,getCoeffDamage()*BigBullet.DAMAGE)));
        }
    }

    @Override
    public BonusMalus Drop() {
        return null;
    }


    //permet de changer le sprite pour un sprite invisible toutes les 0.2s
    public void setUpInvisibleSprite(){
        if(((int)new Date().getTime()/100) % 2 == 0){
            this.setSprite(new Texture(Gdx.files.internal("assets/invisible.png")));
        }
        else{
            this.setSprite(new Texture(Gdx.files.internal(Sprite_directory + "blueships1_small.png")));
        }

    }
}
