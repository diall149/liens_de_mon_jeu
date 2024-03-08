package com.mygdx.game.menu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Entite.Entite;
import com.mygdx.game.Weapon.Laser;
import com.mygdx.game.Entite.PJ.Ship1;
import com.mygdx.game.Entite.Enemi.Enemi;
import com.mygdx.game.Weapon.Weapon;
import java.util.Iterator;
import java.util.Random ;

public class Jeu implements Screen {

    private final ShootEmUpGame shootEmUpGame;
    private final OrthographicCamera camera;
    private final Ship1 ship1;
    private final Enemi enemi;

    private final Texture backgroundTexture;

    private final Array<Entite> ships;
    private final Array<Weapon> ammos;
    private final Array<Entite> distroyed;

    public Jeu(ShootEmUpGame shootEmUpGame){
        this.shootEmUpGame = shootEmUpGame;
        this.shootEmUpGame.addScreen(this);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //shootEmUpGame.font.getData().setScale(1.5f, 1.5f);

        ship1 = new Ship1();
        enemi = new Enemi(0, Gdx.graphics.getHeight() - 200, 5);
        ships = new Array<>();
        ammos = new Array<>();
        distroyed = new Array<>();

        ships.add(ship1, enemi);

        backgroundTexture = new Texture(Gdx.files.internal("assets/shmup/Planets.jpg"));
    }

    public void hud(){
        shootEmUpGame.batch.draw(ship1.getSprite(), 10, 20, 50, 50);
        shootEmUpGame.font.draw(shootEmUpGame.batch, ship1.getLife() + "%", 80, 50);
        shootEmUpGame.batch.draw(enemi.getSprite(), Gdx.graphics.getWidth() - 60, 20, 50, 50);
        //shootEmUpGame.font.draw(shootEmUpGame.batch, enemi.getLife() + "%", Gdx.graphics.getWidth() - 120, 50);
    }

    public void spawnBullets(Entite ship){
        if(ship instanceof Ship1){
            if(Gdx.input.justTouched()) ship.shootBullets();
        }
        else if(ship instanceof Enemi){
            Random r = new Random();
            int n = r.nextInt(25);
            if(n==0){
                ship.shootBullets();
            }
        }
    }

    public void drawBullets(Entite ship){
        spawnBullets(ship);
        for(Weapon ammo : ship.getTirs()){
            shootEmUpGame.batch.draw(ammo.getSprite(), ammo.getX(), ammo.getY(), ammo.getWidth(), ammo.getHeight());
            if(ship instanceof Ship1) ammo.setY(ammo.getY() + ammo.getSpeed());
            else ammo.setY(ammo.getY() - ammo.getSpeed());
        }
    }

    private void dispawnShip(){
        for(Iterator<Entite> iter = ships.iterator(); iter.hasNext();){
            Entite ship = iter.next();
            if(ship.getLife() <= 0) {
                distroyed.add(ship);
                iter.remove();
            }
        }
    }

    private boolean collision(Weapon ammo, Entite aggressor, Entite touched) {
        if (Intersector.overlaps(ammo.getHitbox(), touched.getHitbox())) {
            //if (!(aggressor instanceof Enemi)) {
                touched.setLife(touched.getLife() - ammo.getDmg());
                if (touched.getLife() <= 0) touched.setLife(0);
                return true;
            //}
        }
        return false;
    }

    public void dispawnBullets(Entite ship) {
        for(int i = 0; i < ships.size; i++){
            for (Iterator<Weapon> iter = ship.getTirs().iterator(); iter.hasNext(); ) {
                Weapon ammo = iter.next();
                ammo.hitboxUpdate();
                if(ammo.getY() > Gdx.graphics.getHeight() || ammo.getY() + ammo.getHeight() < 0) iter.remove();
                if(collision(ammo, ship, ships.get(i))) {
                    ammos.add(ammo);
                    iter.remove();
                }
            }
        }
    }



    @Override
    public void show() {

    }

    @Override
    public void render (float delta) {
        ScreenUtils.clear(Color.WHITE);
        camera.update();
        shootEmUpGame.batch.setProjectionMatrix(camera.combined);

        shootEmUpGame.batch.begin();

        shootEmUpGame.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for (Entite ship : ships) {
            shootEmUpGame.batch.setColor(1, (float) ship.getLife() / 100f, (float) ship.getLife() / 100f, 1);
            shootEmUpGame.batch.draw(ship.getSprite(), ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
            shootEmUpGame.batch.setColor(Color.WHITE);
            drawBullets(ship);
            ship.hitboxUpdate();
            ship.move();
            dispawnBullets(ship);
        }

        dispawnShip();

        hud();
        shootEmUpGame.batch.end();

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

    }

}
