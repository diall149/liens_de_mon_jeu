package com.mygdx.game.Weapon.Boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Weapon.Weapon;

public class BossMultiBullet extends Weapon {
    //MultiBullet du Boss
    public static int WIDTH = 25;
    public static int HEIGHT = 50;
    public static int DAMAGE = 30;
    public static int SPEED = 15;

    private float direction;

    public float getDirection() {
        return direction;
    }

    public BossMultiBullet(int x, int y,float direction, float speed, float dmg){
        // valeurs arbitraires
        super(x, y, speed,dmg, WIDTH, HEIGHT);
        this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "multi.png")));
        this.direction = direction;
    }

    public BossMultiBullet(int x, int y, float direction){
        this(x, y, direction, SPEED ,DAMAGE);
    }
}
