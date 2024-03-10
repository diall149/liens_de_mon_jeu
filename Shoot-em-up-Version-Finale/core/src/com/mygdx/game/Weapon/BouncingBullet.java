package com.mygdx.game.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class BouncingBullet extends Weapon{

    public static int WIDTH = 15;
    public static int HEIGHT = 15;
    public static int DAMAGE = 25;
    public static int SPEED = 10;

    //définit le mouvement en x de la balle aléatoirement
    private int[] bouncingBulletTab = {1,-1};
    private int bouncingBullet = bouncingBulletTab[new Random().nextInt(2)];

    public BouncingBullet(int x, int y, float speed, float dmg) {
        super(x, y, speed, dmg,WIDTH, HEIGHT);
        this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "bouncing.png")));
    }

    public BouncingBullet(int x, int y){
        this(x,y,SPEED, DAMAGE);
    }

    public int getBouncingBullet() {
        return bouncingBullet;
    }

    public void setBouncingBullet(int bouncingBullet) {
        this.bouncingBullet = bouncingBullet;
    }
}
