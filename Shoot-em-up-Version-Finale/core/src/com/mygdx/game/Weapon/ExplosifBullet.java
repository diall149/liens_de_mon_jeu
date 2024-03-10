package com.mygdx.game.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.menu.GameScreen;

import java.util.Random;

public class ExplosifBullet extends Weapon{
    public static int WIDTH = 30;
    public static int HEIGHT = 40;
    public static int DAMAGE = 15;
    public static int SPEED = 11;

    private boolean enemiBullet;

    //définit la position d'explosion aléatoirement en y après avoir effectué au moins 1/6 de la taille de l'écran
    private int randExplosion = new Random().nextInt(GameScreen.maxYShip);

    public int getRandExplosion() {
        return randExplosion;
    }

    public boolean isEnemiBullet() {
        return enemiBullet;
    }
    public void setEnemiBullet(boolean enemiBullet) {
        this.enemiBullet = enemiBullet;
    }

    public ExplosifBullet(int x, int y, float speed, float dmg){
        // valeurs arbitraires
        super(x, y, speed,dmg, WIDTH, HEIGHT);
        this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "explo.png")));
        this.setHitboxExplo(new Rectangle(x - 75, y - 75, 150, 150));
    }

    public ExplosifBullet(int x, int y){
        this(x, y, SPEED ,DAMAGE);
    }

}