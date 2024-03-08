package com.mygdx.game.Entite.PJ;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entite.Entite;
import com.mygdx.game.Weapon.Laser;

public class Ship1 extends Entite{
    public Ship1(){
        // définir le constructeur du vaisseau en fonction des characteristiques souhaitées 
        super(20, 20, 100, 130, 100);
        this.setSprite(new Texture(Gdx.files.internal(Sprite_directory + "plane.png")));
    }

    @Override
    //définition de la fonction move pour le joueur en fonction du mouvement à attribuer
    public void move(){

        if(Gdx.input.getX() - getWidth() / 2 < 0) setX(0);
        else if(Gdx.input.getX() + getWidth() / 2 > Gdx.graphics.getWidth()) setX(Gdx.graphics.getWidth() - getWidth());
        else setX(Gdx.input.getX() - getWidth() / 2);


        if(Gdx.graphics.getHeight() - Gdx.input.getY() - getHeight() / 2 < 0) setY(0);
        else if( Gdx.graphics.getHeight() - Gdx.input.getY() - getHeight() / 2 > Gdx.graphics.getHeight() / 6) setY(Gdx.graphics.getHeight() / 6);
        else setY(Gdx.graphics.getHeight() - Gdx.input.getY() - getHeight() / 2);

    }

    public void shootBullets(){
        this.getTirs().add(new Laser(this.getX() + getWidth()/2, this.getY() + this.getHeight()));
    }
}
