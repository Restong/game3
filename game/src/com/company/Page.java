package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Page extends GameObject {
    private Handler handler;
Game game;

    private BufferedImage[] Page_image = new BufferedImage[3];
    Animation anim;
    public Page(int x, int y, ID id,Game game, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.game=game;
        Page_image[0] =ss.grabImage(7,2,32,32);
        Page_image[1] =ss.grabImage(8,2,32,32);
        Page_image[2] =ss.grabImage(9,2,32,32);
        anim = new Animation(5,Page_image[1],Page_image[0],Page_image[2],Page_image[0]);

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
