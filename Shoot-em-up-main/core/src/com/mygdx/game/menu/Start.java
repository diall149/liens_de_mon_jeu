package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.org.apache.bcel.internal.generic.LADD;

public class Start implements Screen {
    private Skin myskin;

    final ShootEmUpGame shootemupgame;
    
    //creation de la camera
    OrthographicCamera camera;
    private Stage stage;

    private TextField usernameTextField;
    
    //méthode pour set la camera
    public Start(final ShootEmUpGame shootemupgame){
        this.shootemupgame = shootemupgame;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 750);

        myskin=new Skin(Gdx.files.internal(GameConstants.skin));

        stage=new Stage();
        Label title=new Label("SHOOTER III",myskin,"big");
        title.setSize(GameConstants.co_width*2,GameConstants.row_height*2);
        title.setPosition(GameConstants.centerX-title.getWidth()/2,GameConstants.centerY+GameConstants.row_height+100);
        title.setAlignment(Align.center);

        Button startbtn=new TextButton("START GAME",myskin,"small");
        startbtn.setSize(GameConstants.co_width*2,GameConstants.row_height);
        startbtn.setPosition(GameConstants.centerX-startbtn.getWidth()/2,GameConstants.centerY);

        Button settingBtn=new TextButton("SETTING",myskin,"small");
        settingBtn.setSize(GameConstants.co_width*2,GameConstants.row_height);
        settingBtn.setPosition(GameConstants.centerX-settingBtn.getWidth()/2,startbtn.getY()-GameConstants.row_height-15);



        stage.addActor(title);
        stage.addActor(startbtn);
        stage.addActor(settingBtn);


        /*
        Gdx.input.setInputProcessor(stage);

        Table table =new Table();
        table.setFillParent(true);
        stage.addActor(table);






        Label username=new Label("PSEUDO: ",new Label.LabelStyle(new BitmapFont(),Color.WHITE ));
        table.add(username).padTop(10).align(Align.left);


        TextField.TextFieldStyle textFieldStyle=new TextField.TextFieldStyle();
        textFieldStyle.font=new BitmapFont();
        textFieldStyle.background=createDrawable(100,30, Color.BLACK);
        textFieldStyle.focusedFontColor=Color.WHITE;

        usernameTextField=new TextField("",textFieldStyle);
        usernameTextField.setAlignment(Align.center);
        table.add(usernameTextField).width(200).padTop(10).align(Align.left);




        TextButton valider=new TextButton("OK",new TextButton.TextButtonStyle(
                createDrawable(80,30,Color.BLUE),
                createDrawable(80,30,Color.BLUE),
                createDrawable(80,30,Color.BLUE),
                new BitmapFont()

        ));

      table.add(valider).padTop(10).padLeft(10).align(Align.left);

        */

    }
/*
    private Drawable createDrawable(int with, int height, Color color) {
        Pixmap pixmap=new Pixmap(with,height,Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        Texture texture=new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegionDrawable(new TextureRegion(texture));
    }

 */

    //méthode pour render le start screen
    public void render(float delta){
        ScreenUtils.clear(1, 0, 0.2f, 1);

        camera.update();
        shootemupgame.batch.setProjectionMatrix(camera.combined);

        shootemupgame.batch.begin();

        stage.act();
        stage.draw();

        /*
        shootemupgame.font.draw(shootemupgame.batch,"SHOOTER III", 400,700);
        shootemupgame.font.getData().setScale(2,2);

         */

        shootemupgame.batch.end();


        if(Gdx.input.justTouched()){
            shootemupgame.setScreen(new Jeu(shootemupgame));
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        myskin.dispose();
        stage.dispose();

    }
}
