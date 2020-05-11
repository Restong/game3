package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private  Game game;
    public KeyInput(Handler handler, Game game){
        this.handler=handler;
        this.game =game;
    }
    public void keyPressed(KeyEvent e){
        int key= e.getKeyCode();
        System.out.println(key);
        if(key==KeyEvent.VK_ESCAPE&&game.hp>0){
            handler.nw();
        }
        for(int i=0; i<handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getID()==ID.Player&&game.hp>0){
                if(key==KeyEvent.VK_W)handler.setUp(true);
                if(key==KeyEvent.VK_S)handler.setDown(true);
                if(key==KeyEvent.VK_A)handler.setLeft(true);
                if(key==KeyEvent.VK_D)handler.setRight(true);
                if(key==KeyEvent.VK_UP)handler.setUp(true);
                if(key==KeyEvent.VK_DOWN)handler.setDown(true);
                if(key==KeyEvent.VK_LEFT)handler.setLeft(true);
                if(key==KeyEvent.VK_RIGHT)handler.setRight(true);
            }

        }

    }
    public void keyReleased(KeyEvent e){
        int key= e.getKeyCode();
        for(int i=0; i<handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getID()==ID.Player&&game.hp>0){
                if(key==KeyEvent.VK_W)handler.setUp(false);
                if(key==KeyEvent.VK_S)handler.setDown(false);
                if(key==KeyEvent.VK_A)handler.setLeft(false);
                if(key==KeyEvent.VK_D)handler.setRight(false);
                if(key==KeyEvent.VK_UP)handler.setUp(false);
                if(key==KeyEvent.VK_DOWN)handler.setDown(false);
                if(key==KeyEvent.VK_LEFT)handler.setLeft(false);
                if(key==KeyEvent.VK_RIGHT)handler.setRight(false);
            }
        }
    }
}