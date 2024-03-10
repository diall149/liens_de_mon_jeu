package com.mygdx.game.Levels;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.BonusMalus.BonusMalus;
import com.mygdx.game.Entite.Enemi.*;
import com.mygdx.game.Entite.Enemi.Objets.Asteroid;
import com.mygdx.game.Entite.Enemi.Objets.GoldenAsteroid;
import com.mygdx.game.Entite.Enemi.Objets.MissileGuide;
import com.mygdx.game.Entite.Entite;
import com.mygdx.game.Weapon.*;
import com.mygdx.game.Entite.PJ.Ship1;
import com.mygdx.game.Weapon.Boss.BossBouncingBullet;
import com.mygdx.game.Weapon.Boss.BossExploBullet;
import com.mygdx.game.Weapon.Boss.BossMultiBullet;
import com.mygdx.game.menu.*;
import com.mygdx.game.views.Setting;

import java.util.Date;
import java.util.Iterator;
import java.util.Random;

public abstract class Levels implements Screen{


    // définit un entier correspondant aux keys enter et escape
    private int enter = Input.Keys.ENTER;
    private int escape = Input.Keys.ESCAPE;
    private float musicVolume;

    //utilisé pour changer la couleur du texte en fonction du rang
    private Color colorResult;

    protected final ShootEmUpGame shootEmUpGame;
    protected final OrthographicCamera camera;

    //timer affiché dans le hud
    private int hudTime = 0;

    private final TextureAtlas textureAtlas;
    private final Animation<TextureRegion> animation;

    //timer prédéfinit à la date actuelle en seconde
    protected int time = (int)new Date().getTime()/1000;
    //timer pour générer les drops sur la ma^p lorsque le boss à spawn
    protected int timerDropBoss ;

    //vaisseau du joueur
    protected final Ship1 ship1;

    //définit si la dernière partie à déjà été généré ou non en fonction du nombre d'enemies tué(par le joueur ou non)
    private int lastpart = 0;
    private int combo = 0;
    private int score = 0;

    //timer du vaisseau pour gérer le temps des bonus
    protected int shipTimer = (int)new Date().getTime()/1000;

    //boolean des parties qui permet génère la partie x si elle n'a pas été généré
    protected boolean[] levelParts ;

    //nombre d'enemies tués
    protected int enemiesKilled = 0;

    //nombre d'enemies spawned
    protected int enemiesSpawned = 0;

    //nombre de boss tués
    protected int bosskilled = 0;

    boolean bossSpawn = false;
    boolean bossDispawned = false;
    boolean ShipIsInvisible = false;
    boolean gameStop = false;

    boolean lose = false;
    boolean win = false;

    //textures
    protected final Texture backgroundTexture;
    private Texture backgroundGameG;
    private Texture backgroundGameD;
    private Texture separ;
    protected int backgroundOffSet;
    private Texture heart;
    private Texture cadre;
    private Texture filtre;
    private Texture bosspng;

    protected Setting setting;

    //tableaux des entitees, balles
    protected final Array<Entite> ships;
    protected final Array<Weapon> ammos;
    protected final Array<Entite> distroyed;
    protected final Array<Boss> distroyedBoss;
    protected final Array<Entite> objects;
    protected final Array<ExplosifBullet> distroyedBullets;
    protected final Array<ExplosifBullet> distroyedBulletsAnimation;
    protected final Array<BossExploBullet> distroyedBossBullets;
    protected final Array<BossExploBullet> distroyedBossBulletsAnimation;
    protected final Array<Boss> allBoss;

    private boolean musicPlayed = false;

    //tableau des drops
    protected final Array<BonusMalus> drops;

    protected Random rand = new Random();

    //partie actuelle du niveau
    protected int current_part = 0;

    public Levels(ShootEmUpGame shootEmUpGame, Setting setting){

        //setting qui gère le volume des sons
        this.setting = setting;
        setting.stopMusic();

         musicVolume = setting.getMusicVolume();
        initLevelParts();
        this.shootEmUpGame = shootEmUpGame;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        shootEmUpGame.font.getData().setScale(1.5f, 1.5f);

        ship1 = new Ship1();
        ships = new Array<>();
        ammos = new Array<>();
        distroyed = new Array<>();
        objects = new Array<>();
        drops = new Array<>();
        distroyedBullets = new Array<>();
        distroyedBulletsAnimation = new Array<>();
        distroyedBossBullets = new Array<>();
        distroyedBossBulletsAnimation = new Array<>();
        allBoss = new Array<>();
        distroyedBoss = new Array<>();

        backgroundTexture = new Texture(Gdx.files.internal("assets/fond/background_game.png"));
        textureAtlas = new TextureAtlas(Gdx.files.internal("animation/kisspng-sprite-explosion_pack/kisspng-sprite-explosion_pack.atlas"));
        animation = new Animation<TextureRegion>(1f/30f, textureAtlas.getRegions());
        backgroundGameG = new Texture(Gdx.files.internal("assets/fond/fond_gameG.png"));
        backgroundGameD = new Texture(Gdx.files.internal("assets/fond/fond_gameD.png"));
        backgroundOffSet = 0;
        heart = new Texture(Gdx.files.internal("assets/icon/heart.png"));
        cadre = new Texture(Gdx.files.internal("assets/cadre.png"));
        filtre = new Texture(Gdx.files.internal("assets/filtre.png"));
        separ = new Texture(Gdx.files.internal("assets/separation.png"));
        bosspng = new Texture(Gdx.files.internal("assets/entite/boss.png"));

    }

    //renvoie le niveau actuel
    abstract String getNumLevel();

    //renvoie le nombre de parties du niveau
    abstract int getNbParts();


    abstract void generationEntite(int nbEntitees, int time);

    //renvoie le nombre total d'enemies du niveau
    abstract int getEnemiesAmount();

    public void initLevelParts(){
        for(int i =0;i<getNbParts();i++){
            this.levelParts[i] = false ;
        }
    }

