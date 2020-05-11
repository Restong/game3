package com.company;



import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Game extends Canvas implements Runnable{// main class
    private boolean isRunning =false;
    private Thread thread;
    private Handler handler;
    private BufferedImage level =null;
    private BufferedImage sprite_sheet=null;
    private BufferedImage floor =null;
    private Camera camera;
    private SpriteSheet ss;
    public Window window;
    public int ammo = 50;
    public int hp =100;
    public int mx=0;
    public  int my=0;
    public int ennum=13;
    public String data = readFileAsString("res\\text.txt");

    public int PG=Integer.parseInt(data.substring(3,4));
    public List<Object> guns = Arrays.asList(0,1,2);

    public Game() throws Exception {
       window = new Window(1032,580,"Mafia Mania",this);

          // start();


           handler = new Handler();
           camera= new Camera(0,0);
           this.addKeyListener(new KeyInput(handler,this));

           BufferedImageLoader loader = new BufferedImageLoader();

           level=loader.loadImage("/topdown_map.png");//class not linked properly?
           sprite_sheet = loader.loadImage("/sprite_sheet.png");
           ss = new SpriteSheet(sprite_sheet);
           floor =ss.grabImage(4,2,32,32);
           this.addMouseListener(new MouseInput(handler, camera,this,ss));
           loadLevel(level);
           //  handler.addObject(new Player(100,100,ID.Player,handler));
           //handler.addObject(new Block( 100,100, ID.Block)); //now game knows its a block not a player


    }


    public void start(){
        isRunning=true;
        thread = new Thread(this);
        thread.start();
    }
    public void stop(){
        isRunning=false;
        try{// code below can fail
            thread.join();
        }catch (InterruptedException e){//cathces the fail
            e.printStackTrace();
        }

    }
    public void run() {//game loop
        this.requestFocus();//notch code
        long lastTime =System.nanoTime();
        double amountOfTicks =60.0;
        double ns =1000000000/ amountOfTicks;
        double delta=0;
        long timer =System.currentTimeMillis();
        int frames =0;
        while(isRunning){
            long now =System.nanoTime();
            delta+=(now-lastTime)/ ns;
            lastTime= now;
            while(delta>=1){
                tick();
                //updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis()-timer>1000){
                timer+=1000;
                frames=0;
                //updates =0;
            }
        }
        stop();
    }
    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
   
        return data;
    }
    public void tick(){//updates everything in the game, 60 times a sec
        if (!handler.pause){
            handler.once=false;
            window.QG.setVisible(false);
            window.frame.validate();
            for (int i = 0; i < handler.object.size(); i++) {
                if (handler.object.get(i).getID() == ID.Player) {
                    camera.tick(handler.object.get(i));
                }


            }


            handler.tick();
            if (hp <= 0||ennum<=0) {
                handler.pause=true;
            }
        }else{
            if (!handler.once){
                if (hp>0&&ennum>1){
                    window.psmenu(this);

                }else if (ennum<=0){
                    window.win(this);
                }else{
                    System.out.println("You died");
                    window.death(this);
                }
                handler.once =true;
            }




        }
    }
    public void render(){//makes everything in our game, thousands of times a sec
        BufferStrategy bs =this.getBufferStrategy();
        if (bs ==null){
            this.createBufferStrategy(3);//three arguments
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        //////////////////////////////////////draw area below
        g.setColor(Color.red);
        g.fillRect(0,0,1000,563);
        g2d.translate(-camera.getX(),-camera.getY());
        for (int xx=0;xx<30*72;xx+=32){//so tiles look nice
            for(int yy=0; yy < 30*72;yy+=32){
                g.drawImage(floor,xx,yy,null);
            }
        }

        handler.render(g);
        g2d.translate(camera.getX(),camera.getY());
        g.setColor(Color.gray);
        g.fillRect(5,5,200,32);
        g.setColor(Color.green);
        g.fillRect(5,5,hp*2,32);
//        g.setColor(Color.black);
//        g.fillRect(5,5,200,32);

        g.setColor(Color.white);
        g.drawString("Ammo: "+ ammo,5,50);
        //////////////////////////////////////
        g.dispose();
        bs.show();
    }
    //loading the lvl
    private void loadLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        for(int xx=0;xx<w;xx++){
            for(int yy = 0;yy<h;yy++){
                int pixel= image.getRGB(xx,yy);
                int red=(pixel>>16) & 0xff;
                int green=(pixel>>8) & 0xff;
                int blue=(pixel) & 0xff;

                if(red==255&&blue==0&&green==0){
                    handler.addObject(new Block(xx*32,yy*32,ID.Block,ss));
                }
                if (red==255&&green==255){
                    handler.addObject(new Page(xx*32,yy*32,ID.Page,this,ss));
                }
                if(blue==255 && green==0&&red==0){
                        handler.addObject(new Player(xx*32,yy*32,ID.Player,handler,this,ss));

                }

                if(green==255 && blue==0&&red==0){
                        handler.addObject(new Enemy(xx*32,yy*32,ID.Enemy,handler,this,ss));
                }
                if (green==255 && blue ==255){
                    handler.addObject(new Crate(xx*32,yy*32,ID.Crate,ss));
                }
                if(blue==255 &&red==255){
                    handler.addObject(new Wine(xx*32,yy*32,ID.Wine,this,ss));
                }
                }

            }
        }



    public static  void main(String args[])throws Exception{
        new Game();






    }


}