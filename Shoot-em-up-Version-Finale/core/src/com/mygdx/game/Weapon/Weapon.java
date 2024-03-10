package com.mygdx.game.Weapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Weapon {

   // private int firetime = (int)new Date().getTime()/100;
     // position du vaisseau sur l'écran
    private float x;
    private float y;
     // hauteur et largeur attribué au rectangle pour la hitbox
    private final int width;
    private final int height;
     // vitesse du tir
    private final float speed;
     // dommages infligé par le tir
    private final float dmg;
     // hitbox du projectile
    protected final Rectangle hitbox;

    private Rectangle hitboxExplo;
     // texture du projectile
    private Texture sprite;
    // chemin du dossier à compléter lors de la creation du projectile (comme pour entite)
    protected static final String SPRITE_AMMO_DIR = "assets/weapon/";

    private float elaspseTime = 0f;
        
    //getter et setter
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {this.x = x; }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDmg() {
        return dmg;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Rectangle getHitboxExplo() {
        return hitboxExplo;
    }

    public void setHitboxExplo(Rectangle hitboxExplo) {
        this.hitboxExplo = hitboxExplo;
    }

    public Texture getSprite() {
        return sprite;
    }

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }
     
    // constructeur
    public Weapon(int x, int y,float speed, float dmg, int width, int height) {
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

    public void hitboxExploUpdate(){
        hitboxExplo.setX(x);
        hitboxExplo.setY(y);
    }

    public float getElaspseTime() {
        return elaspseTime;
    }
    public void setElaspseTime(float elaspseTime) {
        this.elaspseTime = elaspseTime;
    }

}
