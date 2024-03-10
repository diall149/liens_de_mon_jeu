package com.mygdx.game.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.views.Setting;


public class ShootEmUpGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	//tableau pour stocke les objet de type Screen
	private Array<Screen> screens =new Array<>();

	private Setting setting;






	public void addScreen(Screen screen){
		this.screens.add(screen);
	}

	@Override
	public void create () {


		//pour dessiner des  sprites et des textures
		batch=new SpriteBatch();

		//pour dessiner du texte sur l'ecran
		font=new BitmapFont();
		setting=new Setting();
		setting.playBackgroundMusic();


		//exectuer l'ecran de demmarage
		Screen start = new Start(this, setting);


		this.addScreen(start);
		this.setScreen(start);

	}

	/* la methode à appler pour changer les ecrans
	public void setScreen(Screen screen) {
		this.screens.add(screen);
	} */

	@Override
	public void render () {
		ScreenUtils.clear(Color.WHITE);
		super.render();

	}
	public Setting getSetting(){
		return setting;
	}


	//liberez les ressourses utiliser dans chaque screen

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();

		for(Screen s:screens){
			s.dispose();
		}


	}

}
