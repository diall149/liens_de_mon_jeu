package com.mygdx.game.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BigBullet extends Weapon{

    public static int WIDTH = 45;
    public static int HEIGHT = 65;
    public static int DAMAGE = 50;
    public static int SPEED = 17;

    public BigBullet(int x, int y, float speed, float dmg){
        // valeurs arbitraires
        super(x, y, speed,dmg, WIDTH, HEIGHT);
        this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "big.png")));
    }

    public BigBullet(int x, int y){
        this(x, y, SPEED ,DAMAGE);
    }
}