    // affiche les données tel que la vie du joueur, du boss, le temps , le score et le combo actuel
    public void hud(){
        if((((int)new Date().getTime()/1000 ) - time) < 2){
            shootEmUpGame.font.setColor(Color.GREEN);
            shootEmUpGame.font.getData().setScale(7f, 7f);
            shootEmUpGame.font.draw(shootEmUpGame.batch, getNumLevel(), GameScreen.screenGameWidthStart + (int)(GameScreen.GameScreenWidth/3), (int)(Gdx.graphics.getHeight()/2));
            shootEmUpGame.font.getData().setScale(1.5f, 1.5f);
            shootEmUpGame.font.setColor(Color.WHITE);
        }
        shootEmUpGame.batch.draw(ship1.getSprite(), GameScreen.screenGameWidthStart - 150- 50, Gdx.graphics.getHeight()-75, 50, 50);
        shootEmUpGame.batch.draw(heart, GameScreen.screenGameWidthStart - 45- 50, Gdx.graphics.getHeight() - 65);
        shootEmUpGame.font.draw(shootEmUpGame.batch, ship1.getLife() + "", GameScreen.screenGameWidthStart - 85- 50, Gdx.graphics.getHeight() - 40);
        shootEmUpGame.font.setColor(getComboColor(getComboValue(combo)));
        shootEmUpGame.font.getData().setScale(getComboSize(getComboValue(combo)));
        shootEmUpGame.font.draw(shootEmUpGame.batch, "Score : " + score , GameScreen.screenGameWidthEnd + 10 + 40, Gdx.graphics.getHeight() - 90);
        shootEmUpGame.font.draw(shootEmUpGame.batch, "Combo : x"  + getComboValue(combo) , GameScreen.screenGameWidthEnd + 10+ 40, Gdx.graphics.getHeight() - 140);
        shootEmUpGame.font.setColor(Color.WHITE);
        shootEmUpGame.font.getData().setScale(1.5f,1.5f);
        if(!gameStop){
            hudTime = (((int)new Date().getTime()/1000 ) - time);
        }
        shootEmUpGame.font.draw(shootEmUpGame.batch, "Timer : " + hudTime + "s" , GameScreen.screenGameWidthEnd + 10+ 40, Gdx.graphics.getHeight() - 40);
        if(bossSpawn){
            int lifeBoss = 0;
            if(!bossDispawned){
                lifeBoss = allBoss.get(0).getLife();
            }
            shootEmUpGame.batch.draw(heart, GameScreen.screenGameWidthStart - 45 - 50, Gdx.graphics.getHeight() - 165);
            shootEmUpGame.batch.draw(bosspng, GameScreen.screenGameWidthStart - 280- 50, Gdx.graphics.getHeight()-200, 150, 100);
            shootEmUpGame.font.draw(shootEmUpGame.batch, "" + lifeBoss , GameScreen.screenGameWidthStart - 105- 50, Gdx.graphics.getHeight() - 140);
        }
    }

    //génère les balles en fonction du type de balles et du  type d'entitee
    public void spawnBullets(Entite ship){
        if(ship instanceof Ship1){
            if(Gdx.input.justTouched()) {
                ship.shootBullets();
                setting.playShootSound();
            }
        }
        else {
            if(ship instanceof MissileGuideeEnemi){
                if((ship.current_time + 35) == (int)new Date().getTime()/100){
                    int r = rand.nextInt(3);
                    if(r == 0){
                        MissileGuideeEnemi shipMG = (MissileGuideeEnemi)  ship;
                        MissileGuide missile = shipMG.ShootMissile();
                        objects.add(missile);
                    }
                    ship.current_time = (int)new Date().getTime()/100;
                }
            }
            else{
                if((ship.current_time + 2 == (int)new Date().getTime()/100)){
                    int r = rand.nextInt(3);
                    if(r == 0){
                        ship.shootBullets();
                    }
                    ship.current_time = (int)new Date().getTime()/100;
                }
            }
        }
    }

    //update le pattern du vaisseau
    public void ShipPattern(Entite ship){
        if(!(ship instanceof Ship1 )){
            int rtime = 4 + rand.nextInt(65);
            if((ship.pattern_time + rtime) == (int)new Date().getTime()/100){
                int rpattern = 1+ rand.nextInt(5);
                int rsens = rand.nextInt(2);
                if(rsens == 0){
                    rpattern = -rpattern;
                }
                ship.setPattern(rpattern);
                ship.pattern_time = (int)new Date().getTime()/100;
            }
        }
    }

    //update le patern de tir du boss
    public void BossPattern(Boss boss){
            if((boss.getPattern_BossTime() + 5) == (int)new Date().getTime()/1000){
                int r = rand.nextInt(10);
                boss.setPattern_Boss(r);
                boss.setPattern_BossTime((int)new Date().getTime()/1000);
            }
    }

    //definis la trajectoire des balles
    public void drawBullets(Entite ship){
        spawnBullets(ship);
        for(Weapon ammo : ship.getTirs()){
            shootEmUpGame.batch.draw(ammo.getSprite(), ammo.getX(), ammo.getY(), ammo.getWidth(), ammo.getHeight());
            if(ship instanceof Ship1){
                if(ammo instanceof DoubleBouncingBullet){
                    DoubleBouncingBullet bBullet = (DoubleBouncingBullet) ammo;
                    if (ammo.getX() > GameScreen.screenGameWidthEnd - bBullet.getWidth() && bBullet.getBouncingBullet() > 0) bBullet.setBouncingBullet(-1);
                    if (ammo.getX() < GameScreen.screenGameWidthStart + bBullet.getWidth() && bBullet.getBouncingBullet() < 0) bBullet.setBouncingBullet(1);
                    ammo.setY(ammo.getY() + ammo.getSpeed());
                    ammo.setX(ammo.getX() + (bBullet.getBouncingBullet() * ammo.getSpeed()));
                }
                else if(ammo instanceof MultiBullet){
                    MultiBullet mBullet = (MultiBullet) ammo;
                    ammo.setY(ammo.getY() + ammo.getSpeed());
                    ammo.setX(ammo.getX() + ((MultiBullet) ammo).getDirection());
                }
                else{
                    ammo.setY(ammo.getY() + ammo.getSpeed());
                }
            }
            else if(ship instanceof SuperEnemi) {
                BouncingBullet bBullet = (BouncingBullet) ammo;
                if (ammo.getX() > GameScreen.screenGameWidthEnd - bBullet.getWidth() && bBullet.getBouncingBullet() > 0) bBullet.setBouncingBullet(-1);
                if (ammo.getX() < GameScreen.screenGameWidthStart + bBullet.getWidth() && bBullet.getBouncingBullet() < 0) bBullet.setBouncingBullet(1);
                ammo.setY(ammo.getY() - ammo.getSpeed());
                ammo.setX(ammo.getX() + (bBullet.getBouncingBullet() * ammo.getSpeed()));
            }
            else if(ship instanceof MultiBulletEnemi){
                MultiBullet mBullet = (MultiBullet) ammo;
                ammo.setY(ammo.getY() - ammo.getSpeed());
                ammo.setX(ammo.getX() + ((MultiBullet) ammo).getDirection());
            }
            else if(ship instanceof Boss){
                if(ammo instanceof BossBouncingBullet){
                    BossBouncingBullet bBullet = (BossBouncingBullet) ammo;
                    if (ammo.getX() > GameScreen.screenGameWidthEnd - bBullet.getWidth() && bBullet.getBouncingBullet() > 0) bBullet.setBouncingBullet(-2);
                    if (ammo.getX() < GameScreen.screenGameWidthStart + bBullet.getWidth() && bBullet.getBouncingBullet() < 0) bBullet.setBouncingBullet(2);
                    ammo.setY(ammo.getY() - ammo.getSpeed());
                    ammo.setX(ammo.getX() + (bBullet.getBouncingBullet() * ammo.getSpeed()));
                }
                else if(ammo instanceof BossMultiBullet){
                    BossMultiBullet mBullet = (BossMultiBullet) ammo;
                    ammo.setY(ammo.getY() - ammo.getSpeed());
                    ammo.setX(ammo.getX() + mBullet.getDirection());
                }
                else{
                    ammo.setY(ammo.getY() - ammo.getSpeed());
                }
            }
            else{
                ammo.setY(ammo.getY() - ammo.getSpeed());
            }
        }
    }


