package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.menu.GameConstants;
import com.mygdx.game.menu.GameScreen;
import com.mygdx.game.menu.ShootEmUpGame;
import com.mygdx.game.menu.Start;


public class SettingScreen implements Screen {
    private Skin myskin;

    private final ShootEmUpGame shootEmUpGame;
    private OrthographicCamera camera;
    private Stage stage;

    private Slider musicVolumesSlider;
    private Slider SoundVolumesSlider;
    private CheckBox musicCheckbox;
    private CheckBox soundCheckbox;
    private Label titleLabel;
    private Button backButton;


    //les labels
    private Label musicVolumeLabel;
    private Label soundVolumeLabel;
    private Setting setting;

    private Texture backgroundTexture;




    public SettingScreen(final ShootEmUpGame shootEmUpGame){
        this.shootEmUpGame = shootEmUpGame;

        //creation l'objet audio
        setting=new Setting();



        //chargement du skin
        myskin=new Skin(Gdx.files.internal(GameConstants.skin));

        //ecran
        stage=new Stage();
        camera=new OrthographicCamera();
        camera.setToOrtho(false,GameScreen.screenWidth,GameScreen.screenHeight);






        //Texture
        backgroundTexture=new Texture(Gdx.files.internal("assets/fond/setting_fond.png"));
        Image backgroundImage=new Image(backgroundTexture);
        backgroundImage.setPosition(0,0);
        backgroundImage.setSize(GameScreen.screenWidth,GameScreen.screenHeight);

        //le titre
        titleLabel=new Label("PREFERENCES",myskin,"big");
        titleLabel.setSize(GameScreen.co_width*2,GameScreen.row_height*2);
        titleLabel.setPosition(GameScreen.centerX-titleLabel.getWidth()/2,GameScreen.centerY+GameScreen.row_height);
        titleLabel.setAlignment(Align.center);

        //les labels
        musicVolumeLabel=new Label("Music Volume", myskin);
        musicVolumeLabel.setSize(GameScreen.co_width*2,GameScreen.row_height);
        musicVolumeLabel.setPosition(GameScreen.centerX - musicVolumeLabel.getWidth(), titleLabel.getY()-titleLabel.getHeight()/4);

        //le Slider
        musicVolumesSlider=new Slider(0f,1f,0.1f,false,myskin);
        musicVolumesSlider.setSize(GameScreen.co_width*2,GameScreen.row_height*2);
        musicVolumesSlider.setPosition(GameScreen.centerX-musicVolumeLabel.getWidth()/2,titleLabel.getY()-titleLabel.getHeight()/2);
        musicVolumesSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float volume=musicVolumesSlider.getValue();
                setting.setMusicVolume(volume);
            }
        });

        //les CheckBox
        musicCheckbox=new CheckBox("Music",myskin);
        musicCheckbox.setPosition(musicVolumeLabel.getX(), musicVolumeLabel.getY() - musicCheckbox.getHeight());

        musicCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean enable=musicCheckbox.isChecked();
                setting.setMusicEnabled(enable);
            }
        });

        //label de mon checkbox
        soundVolumeLabel=new Label("Sound Volume ",myskin);
        soundVolumeLabel.setSize(GameScreen.co_width*2,GameScreen.row_height);
        soundVolumeLabel.setPosition(GameScreen.centerX - soundVolumeLabel.getWidth(), musicVolumeLabel.getY() - musicVolumeLabel.getHeight());

        //slider sound
        SoundVolumesSlider = new Slider(0f, 1f, 0.1f, false, myskin);
        SoundVolumesSlider.setSize(GameScreen.co_width * 2, GameScreen.row_height);
        SoundVolumesSlider.setPosition(soundVolumeLabel.getX() + soundVolumeLabel.getWidth()/2, soundVolumeLabel.getY());

        SoundVolumesSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float volume=SoundVolumesSlider.getValue();
                setting.setSoundVolume(volume);
            }
        });



        //CheckBox
        soundCheckbox=new CheckBox("Sound Effects",myskin);
        soundCheckbox.setPosition(soundVolumeLabel.getX(), soundVolumeLabel.getY() - soundCheckbox.getHeight());

        soundCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean enabled=soundCheckbox.isChecked();
                setting.setSoundEnabled(enabled);
            }
        });



        //bouton back
        backButton = new TextButton("Back", myskin, "default");
        backButton.setSize(GameScreen.co_width * 2, GameScreen.row_height);
        backButton.setPosition(GameScreen.centerX - backButton.getWidth() / 2, soundCheckbox.getY() - 2 * soundCheckbox.getHeight() - backButton.getHeight());

        //configurer le bouton back
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setting.playButtonClickSound();
                shootEmUpGame.setScreen(new Start(shootEmUpGame, setting));
            }
        });


        //ajout des composants
        stage.addActor(backgroundImage);
        stage.addActor(musicVolumeLabel);
        stage.addActor(soundVolumeLabel);
        stage.addActor(titleLabel);
        stage.addActor(musicVolumesSlider);
        stage.addActor(SoundVolumesSlider);
        stage.addActor(musicCheckbox);
        stage.addActor(soundCheckbox);
        stage.addActor(backButton);




        Gdx.input.setInputProcessor(stage);






    }

    @Override
    public void show() {

        setting=shootEmUpGame.getSetting();
        musicVolumesSlider.setValue(setting.getMusicVolume());
        SoundVolumesSlider.setValue(setting.getSoundVolume());
        musicCheckbox.setChecked(setting.isMusicEnabled());
        soundCheckbox.setChecked(setting.isSoundEnabled());

    }

    @Override
    public void render(float delta) {
        // Effacer l'écran avec une couleur de fond
        ScreenUtils.clear(Color.WHITE);

        camera.update();
        shootEmUpGame.batch.setProjectionMatrix(camera.combined);
        shootEmUpGame.batch.begin();

        // Mettre à jour la scène
        stage.act(delta);
        // Dessiner la scène
        stage.draw();
        shootEmUpGame.batch.end();

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
        setting.stopMusic();
        setting.stopbuttonClick();
        backgroundTexture.dispose();
    }
}