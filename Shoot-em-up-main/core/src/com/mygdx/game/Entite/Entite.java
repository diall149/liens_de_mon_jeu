package com.mygdx.game.Entite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Weapon.Weapon;
// Modifier game.ammo.Ammunition pour match le dossier des projectiles
//import com.mygdx.game.ammo.Ammunition;

public abstract class Entite {
    // Position de l'entité en x et y
    private int x;
    private int y;
    // points de vie de l'entité
    private int life;
    // texture appliqué à l'entité
    private Texture sprite;
    // hauteur et largeur de la hitbox (le rectangle)
    private final int width;
    private final int height;
    // vitesse de l'entité
    private int speed;
    private final Rectangle hitbox;
    // on aura juste besoin d'ajouter la fin du chemin pour aller chercher le sprite
    protected String Sprite_directory = "assets/shmup/";

    private final Array<Weapon> tirs;

    //getter et setter pour la plupart des variables d'une entité

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
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
    public void setSpeed(int speed){
        this.speed = speed;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Array<Weapon> getTirs() { return tirs; }

    public Texture getSprite() {
        return sprite;
    }

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }

    public abstract void move();

    public abstract void shootBullets();


    //constructeur de l'entité, x, y la position, width et height la taille de la hitbox et life les points de vies de l'entité
    public Entite(int x, int y, int width, int height, int life) {
        this.x = x;
        this.y = y;
        this.life = life;
        this.width = width;
        this.height = height;
        this.tirs = new Array<>();
        this.hitbox = new Rectangle(x, y, width, height);
    }

    public void hitboxUpdate(){
        hitbox.setX(x);
        hitbox.setY(y);
    }


}
