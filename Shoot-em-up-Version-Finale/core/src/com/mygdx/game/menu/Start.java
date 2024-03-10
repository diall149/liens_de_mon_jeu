package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Levels.*;
import com.mygdx.game.views.Setting;
import com.mygdx.game.views.SettingScreen;

public class Start implements Screen {

    private Skin myskin;

    private Button helpButton;
    private Button helpButtonCreator;
    private Texture cadre;
    private Image cadreImage;
    private Texture filtre;
    private Image filtreImage;
    private Image filtreImageC;
    private Image cadreImageC;

    final ShootEmUpGame shootemupgame;

    //creation de la camera
    OrthographicCamera camera;
    private Stage stage;

    private SelectBox<String> levelSelectBox;

    private Label nameLabel;
    private Button quitte;
    private  Button settingBtn;
    private Button startbtn;
    private Setting setting;

    private Texture backgroundTexture;

    private Image backgroundImage;

    private Label title;
    private Label helpText;
    private Label CreatorText;
    private  boolean helpButtonIsSet = false;
    private  boolean helpButtonCreatorIsSet = false;


    //méthode pour set la camera
    public Start(final ShootEmUpGame shootemupgame, final Setting setting){

        setting.playBackgroundMusic();
        //chargement du Skin
        myskin=new Skin(Gdx.files.internal(GameConstants.skin));
        stage=new Stage();

        nameLabel=new Label("",myskin);
        nameLabel.setAlignment(Align.center);




        //Texture pour le background
        backgroundTexture =new Texture(Gdx.files.internal("assets/fond/fond_start.png"));
        backgroundImage=new Image(backgroundTexture);
        backgroundImage.setPosition(0,0);
        backgroundImage.setSize(GameScreen.screenWidth,GameScreen.screenHeight);
        cadre = new Texture(Gdx.files.internal("assets/cadre.png"));
        cadreImage=new Image(cadre);
        cadreImage.setPosition(GameScreen.screenGameWidthStart + 185,GameScreen.screenHeight - cadre.getHeight() - 30);
        cadreImageC=new Image(cadre);
        cadreImageC.setPosition(GameScreen.screenGameWidthStart + 185,20);

        filtre = new Texture(Gdx.files.internal("assets/filtre.png"));
        filtreImage=new Image(filtre);
        filtreImage.setPosition(GameScreen.screenGameWidthStart + 185 + 37,GameScreen.screenHeight - cadre.getHeight() - 5);
        filtreImageC=new Image(filtre);
        filtreImageC.setPosition(GameScreen.screenGameWidthStart + 185 + 37,45);


        helpText=new Label( "                 Press Escape to quit in Game \n " +
                                "                   Tentez de terminer tous les niveaux en faisant \n" +
                                "                 le plus de score possible \n" +
                                "                      Evitez de casser votre combo en vous faisant toucher \n"+
                                "                                               Bonne Chance   !!!                                " ,myskin);
        helpText.setColor(Color.LIGHT_GRAY);
        helpText.setPosition(GameScreen.screenGameWidthStart + 85 ,GameScreen.screenHeight - cadre.getHeight() + filtre.getHeight() - 165);
        helpText.setAlignment(Align.center);

        CreatorText=new Label( "                Assets used created by : \n " +
                "               Start : Trevor Lentz\n" +
                "               Level 4: Pierre Bondoerffer \n" ,myskin);
        CreatorText.setColor(Color.LIGHT_GRAY);
        CreatorText.setPosition(GameScreen.screenGameWidthStart + 250 ,155);
        CreatorText.setAlignment(Align.center);

        //Music de font



        //position du camera
        this.shootemupgame = shootemupgame;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 1200);



        //Titre
        title=new Label("SHOOTER III",myskin,"big");
        title.setSize(GameScreen.co_width*2,GameScreen.row_height*2);
        title.setPosition(GameScreen.centerX - title.getWidth() / 2, GameScreen.centerY- 2*GameScreen.row_height);
        title.setAlignment(Align.center);

        //Mes boutons
        startbtn=new TextButton("START",myskin,"default");
        startbtn.setSize(GameScreen.co_width*2,GameScreen.row_height);
        startbtn.setPosition(GameScreen.centerX-4*startbtn.getWidth()/2,GameScreen.centerY);

        String[] levelNames={"LEVEL 1","LEVEL 2", "LEVEL 3", "LEVEL 4", "LEVEL 5"};
        levelSelectBox=new SelectBox<>(myskin,"default");
        levelSelectBox.setItems(levelNames);
        levelSelectBox.setSize(GameScreen.co_width*2,GameScreen.row_height);
        levelSelectBox.setPosition(GameScreen.centerX+2*levelSelectBox.getWidth()/2,GameScreen.centerY);
        levelSelectBox.setAlignment(Align.center);


