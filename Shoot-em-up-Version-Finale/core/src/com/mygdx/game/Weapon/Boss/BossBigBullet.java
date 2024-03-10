package com.mygdx.game.Weapon.Boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Weapon.Weapon;

public class BossBigBullet extends Weapon {
    public static int WIDTH = 75;
    public static int HEIGHT = 105;
    public static int DAMAGE = 100;
    public static int SPEED = 12;

    public BossBigBullet(int x, int y, float speed, float dmg){
        // valeurs arbitraires
        super(x, y, speed,dmg, WIDTH, HEIGHT);
        this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "big.png")));
    }

    public BossBigBullet(int x, int y){
        this(x, y, SPEED ,DAMAGE);
    }
}