    protected void dispawnShip(){
        for(Iterator<Entite> iter = ships.iterator(); iter.hasNext();){
            Entite ship = iter.next();
            if(ship.getY() <= GameScreen.maxYShip){
                if(ship instanceof Ship1){
                    for(int i = 0; i< ships.size; i++){
                        Entite otherShip = ships.get(i) ;
                        if(ship != otherShip){
                            if(otherShip.getY() <= GameScreen.maxYShip){
                                collisionEntitee(otherShip, ship, 40);
                            }
                        }
                    }
                }
            }
            if(ship.getLife() <= 0) {
                distroyed.add(ship);
                setting.playExplosionSound();
                if (ship.getClass() != ship1.getClass()) {
                    enemiesKilled += 1;
                    score += ship.getScore() * getComboValue(combo);;
                    int rDrop = rand.nextInt(10);
                    if(rDrop == 0){
                        drops.add(ship.Drop());
                     }
                }
                iter.remove();
            }
            if(ship.getY() < 0) {
                enemiesKilled += 1;
                iter.remove();
            }
        }
    }

    protected void dispawnBoss(){
        for(Iterator<Boss> iter = allBoss.iterator(); iter.hasNext();){
            Boss boss = iter.next();
            if(boss.getLife() <= 0) {
                distroyedBoss.add(boss);
                setting.playExplosionSound();
                bosskilled +=1;
                score += boss.getScore() * getComboValue(combo);
                bossDispawned = true;
                iter.remove();
            }
        }
    }

    //génère l'anmation d'explosion des vaisseaux
    public void shipExplosion(){
        for (Iterator<Entite> iterator = distroyed.iterator(); iterator.hasNext(); ) {
            Entite distroy = iterator.next();
            distroy.setElaspseTime(distroy.getElaspseTime() + Gdx.graphics.getDeltaTime());
            if(!(distroy instanceof Ship1)) shootEmUpGame.batch.draw(animation.getKeyFrame(distroy.getElaspseTime()), distroy.getX() - 80, distroy.getY() - 100, 200, 200);
            else shootEmUpGame.batch.draw(animation.getKeyFrame(distroy.getElaspseTime()), distroy.getX() - 80, distroy.getY() - 100, 200, 200);
            if (animation.isAnimationFinished(distroy.getElaspseTime())) {
                iterator.remove();
            }
        }
    }

    //génère l'anmation d'explosion des vaisseaux
    public void BossExplosion(){
        for (Iterator<Boss> iterator = distroyedBoss.iterator(); iterator.hasNext(); ) {
            Entite distroy = iterator.next();
            distroy.setElaspseTime(distroy.getElaspseTime() + Gdx.graphics.getDeltaTime());
            shootEmUpGame.batch.draw(animation.getKeyFrame(distroy.getElaspseTime()), distroy.getX() -175   , distroy.getY() - 300 , 1000, 1000);
            if (animation.isAnimationFinished(distroy.getElaspseTime())) {
                iterator.remove();
            }
        }
    }

    //génère l'anmation d'explosion des balles et vérifie les collisions avec les explosions des balles
    public void BulletExplosion(){
        for(Iterator<ExplosifBullet> iterator = distroyedBullets.iterator(); iterator.hasNext(); ){
            ExplosifBullet distroy = iterator.next();
            for(int i = 0; i< ships.size;i++){
                collisionExploBullet(distroy,ships.get(i));
            }
            for(int i = 0; i< objects.size;i++){
                collisionExploBullet(distroy,objects.get(i));
            }
            for(int i = 0; i< allBoss.size;i++){
                collisionExploBullet(distroy,allBoss.get(i));
            }
            iterator.remove();
        }
        for (Iterator<ExplosifBullet> iterator = distroyedBulletsAnimation.iterator(); iterator.hasNext(); ) {
            ExplosifBullet distroy = iterator.next();
            distroy.setElaspseTime(distroy.getElaspseTime() + Gdx.graphics.getDeltaTime());
            shootEmUpGame.batch.draw(animation.getKeyFrame(distroy.getElaspseTime()), distroy.getX()- (distroy.getHitboxExplo().width/2) , distroy.getY() -(distroy.getHitboxExplo().height/2), distroy.getHitboxExplo().width, distroy.getHitboxExplo().height);
            if (animation.isAnimationFinished(distroy.getElaspseTime())) {
                iterator.remove();
            }
        }

    }

    //génère l'anmation d'explosion des balles et vérifie les collisions avec les explosions des balles
    public void BossBulletExplosion(){
        for(Iterator<BossExploBullet> iterator = distroyedBossBullets.iterator(); iterator.hasNext(); ){
            BossExploBullet distroy = iterator.next();
                collisionBossExploBullet(distroy,ship1);
            iterator.remove();
        }
        for (Iterator<BossExploBullet> iterator = distroyedBossBulletsAnimation.iterator(); iterator.hasNext(); ) {
            BossExploBullet distroy = iterator.next();
            distroy.setElaspseTime(distroy.getElaspseTime() + Gdx.graphics.getDeltaTime());
            shootEmUpGame.batch.draw(animation.getKeyFrame(distroy.getElaspseTime()), distroy.getX()-(distroy.getHitboxExplo().width/2) , distroy.getY() -(distroy.getHitboxExplo().height/2), distroy.getHitboxExplo().width, distroy.getHitboxExplo().height);
            if (animation.isAnimationFinished(distroy.getElaspseTime())) {
                iterator.remove();
            }
        }

    }

    protected void dispawnObjects(){
        for(Iterator<Entite> iter = ships.iterator(); iter.hasNext();){
            Entite ship = iter.next();
            if(ship.getY() <= GameScreen.maxYShip){
                if(ship instanceof Ship1){
                    for(int i = 0; i< objects.size; i++){
                        Entite otherObject = objects.get(i) ;
                            if(otherObject.getY() <= GameScreen.maxYShip){
                                if(otherObject instanceof Asteroid){
                                    collisionEntitee(otherObject, ship, ((Asteroid) otherObject).getDamage());
                                }
                                else if (otherObject instanceof MissileGuide){
                                    collisionEntitee(otherObject, ship, ((MissileGuide) otherObject).getDamage());
                                }
                                else{
                                    collisionEntitee(otherObject, ship, 40);
                                }
                            }
                    }
                }
            }
        }
        for(Iterator<Entite> iterObject = objects.iterator();iterObject.hasNext();){
            Entite object = iterObject.next() ;
            if(object.getLife() <= 0){
                distroyed.add(object);
                if(!(object instanceof MissileGuide)){
                    enemiesKilled +=1;
                    if(object instanceof GoldenAsteroid){
                        drops.add(object.Drop());
                    }
                    else{
                        int rDrop = rand.nextInt(10);
                        if(rDrop == 0){
                            drops.add(object.Drop());
                         }
                    }
                }
                setting.playExplosionSound();
                iterObject.remove();
            }
            if(object.getY() < 0) {
                if(!(object instanceof MissileGuide)){
                    enemiesKilled += 1;
                }
                iterObject.remove();
            }
        }
    }

