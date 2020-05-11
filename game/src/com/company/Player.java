package com.company;



import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Player extends GameObject {
    Handler handler;
    Game game;
    private BufferedImage[] player_image = new BufferedImage[3];
    Animation anim;
    public Player(int x, int y, ID id,Handler handler,Game game, SpriteSheet ss) {
        super(x, y, id,ss);
        this.handler=  handler;
        this.game =game;
        player_image[0] =ss.grabImage(1,1,32,48);
        player_image[1] =ss.grabImage(2,1,32,48);
        player_image[2] =ss.grabImage(3,1,32,48);
        anim =new Animation(3,player_image[0],player_image[1],player_image[2]);

    }

    public void tick() {
        x+= velX;
        y+= velY;
        collision();
        //mvmnt
        if(handler.isUp()) velY= -5;
        else if (!handler.isDown())velY=0;
        if(handler.isDown()) velY= 5;
        else if (!handler.isUp())velY=0;

        if(handler.isRight()) velX= 5;
        else if (!handler.isLeft())velX=0;
        if(handler.isLeft()) velX= -5;
        else if (!handler.isRight())velX=0;
        anim.runAnimation();

    }
    public void collision(){
        for(int i =0; i< handler.object.size();i++){

                GameObject tempObject = handler.object.get(i);


            if(tempObject.getID()==ID.Block){
                if (getBounds().intersects(tempObject.getBounds())){
                x+=velX* -1;
                y+=velY*-1;

                }
            }
            if(tempObject.getID()==ID.Crate){
                if (getBounds().intersects(tempObject.getBounds())){
                    game.ammo +=10;
                    handler.removeObject(tempObject);

                }
            }
            if(tempObject.getID()==ID.Enemy){
                if (getBounds().intersects(tempObject.getBounds())){
                    game.hp--;
                }
            }
            if(tempObject.getID()==ID.Wine){
                if (getBounds().intersects(tempObject.getBounds())){
                    game.hp+=10;
                    handler.removeObject(tempObject);
                }
            }
            if(tempObject.getID()==ID.Page){
                if (getBounds().intersects(tempObject.getBounds())){
                    if(game.PG<4){
                        game.PG+=1;
                        try {
                            List<String> lines = Arrays.asList("PF="+game.PG);
                            Path file = Paths.get("res/text.txt");
                            Files.write(file, lines, StandardCharsets.UTF_8);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    handler.removeObject(tempObject);
                }
            }
        }
    }


    public void render(Graphics g) {
//        g.setColor(Color.BLUE);
//        g.fillRect(x,y,32,48);
        int vx =   -(game.mx-x);
        int vy = game.my-y;



        if (velX==0 &&velY==0){//no player movement

            AffineTransform at = new AffineTransform();
            at.translate(x,y);
            at.rotate(vy,vx,16,11);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(player_image[0], at, null);
           // g.drawImage(player_image[0],x,y,null);



        }else{


            anim.drawAnimation(g,x,y,1,game,0,0);
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,32,48);
    }
}