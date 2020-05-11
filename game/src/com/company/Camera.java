package com.company;

public class Camera {
    private float x,y;
    public Camera (float x, float y){
        this.x=x;
        this.y=y;
    }
    public void tick(GameObject object){
//    x=50;
//y=object.getY();
        x+=((object.getX()-x)-1000/2)*0.05f; //smooth camera not hard lock on
        y+=((object.getY()-y)-563/2)*0.05f; //smooth camera not hard lock on
        if(x<=0) x=0;
        if(x>=1032)x=1032;
        if(y<=0) y=0;
        if(y>=579)y=579;
//edge bounds for map
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