    //verifie si il y a une collision entre les balles et les vaisseaux
    protected boolean collisionWeapon(Weapon ammo, Entite aggressor, Entite touched) {
        if (Intersector.overlaps(ammo.getHitbox(), touched.getHitbox())) {
            if (!(aggressor instanceof Ship1) && !(touched instanceof Ship1)){
                return false;
            }
            if((aggressor instanceof Ship1) && (touched instanceof Ship1)){
                return false;
            }
            else{
                if(touched instanceof Ship1){
                    if(shipTimer + 2  <= (int)new Date().getTime()/1000){
                        ShipIsInvisible = true;
                        combo = 0;
                        touched.setLife((int)(touched.getLife() - ammo.getDmg()));
                        shipTimer = (int)new Date().getTime()/1000;
                        if (touched.getLife() <= 0) touched.setLife(0);
                    }
                }
                else{
                    touched.setLife((int)(touched.getLife() - ammo.getDmg()));
                    if (touched.getLife() <= 0){
                        if(aggressor instanceof Ship1) {
                            combo += 1;
                        }
                        touched.setLife(0);
                    }
                }
                return true;
            }
        }
        return false;
    }


    //verifie si il y a une collision entre les explosions des balles et les vaisseaux
    protected boolean collisionExploBullet(ExplosifBullet ammo,Entite touched) {
        if (Intersector.overlaps(ammo.getHitboxExplo(), touched.getHitbox())) {
            if(((ammo.isEnemiBullet()) && !(touched instanceof Ship1)) || (!(ammo.isEnemiBullet()) && (touched instanceof Ship1))){
                return false;
            }
            else{
                if(touched instanceof Ship1){
                    if(shipTimer + 2  <= (int)new Date().getTime()/1000){
                        ShipIsInvisible = true;
                        combo = 0;
                        touched.setLife((int)(touched.getLife() - 50 ));
                        shipTimer = (int)new Date().getTime()/1000;
                        if (touched.getLife() <= 0) touched.setLife(0);
                    }
                }
                else{
                    touched.setLife((int)(touched.getLife() - 50 ));
                    if (touched.getLife() <= 0) touched.setLife(0);
                }
                return true;
            }
        }
        return false;
    }

    //verifie si il y a une collision entre les explosions des balles du boss et le joueur
    protected boolean collisionBossExploBullet(BossExploBullet ammo,Entite touched) {
        if (Intersector.overlaps(ammo.getHitboxExplo(), touched.getHitbox())) {
            if(shipTimer + 2  <= (int)new Date().getTime()/1000){
                ShipIsInvisible = true;
                combo = 0;
                touched.setLife((int)(touched.getLife() - 80 ));
                if (touched.getLife() <= 0) touched.setLife(0);
                shipTimer = (int)new Date().getTime()/1000;
            }
                return true;
            }
        return false;
    }

    //verifie si il y a une collision entre les vaisseaux
    protected boolean collisionEntitee(Entite aggressor, Entite touched, int Damage){
        if (Intersector.overlaps(aggressor.getHitbox(), touched.getHitbox())) {
            if (!(aggressor instanceof Ship1) && !(touched instanceof Ship1)){
                return false;
            }
            else{
                if(shipTimer + 2  <= (int)new Date().getTime()/1000){
                    ShipIsInvisible = true;
                    combo = 0;
                    shipTimer = (int)new Date().getTime()/1000;
                    touched.setLife(touched.getLife() - Damage);
                    aggressor.setLife(0);
                    if (touched.getLife() <= 0) touched.setLife(0);
                }
                return true;
            }
        }
        return false;
    }

