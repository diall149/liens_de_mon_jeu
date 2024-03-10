package com.mygdx.game.BonusMalus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entite.PJ.Ship1;

import java.util.Random;

abstract public class BonusMalus {

    // class qui gère l'implémentation des bonus dans les niveaux
    private int x;
    private int y;

    protected Random random = new Random();

    private int height;
    private int width;

    private float value;

    private Texture sprite;

    private final Rectangle hitbox;

    public Rectangle getHitbox() {
        return hitbox;
    }

    protected String Sprite_directory = "assets/bonus_malus/";

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

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public float getValue() {
        return value;
    }

    public Texture getSprite() {
        return sprite;
    }
    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }

    public BonusMalus(int x, int y , int width, int height, float value){
        this.x = x;
        this.y = y;
        this.value = value;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, width, height);
    }
    public BonusMalus(int x, int y, float value){
        this(x,y,35,45,value);
    }

    public void move(){
        y -=5;
    }

    public void hitboxUpdate(){
        hitbox.setX(x);
        hitbox.setY(y);
    }

    abstract public void Add(Ship1 ship);
}
