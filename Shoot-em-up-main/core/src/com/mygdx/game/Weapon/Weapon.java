package com.mygdx.game.Weapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Weapon {
     // position du vaisseau sur l'écran
    private final int x;
    private int y;
     // hauteur et largeur attribué au rectangle pour la hitbox
    private final int width;
    private final int height;
     // vitesse du tir
    private final int speed;
     // dommages infligé par le tir
    private final int dmg;
     // hitbox du projectile
    private final Rectangle hitbox;
     // texture du projectile
    private Texture sprite;
    // chemin du dossier à compléter lors de la creation du projectile (comme pour entite)
    protected static final String SPRITE_AMMO_DIR = "assets/shmup/";
        
    //getter et setter
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDmg() {
        return dmg;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Texture getSprite() {
        return sprite;
    }

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }
     
    // constructeur
    public Weapon(int x, int y, int width, int height, int speed, int dmg) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.dmg = dmg;
        this.hitbox = new Rectangle(x, y, width, height);
    }
    // methode qui permettre d'actualiser la position du projectile
    public void hitboxUpdate() {
        hitbox.setX(x);
        hitbox.setY(y);
    }

}