    //dispawn les balles si elle sont en dehors de l'écran de jeu et si elles ont touchés une entitée enemie
    public void dispawnBullets(Entite ship) {
        for(int i = 0; i < ships.size; i++){
            for (Iterator<Weapon> iter = ship.getTirs().iterator(); iter.hasNext(); ) {
                Weapon ammo = iter.next();
                ammo.hitboxUpdate();
                if(ship instanceof Boss){
                    Boss boss = (Boss) ship;
                    if(ammo instanceof BossExploBullet){
                        BossExploBullet  exploBullet = (BossExploBullet) ammo;
                        exploBullet.hitboxExploUpdate();
                        if(ammo.getY() > Gdx.graphics.getHeight() || ammo.getY() + ammo.getHeight()  < exploBullet.getRandExplosion()){
                            distroyedBossBullets.add(exploBullet);
                            distroyedBossBulletsAnimation.add(exploBullet);
                            iter.remove();
                        }
                        else if(ammo.getX() > GameScreen.screenGameWidthEnd || ammo.getX() + ammo.getWidth() < GameScreen.screenGameWidthStart){
                            iter.remove();
                        }
                        else if(collisionWeapon(exploBullet, boss, ships.get(i))) {
                            distroyedBossBullets.add(exploBullet);
                            distroyedBossBulletsAnimation.add(exploBullet);
                            ammos.add(ammo);
                            iter.remove();

                        }
                    }
                    else{
                        if(ammo.getY() > Gdx.graphics.getHeight() || ammo.getY() + ammo.getHeight() < 0) iter.remove();
                        else if(ammo.getX() > GameScreen.screenGameWidthEnd || ammo.getX() + ammo.getWidth() < GameScreen.screenGameWidthStart) iter.remove();
                        else if(collisionWeapon(ammo, boss, ships.get(i))) {
                            ammos.add(ammo);
                            iter.remove();

                        }
                    }
                }
                else if(ammo instanceof ExplosifBullet){
                    ExplosifBullet exploBullet = (ExplosifBullet) ammo;
                    exploBullet.hitboxExploUpdate();
                    if(!(ship instanceof Ship1)){
                        if(ammo.getY() > Gdx.graphics.getHeight() || ammo.getY() + ammo.getHeight()  < exploBullet.getRandExplosion()){
                            exploBullet.setEnemiBullet(true);
                            distroyedBullets.add(exploBullet);
                            distroyedBulletsAnimation.add(exploBullet);
                            iter.remove();
                        }
                        else if(ammo.getX() > GameScreen.screenGameWidthEnd || ammo.getX() + ammo.getWidth() < GameScreen.screenGameWidthStart){
                            iter.remove();
                        }
                        else if(collisionWeapon(exploBullet, ship, ships.get(i))) {
                            exploBullet.setEnemiBullet(true);
                            distroyedBullets.add(exploBullet);
                            distroyedBulletsAnimation.add(exploBullet);
                            ammos.add(ammo);
                            iter.remove();

                        }
                    }
                    else{
                        if(ammo.getY() > Gdx.graphics.getHeight() - exploBullet.getRandExplosion() || ammo.getY() + ammo.getHeight()  < 0){
                            exploBullet.setEnemiBullet(false);
                            distroyedBullets.add(exploBullet);
                            distroyedBulletsAnimation.add(exploBullet);
                            iter.remove();
                        }
                        else if(ammo.getX() > GameScreen.screenGameWidthEnd || ammo.getX() + ammo.getWidth() < GameScreen.screenGameWidthStart){
                            iter.remove();
                        }
                        else if(collisionWeapon(exploBullet, ship, ships.get(i))) {
                            exploBullet.setEnemiBullet(false);
                            distroyedBullets.add(exploBullet);
                            distroyedBulletsAnimation.add(exploBullet);
                            ammos.add(ammo);
                            iter.remove();

                        }
                    }
                }
                else if(ammo instanceof MultiBullet){
                    if(!(ship instanceof Ship1)){
                        if(ammo.getY() > Gdx.graphics.getHeight() || ammo.getY() + ammo.getHeight() < ship.getY() - 600) iter.remove();
                        else if(ammo.getX() > GameScreen.screenGameWidthEnd || ammo.getX() + ammo.getWidth() < GameScreen.screenGameWidthStart) iter.remove();
                        else if(collisionWeapon(ammo, ship, ships.get(i))) {
                            ammos.add(ammo);
                            iter.remove();

                        }
                    }
                    else{
                        if(ammo.getY() > Gdx.graphics.getHeight() || ammo.getY() + ammo.getHeight() < 0) iter.remove();
                        else if(ammo.getX() > GameScreen.screenGameWidthEnd || ammo.getX() + ammo.getWidth() < GameScreen.screenGameWidthStart) iter.remove();
                        else if(collisionWeapon(ammo, ship, ships.get(i))) {
                            ammos.add(ammo);
                            iter.remove();

                        }
                    }

                }
                else{
                    if(ammo.getY() > Gdx.graphics.getHeight() || ammo.getY() + ammo.getHeight() < 0) iter.remove();
                    else if(ammo.getX() > GameScreen.screenGameWidthEnd || ammo.getX() + ammo.getWidth() < GameScreen.screenGameWidthStart) iter.remove();
                    else if(collisionWeapon(ammo, ship, ships.get(i))) {
                        ammos.add(ammo);
                        iter.remove();

                    }
                }
            }
        }
        if(ship instanceof Ship1){
            for(int i = 0; i < objects.size; i++){
                for (Iterator<Weapon> iter = ship.getTirs().iterator(); iter.hasNext(); ) {
                    Weapon ammo = iter.next();
                    ammo.hitboxUpdate();
                    if(ammo instanceof ExplosifBullet){
                        ExplosifBullet exploBullet = (ExplosifBullet) ammo;
                        exploBullet.hitboxExploUpdate();
                        if(ammo.getY() > Gdx.graphics.getHeight() - exploBullet.getRandExplosion()|| ammo.getY() + ammo.getHeight()  < 0){
                            exploBullet.setEnemiBullet(false);
                            distroyedBullets.add(exploBullet);
                            distroyedBulletsAnimation.add(exploBullet);
                            iter.remove();
                        }
                        else if(ammo.getX() > GameScreen.screenGameWidthEnd || ammo.getX() + ammo.getWidth() < GameScreen.screenGameWidthStart){
                            iter.remove();
                        }
                        else if(collisionWeapon(exploBullet, ship, objects.get(i))) {
                            exploBullet.setEnemiBullet(false);
                            distroyedBullets.add(exploBullet);
                            distroyedBulletsAnimation.add(exploBullet);
                            ammos.add(ammo);
                            iter.remove();

                        }
                    }
                    else if(ammo.getY() > Gdx.graphics.getHeight() || ammo.getY() + ammo.getHeight() < 0) iter.remove();
                    else if(ammo.getX() > GameScreen.screenGameWidthEnd || ammo.getX() + ammo.getWidth() < GameScreen.screenGameWidthStart) iter.remove();
                    else if(collisionWeapon(ammo, ship, objects.get(i))) {
                        ammos.add(ammo);
                        iter.remove();

                    }
                }
            }
            for(int i = 0; i < allBoss.size; i++){
                for (Iterator<Weapon> iter = ship.getTirs().iterator(); iter.hasNext(); ) {
                    Weapon ammo = iter.next();
                    ammo.hitboxUpdate();
                    if(ammo instanceof ExplosifBullet){
                        ExplosifBullet exploBullet = (ExplosifBullet) ammo;
                        exploBullet.hitboxExploUpdate();
                        if(ammo.getY() > Gdx.graphics.getHeight() - exploBullet.getRandExplosion()|| ammo.getY() + ammo.getHeight()  < 0){
                            exploBullet.setEnemiBullet(false);
                            distroyedBullets.add(exploBullet);
                            distroyedBulletsAnimation.add(exploBullet);
                            iter.remove();
                        }
                        else if(ammo.getX() > GameScreen.screenGameWidthEnd || ammo.getX() + ammo.getWidth() < GameScreen.screenGameWidthStart){
                            iter.remove();
                        }
                        else if(collisionWeapon(exploBullet, ship, allBoss.get(i))) {
                            exploBullet.setEnemiBullet(false);
                            distroyedBullets.add(exploBullet);
                            distroyedBulletsAnimation.add(exploBullet);
                            ammos.add(ammo);
                            iter.remove();

                        }
                    }
                    else if(ammo.getY() > Gdx.graphics.getHeight() || ammo.getY() + ammo.getHeight() < 0) iter.remove();
                    else if(ammo.getX() > GameScreen.screenGameWidthEnd || ammo.getX() + ammo.getWidth() < GameScreen.screenGameWidthStart) iter.remove();
                    else if(collisionWeapon(ammo, ship, allBoss.get(i))) {
                        ammos.add(ammo);
                        iter.remove();
                    }
                }
            }
        }
    }

    //verifie si le joueur est entré en collision avec un drop et l'ajoute au vaisseau
    public void touchedShip(){
        for(Iterator<BonusMalus> iter = drops.iterator(); iter.hasNext();){
            BonusMalus drop = iter.next();
            if(drop.getY() <= GameScreen.maxYShip){
                if (Intersector.overlaps(drop.getHitbox(), ship1.getHitbox())){
                    drop.Add(ship1);
                    iter.remove();
                    }
                else if(drop.getY() <= -drop.getHeight()){
                    iter.remove();
                }
                }
            }
        }


        //verifie si le timer (en dehors de life)du drop est terminé et si oui, restaure les stats/tirs
    public void verifBMTime(){
        if(ship1.getTimerDropDamage() + 10 == (int)new Date().getTime()/1000){
            ship1.setCoeffDamage(1);
            ship1.setTimerDropDamage(0);
        }
        else if(ship1.getTimerDropSpeed() + 10 == (int)new Date().getTime()/1000){
            ship1.setCoeffSpeed(1);
            ship1.setTimerDropSpeed(0);
        }
        else if(ship1.getTimerDropTir() + 10 == (int)new Date().getTime()/1000){
            ship1.setWeapon("Laser");
            ship1.setTimerDropTir(0);
        }
    }

    @Override
    public void show() {

    }

