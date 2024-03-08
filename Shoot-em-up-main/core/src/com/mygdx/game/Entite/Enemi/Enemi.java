package com.mygdx.game.Entite.Enemi;
import com.mygdx.game.Entite.Entite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Weapon.Laser;

public class Enemi extends Entite {

    public Enemi(int x, int y, int speed){
        super(x, y, 150, 150, 100);
        this.setSpeed(speed);
        this.setSprite(new Texture(Gdx.files.internal( Sprite_directory + "roundysh_small.png")));
    }

    @Override
    public void move(){
        if(getX() + getWidth() > Gdx.graphics.getWidth() || getX() < 0) setSpeed(-getSpeed());
        setX(getX() + getSpeed());
    }

    @Override
    public void shootBullets() {
        this.getTirs().add(new Laser(this.getX() + this.getWidth() / 2, this.getY() - this.getHeight() / 2));
    }
}
