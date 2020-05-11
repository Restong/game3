package com.company;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MouseInput extends MouseAdapter {
    private Handler handler;
    private Camera camera;
    private  Game game;
    private  SpriteSheet ss;
    public MouseInput(Handler handler, Camera camera, Game game, SpriteSheet ss){
        this.handler =handler;
        this.camera =camera;
        this.game =game;
        this.ss =ss;
    }
    public void mouseMoved(MouseEvent e) {
        System.out.println("moved");
    }
    public void mousePressed(MouseEvent e){
        game.mx = (int)(e.getX() + camera.getX());
        game.my = (int)(e.getY() + camera.getY());
        for(int i =0; i< handler.object.size();i++){
            GameObject tempObject =handler.object.get(i);
            if (tempObject.getID()==ID.Player&&game.ammo>=1&&game.hp>0){
                handler.addObject(new Bullet(tempObject.getX()+16,tempObject.getY()+24,ID.Bullet,handler,game.mx,game.my,ss));

                game.ammo--;

            }
        }
    }
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println("wheel");
    }

}