        settingBtn=new TextButton("SETTING",myskin,"default");
        settingBtn.setSize(GameScreen.co_width*2,GameScreen.row_height);
        settingBtn.setPosition(GameScreen.centerX-4*settingBtn.getWidth()/2,levelSelectBox.getY()-GameScreen.row_height-100);

        quitte=new TextButton("QUIT",myskin,"default");
        quitte.setSize(GameScreen.co_width*2,GameScreen.row_height);
        quitte.setPosition(GameScreen.centerX+2*quitte.getWidth()/2,settingBtn.getY()-GameScreen.row_height+100);


        //buton help
        helpButton=new TextButton("?",myskin,"default");
        helpButton.setSize(70,70);
        helpButton.setPosition(GameScreen.screenWidth-helpButton.getWidth(),GameScreen.screenHeight-helpButton.getHeight());

        helpButtonCreator=new TextButton("!",myskin,"default");
        helpButtonCreator.setSize(70,70);
        helpButtonCreator.setPosition(10,10);

        // affiche le cadre de help si on appuie sur le bouton et l'enlève si on réappuie dessus
        helpButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(!helpButtonCreatorIsSet){
                    if(!helpButtonIsSet){
                        helpButtonIsSet = true;
                        stage.addActor(filtreImage);
                        stage.addActor(cadreImage);
                        stage.addActor(helpText);
                    }
                    else{
                        helpButtonIsSet = false;
                        shootemupgame.setScreen(new Start(shootemupgame, setting));
                        dispose();
                    }
                }
            }
        });

        // affiche le cadre de help creator si on appuie sur le bouton et l'enlève si on réappuie dessus
        helpButtonCreator.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(!helpButtonIsSet){
                    if(!helpButtonCreatorIsSet){
                        helpButtonCreatorIsSet = true;
                        stage.addActor(filtreImageC);
                        stage.addActor(cadreImageC);
                        stage.addActor(CreatorText);
                    }
                    else{
                        helpButtonCreatorIsSet = false;
                        shootemupgame.setScreen(new Start(shootemupgame, setting));
                        dispose();
                    }
                }
            }
        });


        //ajout de mes Objets
        stage.addActor(backgroundImage);
        stage.addActor(title);
        stage.addActor(startbtn);
        stage.addActor(levelSelectBox);
        stage.addActor(settingBtn);
        stage.addActor(helpButton);
        stage.addActor(helpButtonCreator);
        stage.addActor(quitte);


        Gdx.input.setInputProcessor(stage);

    }
    //mes deux methodes de selections de level
    private void startSelectedLevel(String selectedLevel){
        switch (selectedLevel){
            case "LEVEL 1":
                shootemupgame.setScreen(new Level1(shootemupgame,setting));
                break;
            case "LEVEL 2":
                shootemupgame.setScreen(new Level2(shootemupgame,setting));
                break;
            case "LEVEL 3":
                shootemupgame.setScreen(new Level3(shootemupgame,setting));
                break;
            case "LEVEL 4":
                shootemupgame.setScreen(new Level4(shootemupgame,setting));
                break;
            case "LEVEL 5":
                shootemupgame.setScreen(new Level5(shootemupgame,setting));
                break;
            default:
                startDefaultLevel();
                break;
        }
    }
    private void startDefaultLevel(){
        shootemupgame.setScreen(new Level1(shootemupgame,setting));
    }

    //méthode pour render le start screen
    public void render(float delta){
        ScreenUtils.clear(Color.WHITE);

        shootemupgame.batch.begin();
        stage.draw();
        stage.act();
        shootemupgame.batch.end();


    }

    @Override
    public void show() {

        quitte.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                setting.playButtonClickSound();
                Gdx.app.exit();

            }
        });

        //bouton setting
        settingBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                shootemupgame.setScreen(new SettingScreen(shootemupgame));
                setting.playButtonClickSound();
                dispose();
            }
        });


        startbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String selectedLevel=levelSelectBox.getSelected();
                if (selectedLevel!=null){
                    setting.playButtonClickSound();
                    startSelectedLevel(selectedLevel);


                }else {
                    startDefaultLevel();

                }
                dispose();
            }
        });
        setting=shootemupgame.getSetting();
        camera.update();
        shootemupgame.batch.setProjectionMatrix(camera.combined);


    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);


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
        levelSelectBox.clear();
        backgroundTexture.dispose();
        title.clear();
        backgroundImage.clear();
        quitte.clear();
    }
}
