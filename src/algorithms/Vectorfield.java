package algorithms;

import java.awt.*;
import java.util.Random;

public class Vectorfield extends Algorithm{

    private static final boolean MOUSE_INTERACTION = true;

    private static final int VEC_DISTANCE = 16;
    private static final int VEC_LENGTH = 8;
    private static final int NUM_FLYERS = 15;
    private static final double VEC_STRENGTH = 1.0/8.0;
    private static final int POINT_SIZE = 4;
    private static final double MINIMUM_R = 1.0;
    private static final double DECAY_WEIGHT = 1.0/2.0;

    double[][][] field;
    double[][] flyers;

    public Vectorfield(){
        reset();
    }

    private static int getNearestPoint(double x, int upperBound){
        int x0;
        x0 = (int) Math.round(x);
        int difx = x0%VEC_DISTANCE;
        x0 = x0/VEC_DISTANCE;
        if(difx > VEC_DISTANCE/2){
            x0++;
        }
        if(x0 >= upperBound){
            x0 = 0;
        }
        return x0;
    }

    private void updateMouse(){
        //TODO
        //MouseInfo.getPointerInfo().getLocation()
    }

    private void updateField(){
        //TODO
    }

    private void calc(){
        for(int i = 0; i<NUM_FLYERS; i++){
            flyers[i][0] += flyers[i][2];
            flyers[i][1] += flyers[i][3];

            //wall
            while(flyers[i][0]>IMG.getWidth()){
                flyers[i][0] = flyers[i][0]-IMG.getWidth();
            }
            while(flyers[i][0]<0){
                flyers[i][0] = flyers[i][0]+IMG.getWidth();
            }

            while(flyers[i][1]>IMG.getHeight()){
                flyers[i][1] = flyers[i][1]-IMG.getHeight();
            }
            while(flyers[i][1]<0){
                flyers[i][1] = flyers[i][1]+IMG.getHeight();
            }

            //choose closest vector
            flyers[i][2] = flyers[i][2]*DECAY_WEIGHT;
            flyers[i][3] = flyers[i][3]*DECAY_WEIGHT;

            int x = getNearestPoint(flyers[i][0],field[0].length);
            int y = getNearestPoint(flyers[i][1],field.length);
            flyers[i][2] += field[y][x][0]*VEC_STRENGTH*(1.0/DECAY_WEIGHT);
            flyers[i][3] += field[y][x][1]*VEC_STRENGTH*(1.0/DECAY_WEIGHT);

            /*use all nearby vectors using the distance as weight
            int x0,y0;
            double difx,dify;
            double r;//distance, but squared
            x0 = ((int) (flyers[i][0])/VEC_DISTANCE);
            y0 = ((int) (flyers[i][1])/VEC_DISTANCE);
            for(int x = x0; x<x0+2; x++){
                for(int y = y0; y<y0+2; y++) {
                    try {
                        difx = flyers[i][0] - x * VEC_DISTANCE;
                        dify = flyers[i][1] - y * VEC_DISTANCE;
                        r = difx * difx + dify * dify;
                        if (r < MINIMUM_R) r = MINIMUM_R;
                        flyers[i][2] += VEC_STRENGTH * field[y][x][0] / r;
                        flyers[i][3] += VEC_STRENGTH * field[y][x][1] / r;
                    }catch(Exception e){}//out of bounds, just ignore
                }
            }
            */
        }
    }

    private void draw(){
        emptyIMG();

        Graphics g = IMG.createGraphics();
        g.setColor(Color.BLACK);

        for(int y = 0; y<field.length; y++){
            for(int x = 0; x<field[y].length; x++){
                g.drawLine(VEC_DISTANCE*x,VEC_DISTANCE*y,VEC_DISTANCE*x+(int)field[y][x][0],VEC_DISTANCE*y+(int)field[y][x][1]);
            }
        }

        g.setColor(Color.RED);
        for(int i = 0; i<NUM_FLYERS; i++){
            //g.drawLine((int)Math.round(flyers[i][0]),(int)Math.round(flyers[i][1]),(int)Math.round(flyers[i][0]-flyers[i][2]),(int)Math.round(flyers[i][1]-flyers[i][3]));
            g.fillOval((int)Math.round(flyers[i][0]-POINT_SIZE/2.0),(int)Math.round(flyers[i][1]-POINT_SIZE/2.0),POINT_SIZE,POINT_SIZE);
        }
    }

    @Override
    public String toString() {
        return "Vectorfield";
    }

    @Override
    public void step() {
        if(MOUSE_INTERACTION){
            updateMouse();
            updateField();
        }
        calc();
        draw();
    }

    @Override
    public void reset() {
        Random r = new Random();

        int width = IMG.getWidth()/VEC_DISTANCE;
        int height = IMG.getHeight()/VEC_DISTANCE;
        field = new double[height][width][2];
        double x_v,y_v;

        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                x_v = (r.nextDouble()*2.0-1.0)*VEC_LENGTH;
                y_v = Math.sqrt(VEC_LENGTH*VEC_LENGTH - x_v*x_v);
                if(r.nextBoolean()) y_v*=-1;
                field[y][x][0] = x_v;
                field[y][x][1] = y_v;
            }
        }

        flyers = new double[NUM_FLYERS][4];
        for(int i = 0; i<NUM_FLYERS; i++){
            flyers[i][0] = r.nextInt(IMG.getWidth()-40)+20;
            flyers[i][1] = r.nextInt(IMG.getHeight()-40)+20;
            flyers[i][2] = 0;
            flyers[i][3] = 0;
        }
    }


}
