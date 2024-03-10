package com.mygdx.game.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class LargeBullet extends Weapon{
    public static int WIDTH = 150;
    public static int HEIGHT = 20;
    public static int DAMAGE = 20;
    public static int SPEED = 8;

    public LargeBullet(int x, int y, float speed, float dmg){
        // valeurs arbitraires
        super(x, y, speed,dmg, WIDTH, HEIGHT);
        this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "large.png")));
    }

    public LargeBullet(int x, int y){
        this(x, y, SPEED ,DAMAGE);
    }
}

