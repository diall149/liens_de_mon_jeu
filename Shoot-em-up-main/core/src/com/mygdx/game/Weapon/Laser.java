package com.mygdx.game.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

// projectile lambda, nommé laser ici, rappel l'ordre des arguments du constructeur pour weapon est: x, y, width, height, speed et dmg

public class Laser extends Weapon{
    public Laser(int x, int y){
        // valeurs arbitraires
        super(x, y, 10 , 30 , 15, 10);
        this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "glowtube_small.png")));
    }
}
