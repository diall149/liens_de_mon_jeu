package com.mygdx.game.Entite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.Weapon.Laser;
import com.mygdx.game.Weapon.Weapon;
import com.mygdx.game.menu.GameScreen;

import java.util.Date;
import java.util.Random;
// Modifier game.ammo.Ammunition pour match le dossier des projectiles
//import com.mygdx.game.ammo.Ammunition;

public abstract class Entite {


    public Random r = new Random();

    private int score;

    // Timer de l'entité
    public int current_time = (int)new Date().getTime()/100;

    public int pattern_time  = (int)new Date().getTime()/100;

    private float elaspseTime = 0f;

    protected String weapon = "Laser";

    protected int pattern;
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

    private float coeffSpeed = 1;
    private float coeffDamage = 1;

    private int timerDropSpeed = 0;
    private int timerDropDamage = 0;
    private int timerDropTir = 0;

    private final Rectangle hitbox;
    // on aura juste besoin d'ajouter la fin du chemin pour aller chercher le sprite
    protected String Sprite_directory = "assets/entite/";

    private final Array<Weapon> tirs;

    //getter et setter pour la plupart des variables d'une entité

    public int  getX() {
        return x;
    }
    public void setX(int x) {
        if(x< GameScreen.screenGameWidthStart){
            x = GameScreen.screenGameWidthStart;
        }
        else if(x > GameScreen.screenGameWidthEnd - width){
                x = GameScreen.screenGameWidthEnd - width;
        }
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

    public void setPattern(int pattern){
        this.pattern = pattern;
    }

    public float getCoeffDamage() {
        return coeffDamage;
    }
    public void setCoeffDamage(float coeffDamage) {
        this.coeffDamage = coeffDamage;
    }

    public float getCoeffSpeed() {
        return coeffSpeed;
    }
    public void setCoeffSpeed(float coeffSpeed) {
        this.coeffSpeed = coeffSpeed;
    }

    public int getTimerDropDamage() {
        return timerDropDamage;
    }
    public void setTimerDropDamage(int timerDropDamage) {
        this.timerDropDamage = timerDropDamage;
    }

    public int getTimerDropSpeed() {
        return timerDropSpeed;
    }
    public void setTimerDropSpeed(int timerDropSpeed) {
        this.timerDropSpeed = timerDropSpeed;
    }

    public int getTimerDropTir() {
        return timerDropTir;
    }
    public void setTimerDropTir(int timerDropTir) {
        this.timerDropTir = timerDropTir;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
    public String getWeapon() {
        return weapon;
    }

    public abstract void move();

    public abstract void shootBullets();

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public Weapon TypeWeapon(Weapon weapon){
        return weapon;
    }
    public Weapon TypeWeapon(){
        return this.TypeWeapon(new Laser(this.getX() + ((getWidth() + Laser.WIDTH) /2)-Laser.WIDTH, this.getY() + this.getHeight(),getCoeffSpeed()*25,getCoeffDamage()*25));
    }

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

    public float getElaspseTime() {
        return elaspseTime;
    }
    public void setElaspseTime(float elaspseTime) {
        this.elaspseTime = elaspseTime;
    }

    abstract public BonusMalus Drop();

    abstract public void setUpInvisibleSprite();
}
