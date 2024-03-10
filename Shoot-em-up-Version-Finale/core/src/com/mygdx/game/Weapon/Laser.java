package com.mygdx.game.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

// projectile lambda, nommé laser ici, rappel l'ordre des arguments du constructeur pour weapon est: x, y, width, height, speed et dmg



public class Laser extends Weapon{
    public static int WIDTH = 10;
    public static int HEIGHT = 30;
    public static int DAMAGE = 25;
    public static int SPEED = 25;

    public Laser(int x, int y, float speed, float dmg){
        // valeurs arbitraires
        super(x, y, speed,dmg, WIDTH, HEIGHT);
        this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "glowtube_small.png")));
    }

    public Laser(int x, int y){
        this(x, y, SPEED ,DAMAGE);
    }
}