    //utilise la fonction generation entité du niveau et verifie de générer les parties qu'une seule fois, les unes après les autres en générant toutes les entitées
    //de manière à ce que le nombre d'enemies est progressif
    public void useGenerationEntitees(){
        if(current_part<getNbParts()){
            if(enemiesKilled <= enemiesSpawned){
                if(!levelParts[current_part]){
                    int nb_enemies_defaut = getEnemiesAmount()/getNbParts();
                    if((getNbParts()%2)!=0){
                        if(current_part+1<=getNbParts()/2){
                            int n;
                            if(current_part<=1){
                                n = 2+current_part;
                            }
                            else{
                                n = (2*current_part)+1;
                            }
                            generationEntite((int)(((float)n/(float)getNbParts())*nb_enemies_defaut),current_part);
                            enemiesSpawned += (int)(((float)n/(float)getNbParts())*nb_enemies_defaut);
                        }
                        else if(current_part+1 == (getNbParts()/2)+1){
                            generationEntite(nb_enemies_defaut,current_part);
                            enemiesSpawned += nb_enemies_defaut;
                        }
                        else if(current_part+1>(getNbParts()/2)+1 && current_part+1 < getNbParts()){
                            int n = (current_part*2)+1;
                            generationEntite((int)(((float)n/(float)getNbParts())*nb_enemies_defaut),current_part);
                            enemiesSpawned += (int)(((float)n/(float)getNbParts())*nb_enemies_defaut);
                        }
                        else if(current_part+1== getNbParts()){
                            generationEntite(getEnemiesAmount()-enemiesKilled,current_part);
                            enemiesSpawned += getEnemiesAmount()-enemiesKilled;
                        }
                    }
                    else{
                        if(getNbParts() == 2){
                            if(current_part == 0){
                                generationEntite((int)(((float)2/3.0)*nb_enemies_defaut),current_part);
                                enemiesSpawned += (int)(((float)2/3.0)*nb_enemies_defaut);
                            }
                            else{
                                generationEntite(getEnemiesAmount()-enemiesKilled,current_part);
                                enemiesSpawned += getEnemiesAmount()-enemiesKilled;
                            }
                        }
                        else{
                            if(current_part+1<=getNbParts()/2){
                                int n;
                                if(current_part<=1){
                                    n = 2+current_part;
                                }
                                else{
                                    n = (2*current_part)+1;
                                }
                                generationEntite((int)(((float)n/(float)getNbParts())*nb_enemies_defaut),current_part);
                                enemiesSpawned += (int)(((float)n/(float)getNbParts())*nb_enemies_defaut);
                            }
                            else if(current_part+1>(getNbParts()/2) && current_part+1 < getNbParts()){
                                int n = (current_part*2)+1;
                                generationEntite((int)(((float)n/(float)getNbParts())*nb_enemies_defaut),current_part);
                                enemiesSpawned += (int)(((float)n/(float)getNbParts())*nb_enemies_defaut);
                            }
                            else if(current_part+1== getNbParts()){
                                generationEntite(getEnemiesAmount()-enemiesKilled,current_part);
                                enemiesSpawned += getEnemiesAmount()-enemiesKilled;
                            }
                        }
                    }
                    levelParts[current_part]=true;
                }
            }
            if(enemiesKilled == enemiesSpawned && enemiesKilled != lastpart){
                lastpart = enemiesKilled;
                current_part++;
            }
        }
        else if(!(bossSpawn)){
            timerDropBoss = (int)new Date().getTime()/1000;
            if(getNumLevel() == "Level5"){
                setting.stopMusic();
                setting.playBossMusic();
            }
            allBoss.add(new Boss(GameScreen.screenGameWidthStart + GameScreen.GameScreenWidth/2, GameScreen.screenHeight + Boss.HEIGHT, 3, this.getBossLife()));
            bossSpawn = true;
        }
    }

    //renvoie la valeur du combo en fonction du nombre d'enemies tué sans avoir reçu de dégâts
    public int getComboValue(int combo){
        if(combo <= 1){
            return 1;
        }
        else if(combo <= 4){
            return 2;
        }
        else if(combo <= 10){
            return 3;
        }
        else{
            return 5;
        }
    }


    abstract public int getBossLife();

    //enlève l'invincibilité du joueur si 2s se sont écoulées après les derniers dégâts reçu
    public void setUpShipInvisible(){
        if(shipTimer + 2  <= (int)new Date().getTime()/1000){
            ShipIsInvisible = false;
        }
    }

