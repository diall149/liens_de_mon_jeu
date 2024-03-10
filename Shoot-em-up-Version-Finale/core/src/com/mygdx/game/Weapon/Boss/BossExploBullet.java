package com.mygdx.game.Weapon.Boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Weapon.Weapon;
import com.mygdx.game.menu.GameScreen;

import java.util.Random;

public class BossExploBullet extends Weapon {
    public static int WIDTH = 70;
    public static int HEIGHT = 110;
    public static int DAMAGE = 40;
    public static int SPEED = 10;

    private boolean enemiBullet;

    private int randExplosion = new Random().nextInt(GameScreen.maxYShip);

    public int getRandExplosion() {
        return randExplosion;
    }

    public BossExploBullet(int x, int y, float speed, float dmg){
        // valeurs arbitraires
        super(x, y, speed,dmg, WIDTH, HEIGHT);
        this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "explo.png")));
        this.setHitboxExplo(new Rectangle(x - 100, y - 100, 80, 80));
    }

    public BossExploBullet(int x, int y){
        this(x, y, SPEED ,DAMAGE);
    }

}
