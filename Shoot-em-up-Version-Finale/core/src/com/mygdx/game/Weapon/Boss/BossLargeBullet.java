package com.mygdx.game.Weapon.Boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Weapon.Weapon;

public class BossLargeBullet extends Weapon {
    public static int WIDTH = 200;
    public static int HEIGHT = 20;
    public static int DAMAGE = 40;
    public static int SPEED = 10;

    public BossLargeBullet(int x, int y, float speed, float dmg){
        // valeurs arbitraires
        super(x, y, speed,dmg, WIDTH, HEIGHT);
        this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "large.png")));
    }

    public BossLargeBullet(int x, int y){
        this(x, y, SPEED ,DAMAGE);
    }
}
