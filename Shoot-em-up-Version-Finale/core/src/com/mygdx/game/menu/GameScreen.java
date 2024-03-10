package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;

public class GameScreen {
    //constantes permettent de gérer les positions/tailles de l'écran
    public static final int screenWidth = Gdx.graphics.getWidth();
    public static final int screenHeight =Gdx.graphics.getHeight();
    public static final int screenGameWidthStart = Gdx.graphics.getWidth()/4;
    public static final int screenGameWidthEnd = screenWidth - Gdx.graphics.getWidth()/4;

    public static final int maxYShip = Gdx.graphics.getWidth()/6;
    public static final int centerX= screenWidth /2;
    public static final int centerY= screenHeight /2;
    public static final int co_width= screenWidth /8;
    public static final int row_height= screenHeight /8;
    public static final int GameScreenWidth = screenGameWidthEnd - screenGameWidthStart;
}
