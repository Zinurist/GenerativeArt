package algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import image.Color;
import option.OptionList;

public class YouTubeLoad extends Algorithm{

    private int tick, middleX, middleY;
    //as options:
    private int numOfCircles = 8;
    private int circleRadius = 12;
    private double r = 20.0;
    private boolean loadAnimation = true;

    public YouTubeLoad(){
        super();
        reset();
    }

    @Override
    public String toString() {
        return "YouTube Loading";
    }

    @Override
    public void step() {
        IMG.setColor(Color.BLACK);
        IMG.fillRect(0, 0, IMG.getWidth(), IMG.getHeight());
        IMG.setColor(Color.WHITE);

        double phi;
        double step = Math.PI*2/numOfCircles;
        int x,y,ani;
        for(int i=0; i<numOfCircles; i++){
            if(loadAnimation){
                ani = (i-tick + numOfCircles)%numOfCircles;
                IMG.setColor(new Color(55 + (200*ani)/numOfCircles, 55 + (200*ani)/numOfCircles, 55 + (200*ani)/numOfCircles));
            }

            phi = step * i;
            x = (int)Math.round(r*Math.cos(phi)) + middleX;
            y = (int)Math.round(r*Math.sin(phi)) + middleY;

            IMG.fillOval(x - circleRadius/2, y - circleRadius/2, circleRadius, circleRadius);
        }

        tick++;
        if(tick >= numOfCircles) tick = 0;
    }

    @Override
    public void reset() {
        tick = 0;
        middleX = IMG.getWidth()/2;
        middleY = IMG.getHeight()/2;
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();

        list.addOption("#circles", numOfCircles, 2, 300, val -> numOfCircles = val);
        list.addOption("radius (in px)", circleRadius, 4, 300, val -> circleRadius = val);
        list.addOption("size (in px)", (int)r, 10, 1000, val -> r = val);
        list.addOption("load ani", loadAnimation, val -> loadAnimation = val);

        return list;
    }

}