    @Override
    public void render (float delta) {
        ScreenUtils.clear(Color.WHITE);
        camera.update();
        //permet d'afficher un fond dynamique
        backgroundOffSet +=5;
        if(backgroundOffSet%GameScreen.screenHeight == 0){
            backgroundOffSet =0;
        }
        shootEmUpGame.batch.setProjectionMatrix(camera.combined);

        shootEmUpGame.batch.begin();


        //  les images de fond sont , un background à droite et à gauche identique, qui sont en mouvement, deux images en mirroir pour le jeu
        //  avec à chaque fois une image au dessus de celle visible initialement qui permet de dérouler
        shootEmUpGame.batch.draw(backgroundTexture,  0 , -backgroundOffSet  + GameScreen.screenHeight, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight());
        shootEmUpGame.batch.draw(backgroundTexture,  3*(Gdx.graphics.getWidth()/4) ,- backgroundOffSet  + GameScreen.screenHeight, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight());
        shootEmUpGame.batch.draw(backgroundTexture,  0 ,- backgroundOffSet, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight());
        shootEmUpGame.batch.draw(backgroundTexture,  3*(Gdx.graphics.getWidth()/4) ,- backgroundOffSet, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight());
        shootEmUpGame.batch.draw(backgroundGameD, GameScreen.screenGameWidthStart + GameScreen.GameScreenWidth/2, - backgroundOffSet, GameScreen.GameScreenWidth/2 , Gdx.graphics.getHeight());
        shootEmUpGame.batch.draw(backgroundGameD, GameScreen.screenGameWidthStart + GameScreen.GameScreenWidth/2, - backgroundOffSet + GameScreen.screenHeight, GameScreen.GameScreenWidth/2 , Gdx.graphics.getHeight());
        shootEmUpGame.batch.draw(backgroundGameG, GameScreen.screenGameWidthStart, - backgroundOffSet, GameScreen.GameScreenWidth/2 , Gdx.graphics.getHeight());
        shootEmUpGame.batch.draw(backgroundGameG, GameScreen.screenGameWidthStart, - backgroundOffSet + GameScreen.screenHeight, GameScreen.GameScreenWidth/2 , Gdx.graphics.getHeight());
       //si le jeu n'est pas fini (game over ou victoire, on dessines les elements et update les positions,spawn,despawn,hitbox etc
        //et on verifie si il y a une victoire /defaite
        if(!gameStop){
            if(levelParts == null){
                levelParts = new boolean[getNbParts()];
            }
            useGenerationEntitees();
            for (Entite ship : ships) {
                if(ship instanceof ShieldEnemi){
                    if(ship.getLife() < 75){
                        ship.setSprite(new Texture(Gdx.files.internal("assets/entite/enemi.png")));
                    }
                }
                shootEmUpGame.batch.setColor(1, (float) ship.getLife() / 100f, (float) ship.getLife() / 100f, 1);
                shootEmUpGame.batch.draw(ship.getSprite(), ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
                shootEmUpGame.batch.setColor(Color.WHITE);
                ShipPattern(ship);
                drawBullets(ship);
                ship.hitboxUpdate();
                ship.move();
                dispawnBullets(ship);
            }
            for (Entite object : objects) {
                shootEmUpGame.batch.setColor(1, (float) object.getLife() / 100f, (float) object.getLife() / 100f, 1);
                shootEmUpGame.batch.draw(object.getSprite(), object.getX(), object.getY(), object.getWidth(), object.getHeight());
                shootEmUpGame.batch.setColor(Color.WHITE);
                object.hitboxUpdate();
                if(object instanceof MissileGuide){
                    MissileGuide objectMG = (MissileGuide) object;
                    objectMG.move(ship1);
                }
                else{
                    object.move();
                }
            }
            for (BonusMalus drop : drops){
                shootEmUpGame.batch.draw(drop.getSprite(), drop.getX(), drop.getY(), drop.getWidth(), drop.getHeight());
                shootEmUpGame.batch.setColor(Color.WHITE);
                drop.hitboxUpdate();
                drop.move();
            }

            for (Boss boss : allBoss){
                shootEmUpGame.batch.setColor(1, (float) boss.getLife() / 100f, (float) boss.getLife() / 100f, 1);
                shootEmUpGame.batch.draw(boss.getSprite(), boss.getX(), boss.getY(), boss.getWidth(), boss.getHeight());
                shootEmUpGame.batch.setColor(Color.WHITE);
                ShipPattern(boss);
                BossPattern(boss);
                drawBullets(boss);
                boss.hitboxUpdate();
                boss.move();
                dispawnBullets(boss);
            }

            if(checkDefeat()) {
                gameStop = true;
                lose = true;
                setting.playDefeatSound();
                setting.setMusicVolume(0.1f);
            }

            else if(checkVictory()) {
                gameStop = true;
                win = true;

                setting.playVictorySound();
                setting.setMusicVolume(0.1f);
            }
            else{
                checkedPress(1);
            }
            touchedShip();
            verifBMTime();
            dispawnObjects();
            dispawnShip();
            shipExplosion();
            BulletExplosion();
            if(bossSpawn){
                if(!bossDispawned){
                    if(timerDropBoss + 10 == (int)new Date().getTime()/1000){
                        drops.add(allBoss.get(0).Drop());
                        timerDropBoss = (int)new Date().getTime()/1000;
                    }
                }
                BossBulletExplosion();
                dispawnBoss();
                BossExplosion();
            }
            setUpShipInvisible();
            if(ShipIsInvisible){
                ship1.setUpInvisibleSprite();
            }
            else{
                ship1.setSprite(new Texture(Gdx.files.internal("assets/entite/blueships1_small.png")));
            }
        }
        else{
            //si le jeu est perdu , freeze les entitees et les assombris, tout en affichant un cadre pour le game over
            if(lose){
                for (Entite ship : ships) {
                    shootEmUpGame.batch.setColor(0.1f,0.1f ,0.1f , 1);
                    shootEmUpGame.batch.draw(ship.getSprite(), ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
                    shootEmUpGame.batch.setColor(Color.WHITE);
                }
                for (Entite object : objects) {
                    shootEmUpGame.batch.setColor(0.1f,0.1f ,0.1f , 1);
                    shootEmUpGame.batch.draw(object.getSprite(), object.getX(), object.getY(), object.getWidth(), object.getHeight());
                    shootEmUpGame.batch.setColor(Color.WHITE);
                }
                for (Boss boss : allBoss){
                    shootEmUpGame.batch.setColor(0.1f,0.1f ,0.1f , 1);
                    shootEmUpGame.batch.draw(boss.getSprite(), boss.getX(), boss.getY(), boss.getWidth(), boss.getHeight());
                    shootEmUpGame.batch.setColor(Color.WHITE);
                }
                shootEmUpGame.batch.setColor(Color.WHITE);
                shootEmUpGame.batch.draw(filtre, GameScreen.screenGameWidthStart + 185 + 37, GameScreen.screenHeight - cadre.getHeight() - GameScreen.maxYShip + 25);
                shootEmUpGame.font.setColor(Color.RED);
                shootEmUpGame.font.getData().setScale(5f, 5f);
                shootEmUpGame.font.draw(shootEmUpGame.batch, "Game Over", GameScreen.screenGameWidthStart  - 8 + (int)(cadre.getWidth()/2), GameScreen.screenHeight - cadre.getHeight() - GameScreen.maxYShip + 125);
                shootEmUpGame.font.getData().setScale(1.5f, 1.5f);
            }

            //si le jeu est perdu , freeze les entitees tout en affichant un cadre pour la victoire avec le rang
            else if(win){
                shootEmUpGame.batch.setColor(1, (float) ship1.getLife() / 100f, (float) ship1.getLife() / 100f, 1);
                shootEmUpGame.batch.draw(ship1.getSprite(), ship1.getX(), ship1.getY(), ship1.getWidth(), ship1.getHeight());
                shootEmUpGame.batch.setColor(Color.WHITE);
                shootEmUpGame.batch.draw(filtre, GameScreen.screenGameWidthStart + 185 + 37, GameScreen.screenHeight - cadre.getHeight() - GameScreen.maxYShip + 25);
                if(getResult(getNumLevel()) == "F"){
                    colorResult = Color.BROWN ;
                }
                else if(getResult(getNumLevel()) == "E"){
                    colorResult = Color.FIREBRICK ;
                }
                else if(getResult(getNumLevel()) == "D"){
                    colorResult = Color.OLIVE;
                }
                else if(getResult(getNumLevel()) == "C"){
                    colorResult = Color.CYAN ;
                }
                else if(getResult(getNumLevel()) == "B"){
                    colorResult = Color.BLUE ;
                }
                else if(getResult(getNumLevel()) == "A"){
                    colorResult = Color.RED ;
                }
                else if(getResult(getNumLevel()) == "S"){
                    colorResult = Color.MAGENTA ;
                }
                else{
                    colorResult = Color.GOLDENROD ;
                }
                shootEmUpGame.font.getData().setScale(4f, 4f);
                shootEmUpGame.font.setColor(colorResult);
                shootEmUpGame.font.draw(shootEmUpGame.batch,"Rang   "+getResult(getNumLevel()),GameScreen.screenGameWidthStart + 130 + (int)(cadre.getWidth()/2), GameScreen.screenHeight - cadre.getHeight() - GameScreen.maxYShip + 203);
                shootEmUpGame.font.setColor(Color.GOLD);
                shootEmUpGame.font.getData().setScale(5f, 5f);
                shootEmUpGame.font.draw(shootEmUpGame.batch, "Victoire", GameScreen.screenGameWidthStart + 58 + (int)(cadre.getWidth()/2), GameScreen.screenHeight - cadre.getHeight() - GameScreen.maxYShip + 125);
                shootEmUpGame.font.getData().setScale(1.5f, 1.5f);
            }
            shootEmUpGame.font.setColor(Color.LIGHT_GRAY);
            shootEmUpGame.font.getData().setScale(2f, 2f);
            shootEmUpGame.font.draw(shootEmUpGame.batch, "Press Escape to", GameScreen.screenGameWidthStart - 30 + (int)(cadre.getWidth()/2), GameScreen.screenHeight - cadre.getHeight() - GameScreen.maxYShip + 295);
            shootEmUpGame.font.draw(shootEmUpGame.batch, "Quit", GameScreen.screenGameWidthStart + 30 +   (int)(cadre.getWidth()/2), GameScreen.screenHeight - cadre.getHeight() - GameScreen.maxYShip + 255);
            shootEmUpGame.font.draw(shootEmUpGame.batch, "Press Enter to", GameScreen.screenGameWidthStart + 225 + (int)(cadre.getWidth()/2), GameScreen.screenHeight - cadre.getHeight() - GameScreen.maxYShip + 295);
            shootEmUpGame.font.draw(shootEmUpGame.batch, "Restart", GameScreen.screenGameWidthStart + 270 + (int)(cadre.getWidth()/2), GameScreen.screenHeight - cadre.getHeight() - GameScreen.maxYShip + 255);
            shootEmUpGame.font.getData().setScale(1.5f, 1.5f);
            shootEmUpGame.batch.draw(cadre, GameScreen.screenGameWidthStart + 185, GameScreen.screenHeight - cadre.getHeight() - GameScreen.maxYShip);
            shootEmUpGame.font.setColor(Color.WHITE);
            checkedPress(0);
        }
        shootEmUpGame.batch.draw(separ, GameScreen.screenGameWidthStart - 50, 0, 10, GameScreen.screenHeight);
        shootEmUpGame.batch.draw(separ, GameScreen.screenGameWidthEnd + 40, 0, 10, GameScreen.screenHeight);
        hud();
        if(!musicPlayed){
            setting.playLevelMusic(getNumLevel());
            musicPlayed = true;
        }
        shootEmUpGame.batch.end();

    }

    public boolean checkDefeat(){
        if(ship1.getLife() <= 0) {
            return true;
        }
        return false;
    }

    abstract public boolean checkVictory();

    //verifie si en fonction de l'état du jeu, si une touche à été justpresses pour quitter si escape et restart si defaite/victoire et que enter est justpressed
    // 0 = Defaite , Victoire  ; 1 = Game
    public void checkedPress(int stop){
        if(stop == 0) {
            if(Gdx.input.isKeyJustPressed(escape)){
                gameStop = true;
                setting.setMusicVolume(musicVolume);
                dispose();
                shootEmUpGame.setScreen(new Start(shootEmUpGame, setting));
            }
            else if(Gdx.input.isKeyJustPressed(enter)){
                gameStop = true;
                setting.setMusicVolume(musicVolume);
                dispose();
                if(getNumLevel() == "Level1"){
                    shootEmUpGame.setScreen(new Level1(shootEmUpGame,setting));
                }
                else if(getNumLevel() == "Level2"){
                    shootEmUpGame.setScreen(new Level2(shootEmUpGame,setting));
                }
                else if(getNumLevel() == "Level3"){
                    shootEmUpGame.setScreen(new Level3(shootEmUpGame,setting));
                }
                else if(getNumLevel() == "Level4"){
                    shootEmUpGame.setScreen(new Level4(shootEmUpGame,setting));
                }
                else if(getNumLevel() == "Level5"){
                    shootEmUpGame.setScreen(new Level5(shootEmUpGame,setting));
                }
            }
        }
        else if(stop == 1){
            if(Gdx.input.isKeyJustPressed(escape)){
                gameStop = true;
                setting.setMusicVolume(musicVolume);
                dispose();
                shootEmUpGame.setScreen(new Start(shootEmUpGame, setting));
            }
        }
    }

    //définit la couleur du combo dans le hud en fonction de la valeur du combo
    public Color getComboColor(int comboValue){
        Color c = Color.WHITE;
        if(comboValue == 2){
            c = Color.BLUE;
        }
        else if(comboValue == 3){
            c = Color.RED;
        }
        else if(comboValue == 4){
            c = Color.GOLDENROD;
        }
        else if(comboValue == 5){
            c = Color.MAGENTA;
        }
        return c;
    }

    //définit la taille du combo dans le hud en fonction de la valeur du combo
   public float getComboSize(int comboValue){
        float sizeCombo = 1.5f + (comboValue * 0.2f);
        return sizeCombo;
    }

    //définit le rang en fonction du score et du niveau
    public String getResult(String level){
        String result;
        if(level == "Level1"){
            if(score < 13000){
                result = "F";
            }
            else if(score < 16000){
                result = "E";
            }
            else if(score < 20000){
                result = "D";
            }
            else if(score < 23000){
                result = "C";
            }
            else if(score < 25000){
                result = "B";
            }
            else if(score < 28000){
                result = "A";
            }
            else if(score < 30000){
                result = "S";
            }
            else{
                result = "S+";
            }
        }
        else if(level == "Level2"){
            if(score < 20000){
                result = "F";
            }
            else if(score < 23000){
                result = "E";
            }
            else if(score < 28000){
                result = "D";
            }
            else if(score < 33000){
                result = "C";
            }
            else if(score < 37000){
                result = "B";
            }
            else if(score < 42000){
                result = "A";
            }
            else if(score < 45000){
                result = "S";
            }
            else{
                result = "S+";
            }
        }
        else if(level == "Level3"){
            if(score < 30000){
                result = "F";
            }
            else if(score < 36000){
                result = "E";
            }
            else if(score < 41000){
                result = "D";
            }
            else if(score < 45000){
                result = "C";
            }
            else if(score < 50000){
                result = "B";
            }
            else if(score < 54000){
                result = "A";
            }
            else if(score < 58000){
                result = "S";
            }
            else{
                result = "S+";
            }
        }
        else if(level == "Level4"){
            if(score < 34000){
                result = "F";
            }
            else if(score < 40000){
                result = "E";
            }
            else if(score < 45000){
                result = "D";
            }
            else if(score < 50000){
                result = "C";
            }
            else if(score < 54000){
                result = "B";
            }
            else if(score < 58000){
                result = "A";
            }
            else if(score < 62000){
                result = "S";
            }
            else{
                result = "S+";
            }
        }
        else{
            if(score < 83000){
                result = "F";
            }
            else if(score < 90000){
                result = "E";
            }
            else if(score < 96000){
                result = "D";
            }
            else if(score < 105000){
                result = "C";
            }
            else if(score < 113000){
                result = "B";
            }
            else if(score < 125000){
                result = "A";
            }
            else if(score < 135000){
                result = "S";
            }
            else{
                result = "S+";
            }
        }
            return result;
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
        setting.stopMusic();
    }

}

