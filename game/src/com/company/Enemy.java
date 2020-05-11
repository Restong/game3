package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject{
    private Handler handler;
    Game game;
    Random r = new Random();
    int choose =0;
    int hp =100;
    private BufferedImage[] enemy_image = new BufferedImage[5];
    Animation anim;
    public Enemy(int x, int y,ID id, Handler handler,Game game,SpriteSheet ss){
        super(x,y,id,ss);
        this.handler = handler;
        this.game = game;
        enemy_image[0] = ss.grabImage(1,3,32,32);
        enemy_image[1] = ss.grabImage(2,3,32,32);
        enemy_image[2] = ss.grabImage(3,3,32,32);//mid
        enemy_image[3] = ss.grabImage(4,3,32,32);
        enemy_image[4] = ss.grabImage(5,3,32,32);
        anim = new Animation(3,enemy_image[0],enemy_image[1],enemy_image[2],enemy_image[3],enemy_image[4],enemy_image[3],enemy_image[2],enemy_image[1]);
    }

    public void tick() {
        x+=velX;
        y+=velY;
        choose = r. nextInt(10);
        for (int i =0; i <handler.object.size(); i++){ // code to prevent the enemy from going through walls
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getID()==ID.Block){
                if(getBoundsBig().intersects(tempObject.getBounds())){
                    x += (velX*5) * -1;
                   y += (velY*5) * -1;
                   velX *=-1;
                   velY *=-1;
                }else if(choose==0){
                    velX = (r.nextInt(4 - -4)+-4);
                    velY = (r.nextInt(4 - -4)+-4);
                }
            }
            if (tempObject.getID()==ID.Bullet){// kill enemies
                if (getBounds().intersects(tempObject.getBounds())){
                    hp-=50;
                    handler.removeObject(tempObject);
                }

            }
        }
        anim.runAnimation();
        if (hp<=0){
            game.ennum-=1;
            handler.removeObject(this);
        }
    }


    public void render(Graphics g) {
//            g.setColor(Color.yellow);
//            g.fillRect(x,y,32,32);
        // g.drawImage(enemy_image,x,y,null);
        anim.drawAnimation(g,x,y,2,game,velX,velY);
    }


    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
    public Rectangle getBoundsBig() {// so the enemy doesnt go through walls
        return new Rectangle(x-16,y-16,64,64);
    }
}
