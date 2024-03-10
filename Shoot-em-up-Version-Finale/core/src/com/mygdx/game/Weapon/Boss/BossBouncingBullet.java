package com.mygdx.game.Weapon.Boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Weapon.Weapon;

import java.util.Random;

public class BossBouncingBullet extends Weapon {

    public static int WIDTH = 40;
    public static int HEIGHT = 40;
    public static int DAMAGE = 30;
    public static int SPEED = 7;

    private int bouncingBullet = -1;

    public BossBouncingBullet(int x, int y, float speed, float dmg) {
        super(x, y, speed, dmg,WIDTH, HEIGHT);
        this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "bouncing.png")));
    }

    public BossBouncingBullet(int x, int y){
        this(x,y,SPEED, DAMAGE);
    }

    public int getBouncingBullet() {
        return bouncingBullet;
    }

    public void setBouncingBullet(int bouncingBullet) {
        this.bouncingBullet = bouncingBullet;
    }
}
