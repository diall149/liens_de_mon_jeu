package com.mygdx.game.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MultiBullet extends Weapon{
        public static int WIDTH = 10;
        public static int HEIGHT = 30;
        public static int DAMAGE = 15;
        public static int SPEED = 20;

        private float direction;

        public float getDirection() {
            return direction;
        }

        public MultiBullet(int x, int y,float direction, float speed, float dmg){
            // valeurs arbitraires
            super(x, y, speed,dmg, WIDTH, HEIGHT);
            this.setSprite(new Texture(Gdx.files.internal(SPRITE_AMMO_DIR + "multi.png")));
            this.direction = direction;
        }

        public MultiBullet(int x, int y, float direction){
        this(x, y, direction, SPEED ,DAMAGE);
    }
}
