package com.mygdx.game.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class DoubleBouncingBullet extends Weapon{

    public static int WIDTH = 15;
    public static int HEIGHT = 15;
    public static int DAMAGE = 25;
    public static int SPEED = 10;

    //définit le mouvement en x de la balle
    private int bouncingBullet = -1;

    public DoubleBouncingBullet(int x, int y, float speed, float dmg) {
        super(x, y, speed, dmg,WIDTH, HEIGHT);
        this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "bouncing.png")));
    }

    public int getBouncingBullet() {
        return bouncingBullet;
    }

    public void setBouncingBullet(int bouncingBullet) {
        this.bouncingBullet = bouncingBullet;
    }
}