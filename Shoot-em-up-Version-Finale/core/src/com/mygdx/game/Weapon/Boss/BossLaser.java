package com.mygdx.game.Weapon.Boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Weapon.Weapon;

public class BossLaser extends Weapon {
    //Laser du Boss
    public static int WIDTH = 40;
    public static int HEIGHT = 60;
    public static int DAMAGE = 40;
    public static int SPEED = 20;

    public BossLaser(int x, int y, float speed, float dmg){
        // valeurs arbitraires
        super(x, y, speed,dmg, WIDTH, HEIGHT);
        this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "glowtube_small.png")));
    }

    public BossLaser(int x, int y){
        this(x, y, SPEED ,DAMAGE);
    }
}
