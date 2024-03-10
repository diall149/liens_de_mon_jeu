package com.mygdx.game.Levels;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entite.Enemi.*;
import com.mygdx.game.Entite.Enemi.Objets.Asteroid;
import com.mygdx.game.Entite.Enemi.Objets.GoldenAsteroid;
import com.mygdx.game.menu.GameScreen;
import com.mygdx.game.menu.ShootEmUpGame;
import com.mygdx.game.views.Setting;

public class Level3 extends Levels{

    protected final int enemiesAmount = 82;
    protected int nbParts = 8;
    protected int bossNumber = 1;

    public Level3(ShootEmUpGame shootEmUpGame, Setting setting){
        super(shootEmUpGame,setting);
        this.shootEmUpGame.addScreen(this);
        this.ships.add(ship1);
    }

    //génère les parties du niveau aléatoirement en fonction de la partie, les taux et les types d'enemies diffère
    protected void generationEntite(int nbEntitees, int part){
        if(part == 0){
            int slot = GameScreen.GameScreenWidth / ship1.getWidth();
            int typeEntitees = 4;
            for(int i = 0;i < nbEntitees ; i++){
                int rEntitee = rand.nextInt(typeEntitees*5);
                int rSlot = rand.nextInt(slot);
                int rYpos = rand.nextInt(GameScreen.screenHeight/ship1.getHeight());
                if(rEntitee <= 6){
                    objects.add(new Asteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 1));
                }
                else{
                    int randSpeed = rand.nextInt(7);
                    if(randSpeed < 3){
                        randSpeed = 2;
                    }
                    else {
                        randSpeed = 1;
                    }
                    if(rEntitee <= 10){
                        ships.add(new SuperEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else if(rEntitee <= 16){
                        ships.add(new ShieldEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else{
                        ships.add(new Enemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                }
            }
        }
        if(part == 1){
            int slot = GameScreen.GameScreenWidth / ship1.getWidth();
            int typeEntitees = 4;
            for(int i = 0;i < nbEntitees ; i++){
                int rEntitee = rand.nextInt(typeEntitees*5);
                int rSlot = rand.nextInt(slot);
                int rYpos = rand.nextInt(GameScreen.screenHeight/ship1.getHeight());
                if(rEntitee <= 6){
                    objects.add(new Asteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 1));
                }
                else{
                    int randSpeed = rand.nextInt(10);
                    if(randSpeed <= 3){
                        randSpeed = 3;
                    }
                    else if(randSpeed < 7){
                        randSpeed = 2;
                    }
                    else {
                        randSpeed = 1;
                    }
                    if(rEntitee <= 10){
                        ships.add(new SuperEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else if(rEntitee <= 16){
                        ships.add(new MultiBulletEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else{
                        ships.add(new Enemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                }
            }
        }
        if(part == 2){
            int slot = GameScreen.GameScreenWidth / ship1.getWidth();
            int typeEntitees = 5;
            for(int i = 0;i < nbEntitees ; i++){
                int rEntitee = rand.nextInt(typeEntitees*5);
                int rSlot = rand.nextInt(slot);
                int rYpos = rand.nextInt(GameScreen.screenHeight/ship1.getHeight());
                if(rEntitee <= 6){
                    objects.add(new Asteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 1));
                }
                else{
                    int randSpeed = rand.nextInt(10);
                    if(randSpeed <= 3){
                        randSpeed = 3;
                    }
                    else if(randSpeed <= 7){
                        randSpeed = 2;
                    }
                    else {
                        randSpeed = 1;
                    }
                    if(rEntitee <= 10){
                        ships.add(new SuperEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else if(rEntitee <= 14){
                        ships.add(new MultiBulletEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else if(rEntitee <= 20){
                        ships.add(new ShieldEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else{
                        ships.add(new Enemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                }
            }
        }
        if(part == 3){
            int slot = GameScreen.GameScreenWidth / ship1.getWidth();
            int typeEntitees = 4;
            for(int i = 0;i < nbEntitees ; i++){
                int rEntitee = rand.nextInt(typeEntitees*5);
                int rSlot = rand.nextInt(slot);
                int rYpos = rand.nextInt(GameScreen.screenHeight/ship1.getHeight());
                if(rEntitee <= 6){
                    objects.add(new Asteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 1));
                }
                else{
                    int randSpeed = rand.nextInt(10);
                    if(randSpeed <= 3){
                        randSpeed = 3;
                    }
                    else if(randSpeed <= 7){
                        randSpeed = 2;
                    }
                    else {
                        randSpeed = 1;
                    }
                    if(rEntitee <= 10){
                        ships.add(new MultiBulletEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else if(rEntitee <= 16){
                        ships.add(new LargeBulletEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else{
                        ships.add(new Enemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                }
            }
        }
        if(part == 4){
            int slot = GameScreen.GameScreenWidth / ship1.getWidth();
            int typeEntitees = 5;
            for(int i = 0;i < nbEntitees ; i++){
                int rEntitee = rand.nextInt(typeEntitees*5);
                int rSlot = rand.nextInt(slot);
                int rYpos = rand.nextInt(GameScreen.screenHeight/ship1.getHeight());
                if(rEntitee <= 6){
                    objects.add(new Asteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 1));
                }
                else{
                    int randSpeed = rand.nextInt(10);
                    if(randSpeed <= 3){
                        randSpeed = 3;
                    }
                    else if(randSpeed <= 7){
                        randSpeed = 2;
                    }
                    else {
                        randSpeed = 1;
                    }
                    if(rEntitee <= 10){
                        ships.add(new ShieldEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else if(rEntitee <= 16){
                        ships.add(new LargeBulletEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else if(rEntitee <= 22){
                        ships.add(new SuperEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else{
                        ships.add(new Enemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                }
            }
        }
        if(part == 5){
            int slot = GameScreen.GameScreenWidth / ship1.getWidth();
            int typeEntitees = 7;
            for(int i = 0;i < nbEntitees ; i++){
                int rEntitee = rand.nextInt(typeEntitees*5);
                int rSlot = rand.nextInt(slot);
                int rYpos = rand.nextInt(GameScreen.screenHeight/ship1.getHeight());
                if(rEntitee <= 6){
                    objects.add(new Asteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 1));
                }
                else if(rEntitee <= 9){
                    objects.add(new GoldenAsteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 3));
                }
                else{
                    int randSpeed = rand.nextInt(10);
                    if(randSpeed <= 3){
                        randSpeed = 3;
                    }
                    else if(randSpeed <= 7){
                        randSpeed = 2;
                    }
                    else {
                        randSpeed = 1;
                    }
                    if(rEntitee <= 15){
                        ships.add(new ShieldEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else if(rEntitee <= 22){
                        ships.add(new LargeBulletEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else if(rEntitee <= 27){
                        ships.add(new MultiBulletEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else if(rEntitee <= 31){
                        ships.add(new SuperEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else{
                        ships.add(new Enemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                }
            }
        }
        if(part == 6){
            int slot = GameScreen.GameScreenWidth / ship1.getWidth();
            int typeEntitees = 5;
            for(int i = 0;i < nbEntitees ; i++){
                int rEntitee = rand.nextInt(typeEntitees*5);
                int rSlot = rand.nextInt(slot);
                int rYpos = rand.nextInt(GameScreen.screenHeight/ship1.getHeight());
                if(rEntitee <= 4){
                    objects.add(new Asteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 1));
                }
                else if(rEntitee <= 6){
                    objects.add(new GoldenAsteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 3));
                }
                else{
                    int randSpeed = rand.nextInt(10);
                    if(randSpeed <= 3){
                        randSpeed = 3;
                    }
                    else if(randSpeed <= 7){
                        randSpeed = 2;
                    }
                    else {
                        randSpeed = 1;
                    }
                    if(rEntitee <= 11){
                        ships.add(new ShieldEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else if(rEntitee <= 20){
                        ships.add(new LargeBulletEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else{
                        ships.add(new MultiBulletEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                }
            }
        }
        if(part == 7){
            int slot = GameScreen.GameScreenWidth / ship1.getWidth();
            int typeEntitees = 6;
            for(int i = 0;i < nbEntitees ; i++){
                int rEntitee = rand.nextInt(typeEntitees*5);
                int rSlot = rand.nextInt(slot);
                int rYpos = rand.nextInt(((int)((GameScreen.screenHeight/ship1.getHeight())*1.5)));
                if(rEntitee <= 4){
                    objects.add(new Asteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 1));
                }
                else if(rEntitee <= 6){
                    objects.add(new GoldenAsteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 3));
                }
                else{
                    int randSpeed = rand.nextInt(10);
                    if(randSpeed <= 2){
                        randSpeed = 4;
                    }
                    else if(randSpeed <= 5){
                        randSpeed = 3;
                    }
                    else if(randSpeed <= 8){
                        randSpeed = 2;
                    }
                    else {
                        randSpeed = 1;
                    }
                    if(rEntitee <= 11){
                        ships.add(new ShieldEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else if(rEntitee <= 20){
                        ships.add(new LargeBulletEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else if(rEntitee <= 25){
                        ships.add(new SuperEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else{
                        ships.add(new MultiBulletEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                }
            }
        }
    }
    @Override
    public int getBossLife() {
        return 8000;
    }

    protected String getNumLevel(){
        return "Level3";
    }

    protected int getNbParts(){
        return this.nbParts;
    }

    protected int getEnemiesAmount(){
        return enemiesAmount;
    }

    public boolean checkVictory(){
        if(bosskilled == bossNumber) {
            return true;
        }
        return false;
    }

}

