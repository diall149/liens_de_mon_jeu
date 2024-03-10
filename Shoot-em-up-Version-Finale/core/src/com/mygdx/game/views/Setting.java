package com.mygdx.game.views;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.Levels.Levels;

public class Setting {

    private float musicVolume;
    private float soundVolume;
    private boolean musicEnabled;
    private boolean soundEnabled;

    //instanciation des music
    private Music backgroundMusic= Gdx.audio.newMusic(Gdx.files.internal("assets/music/start.mp3"));
    private Music level1Music= Gdx.audio.newMusic(Gdx.files.internal("assets/music/level1.mp3"));
    private Music level2Music= Gdx.audio.newMusic(Gdx.files.internal("assets/music/level2.wav"));
    private Music level3Music= Gdx.audio.newMusic(Gdx.files.internal("assets/music/level3.wav"));
    private Music level4Music= Gdx.audio.newMusic(Gdx.files.internal("assets/music/level4.mp3"));
    private Music level5Music= Gdx.audio.newMusic(Gdx.files.internal("assets/music/level5.mp3"));
    private Music bossMusic= Gdx.audio.newMusic(Gdx.files.internal("assets/music/boss.wav"));

    private Sound buttonClickSound;
    private static final String pref_name="bdiall";

    protected Sound victory = Gdx.audio.newSound(Gdx.files.internal("music/victory.mp3"));
    protected Sound defeat = Gdx.audio.newSound(Gdx.files.internal("music/defeat.mp3"));

    protected Sound mortEnemi = Gdx.audio.newSound(Gdx.files.internal("music/boom.mp3"));
    protected Sound tirHero  = Gdx.audio.newSound(Gdx.files.internal("music/ship1shoot.mp3"));

    public Setting(){
        buttonClickSound=Gdx.audio.newSound(Gdx.files.internal("assets/music/click.mp3"));

        musicVolume=0.5f;
        soundVolume=0.25f;

        musicEnabled=true;
        soundEnabled=true;
    }
    protected Preferences get_pref(){
        return Gdx.app.getPreferences(pref_name);
    }

    //joue la music en fonction du volume et de musicEnabled
    public void playBackgroundMusic(){
        if (musicEnabled){
            backgroundMusic.setLooping(true);
            backgroundMusic.setVolume(musicVolume);
            backgroundMusic.play();
        }
    }

    //joue la music du niveau associé
    public void playLevelMusic(String level){
        Music music;
        if (musicEnabled){
            if(level == "Level1"){
                music = level1Music;
            }
            else if(level == "Level2"){
                music = level2Music;
            }
            else if(level == "Level3"){
                music = level3Music;
            }
            else if(level == "Level4"){
                music = level4Music;
            }else {
                music = level5Music;
            }
            music.setLooping(true);
            music.setVolume(musicVolume);
            music.play();
        }
    }

    //joue la music du boss
    public void playBossMusic(){
        if (musicEnabled){
            bossMusic.setLooping(true);
            bossMusic.setVolume(musicVolume);
            bossMusic.play();
        }
    }

    //stop les musics
    public void stopMusic(){
        backgroundMusic.stop();
        level1Music.stop();
        level2Music.stop();
        level3Music.stop();
        level4Music.stop();
        level5Music.stop();
        bossMusic.stop();
    }
    public void playButtonClickSound(){
        if(soundEnabled){
            buttonClickSound.play(soundVolume);
        }
    }
    public void stopbuttonClick(){
        buttonClickSound.stop();
    }
    //les methodes d'acces et de modification des reglages
    public float getMusicVolume(){
        return musicVolume;
    }

    public void setMusicVolume(float volume){
        this.musicVolume=volume;
        if (musicEnabled){
            backgroundMusic.setVolume(musicVolume);
            level1Music.setVolume(musicVolume);
            level2Music.setVolume(musicVolume);
            level3Music.setVolume(musicVolume);
            level4Music.setVolume(musicVolume);
            level5Music.setVolume(musicVolume);
            get_pref().flush();
        }
    }

    public void playExplosionSound(){
        if (soundEnabled) {
            mortEnemi.play(soundVolume);
        }
    }

    public void playShootSound() {
        if (soundEnabled) {
            tirHero.play(soundVolume);
        }
    }

    public void playDefeatSound() {
        if (soundEnabled) {
            defeat.play(soundVolume);
        }
    }

    public void playVictorySound() {
        if (soundEnabled) {
            victory.play(soundVolume);
        }
    }

    public float getSoundVolume(){
        return soundVolume;
    }

    public void  setSoundVolume(float volume){
        this.soundVolume=volume;
        if(soundEnabled){
            buttonClickSound.setVolume(0,soundVolume);
            get_pref().flush();
        }
    }

    public boolean isMusicEnabled(){
        return musicEnabled;
    }
    public void setMusicEnabled(boolean enabled){
        musicEnabled=enabled;
        if(enabled){
            playBackgroundMusic();

        }else {
            stopMusic();
        }
        get_pref().flush();
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }
    public void setSoundEnabled(boolean enabled){
        this.soundEnabled=enabled;
        if(enabled){
            playButtonClickSound();
        }else {
            stopbuttonClick();
        }
        get_pref().flush();
    }

}
