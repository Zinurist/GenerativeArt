package algorithms;


import algorithms.randomizer.Randomizer;
import gui.MainFrame;

import java.awt.*;
import java.util.Random;
import image.Color;

public class Balls extends Algorithm{

    private class Ball{
        double x, y;
        double xOld, yOld;
        double r;
        Color c;

        int x(){
            return (int)(x-r+0.5);
        }
        int y(){
            return (int)(y-r+0.5);
        }
        int r(){
            return (int)(r+0.5);
        }
        int width(){
            return (int)(r*2+0.5);
        }

        void readjust(){
            if(x < r){
                xOld = x;
                x = r;
            }else if(x+r > IMG.getWidth()){
                xOld = x;
                x = IMG.getWidth() - r;
            }

            if(y < r){
                yOld = y;
                y = r;
            }else if(y+r > IMG.getHeight()){
                yOld = y;
                y = IMG.getHeight() - r;
            }
        }
    }

    private int wx, wy, wvy, wvx;
    private int num;
    private boolean started;

    private Ball[] balls;

    public Balls() {
        num = 5;
        balls = new Ball[num];

        reset();
    }

    public boolean adjustBalls(){
        double dis, deltaX, deltaY;
        boolean collision = false;
        for(int b1 = 0; b1<num; b1++){
            for(int b2 = b1+1; b2<num; b2++){
                deltaX = balls[b1].x - balls[b2].x;
                deltaY = balls[b1].y - balls[b2].y;
                dis = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
                dis = dis - (balls[b1].r + balls[b2].r);

                if(dis < 0){//collision
                    collision = true;
                    deltaX = deltaX / (Math.abs(deltaX) + Math.abs(deltaY));
                    deltaY = deltaY / (Math.abs(deltaX) + Math.abs(deltaY));
                    //dis is negative!
                    balls[b1].x = -1 * dis * deltaX + balls[b1].x;
                    balls[b1].y = -1 * dis * deltaY + balls[b1].y;
                    balls[b2].x =  1 * dis * deltaX + balls[b2].x;
                    balls[b2].y =  1 * dis * deltaY + balls[b2].y;

                    //balls[b1].readjust();
                    //balls[b2].readjust();
                }
            }
        }

        return collision;
    }

    @Override
    public String toString() {
        return "Balls";
    }


    @Override
    public void step() {

        Point w;
        if(!started){
            w = MainFrame.MF.getPanelLocation();
            wx = w.x;
            wy = w.y;
            started = true;
        }else {
            w = MainFrame.MF.getPanelLocation();
            wvx = w.x - wx;
            wvy = w.y - wy;
            wx = w.x;
            wy = w.y;
        }


        Ball b;
        double vx,vy;//update positions
        for(int i=0; i<num; i++){
            b = balls[i];

            b.xOld -= wvx;
            b.yOld -= wvy;
            b.x -= wvx;
            b.y -= wvy;

            vx = b.x - b.xOld;
            vy = b.y - b.yOld;

            vx = b.x + vx;
            vy = b.y + vy;
            b.xOld = b.x;
            b.yOld = b.y;
            b.x = vx;
            b.y = vy;


            //b.readjust();
        }

        for(int i=0; i<10 && adjustBalls(); i++){}
        for(int i=0; i<num; i++){balls[i].readjust();}


        emptyIMG();
        for (int i = 0; i < num; i++) {
            b = balls[i];
            IMG.setColor(b.c);
            IMG.fillOval(b.x(), b.y(), b.width(), b.width());
        }
    }

    @Override
    public void reset() {
        if(num != balls.length) balls = new Ball[num];

        Random r = new Random();
        int radTemp;
        for(int i = 0; i<num; i++){
            balls[i] = new Ball();

            balls[i].c = Randomizer.randomColor(true);
            radTemp = r.nextInt(150) + 10;
            balls[i].r = radTemp;
            balls[i].x = r.nextInt(IMG.getWidth() - 2*radTemp) + radTemp;
            balls[i].y = r.nextInt(IMG.getHeight() - 2*radTemp) + radTemp;
        }

        //adjust ball position for at least 20 tries
        for(int i=0; i<10 && adjustBalls(); i++){}


        for(int i = 0; i<num; i++){
            balls[i].xOld = balls[i].x;
            balls[i].yOld = balls[i].y;
        }

        started = false;
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = new java.util.LinkedList<>();
        return list;
    }
}
