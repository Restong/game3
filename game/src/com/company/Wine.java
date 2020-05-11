package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Wine extends GameObject {
    private Handler handler;
Game game;

    private BufferedImage[] Wine_image = new BufferedImage[5];
    Animation anim;
    public Wine(int x, int y,ID id,Game game,SpriteSheet ss){
        super(x,y,id,ss);
        this.handler = handler;
        this.game=game;
        Wine_image[0] = ss.grabImage(7,1,32,32);
        Wine_image[1] = ss.grabImage(8,1,32,32);
        Wine_image[2] = ss.grabImage(9,1,32,32);
        Wine_image[3] = ss.grabImage(10,1,32,32);
        Wine_image[4] = ss.grabImage(11,1,32,32);
        anim = new Animation(3,Wine_image[0],Wine_image[1],Wine_image[2],Wine_image[3],Wine_image[4],Wine_image[3],Wine_image[2],Wine_image[1]);
    }

    public void tick() {

        anim.runAnimation();
    }


    public void render(Graphics g) {
        anim.drawAnimation(g,x,y,0,game,0,0);
    }


    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}
