package com.mygdx.game.Levels;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entite.Enemi.*;
import com.mygdx.game.Entite.Enemi.Objets.Asteroid;
import com.mygdx.game.Entite.Enemi.Objets.GoldenAsteroid;
import com.mygdx.game.menu.GameScreen;
import com.mygdx.game.menu.ShootEmUpGame;
import com.mygdx.game.views.Setting;

public class Level1 extends Levels{

    protected final int enemiesAmount = 50;
    protected int nbParts = 5;
    protected int bossNumber = 1;

    public Level1(ShootEmUpGame shootEmUpGame, Setting setting){
        super(shootEmUpGame, setting);
        this.shootEmUpGame.addScreen(this);

        this.ships.add(ship1);
    }


    //génère une partie du niveau aléatoirement en fonction de la partie, les taux et les types d'enemies diffère
    protected void generationEntite(int nbEntitees, int part){
        if(part == 0){
            int slot = GameScreen.GameScreenWidth / ship1.getWidth();
            for(int i = 0;i < nbEntitees ; i++){
                int rSlot = rand.nextInt(slot);
                int rYpos = rand.nextInt(GameScreen.screenHeight/ship1.getHeight());
                ships.add(new Enemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 1));
            }
        }
        else if(part == 1){
            int slot = GameScreen.GameScreenWidth / ship1.getWidth();;
            for(int i = 0;i < nbEntitees ; i++){
                int randSpeed = rand.nextInt(3);
                if(randSpeed == 0){
                    randSpeed = 2;
                }
                else{
                    randSpeed = 1;
                }
                int rSlot = rand.nextInt(slot);
                int rYpos = rand.nextInt(GameScreen.screenHeight/ship1.getHeight());
                ships.add(new Enemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
            }
        }
        else if(part == 2){
            int slot = GameScreen.GameScreenWidth / ship1.getWidth();
            int typeEntitees = 2;
            for(int i = 0;i < nbEntitees ; i++){
                int rEntitee = rand.nextInt(typeEntitees*2);
                int rSlot = rand.nextInt(slot);
                int rYpos = rand.nextInt(GameScreen.screenHeight/ship1.getHeight());
                if(rEntitee == 0){
                    ships.add(new SuperEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 1));
                }
                else{
                    int randSpeed = rand.nextInt(3);
                    if(randSpeed == 0){
                        randSpeed = 2;
                    }
                    else{
                        randSpeed = 1;
                    }
                    ships.add(new Enemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                }
            }
        }
        else if(part == 3){
            int slot = GameScreen.GameScreenWidth / ship1.getWidth();
            int typeEntitees = 4;
            for(int i = 0;i < nbEntitees ; i++){
                int rEntitee = rand.nextInt(typeEntitees*5);
                int rSlot = rand.nextInt(slot);
                int rYpos = rand.nextInt(GameScreen.screenHeight/ship1.getHeight());
                if(rEntitee == 0){
                    objects.add(new GoldenAsteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 3));
                }
                else if(rEntitee <= 5){
                    objects.add(new Asteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 1));
                }
                else{
                    int randSpeed = rand.nextInt(5);
                    if(randSpeed == 0){
                        randSpeed = 3;
                    }
                    else if (randSpeed <= 2){
                        randSpeed = 2;
                    }
                    else {
                        randSpeed = 1;
                    }
                    if(rEntitee <= 11){
                        ships.add(new SuperEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else{
                        ships.add(new Enemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                }
            }
        }
        else if(part == 4){
            int slot = GameScreen.GameScreenWidth / ship1.getWidth();
            int typeEntitees = 4;
            for(int i = 0;i < nbEntitees ; i++){
                int rEntitee = rand.nextInt(typeEntitees*5);
                int rSlot = rand.nextInt(slot);
                int rYpos = rand.nextInt(GameScreen.screenHeight/ship1.getHeight());
                if(rEntitee == 0){
                    objects.add(new GoldenAsteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 3));
                }
                else if(rEntitee <= 5){
                    objects.add(new Asteroid((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), 1));
                }
                else{
                    int randSpeed = rand.nextInt(7);
                    if(randSpeed == 0){
                        randSpeed = 4;
                    }
                    else if (randSpeed <= 2){
                        randSpeed = 3;
                    }
                    if(randSpeed <= 5){
                        randSpeed = 2;
                    }
                    else {
                        randSpeed = 1;
                    }
                    if(rEntitee <= 13){
                        ships.add(new SuperEnemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                    else{
                        ships.add(new Enemi((rSlot)*(ship1.getWidth()) + GameScreen.screenGameWidthStart, Gdx.graphics.getHeight() + ship1.getHeight()*(rYpos), randSpeed));
                    }
                }
            }
        }
    }

    @Override
    public int getBossLife() {
        return 2000;
    }

    protected String getNumLevel(){
        return "Level1";
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

