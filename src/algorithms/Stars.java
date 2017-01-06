package algorithms;

import gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import image.Color;
import option.OptionList;

public class Stars extends Algorithm {

    private int num;
    private double factor;
    private boolean connect;
    private LinkedList<int[]> points;

    public Stars(){
        points = new LinkedList<>();
        num = 100;
        factor = 1.0;
        connect = false;
        reset();
    }

    @Override
    public String toString() {
        return "Stars";
    }

    @Override
    public void step() {
        emptyIMG();

        IMG.setColor(Color.BLACK);
        Point m = MainFrame.MF.getMouseLocation();

        int vx, vy;
        double r;
        for(int[] p : points){
            IMG.fillOval(p[0]-5,p[1]-5,10,10);

            vx = m.x - p[0];
            vy = m.y - p[1];
            r = Math.sqrt(vx*vx + vy*vy);
            r = 1.0 + Math.sqrt(r)/r * factor;

            vx = (int)(vx*r + 0.5);
            vy = (int)(vy*r + 0.5);

            IMG.fillOval(m.x - vx - 5, m.y - vy - 5, 10, 10);

            if(connect){
                IMG.drawLine(m.x - vx, m.y - vy, p[0], p[1]);
            }
        }


    }


    @Override
    public void reset() {
        points.clear();
        Random r = new Random();
        for(int i=0; i<num; i++){
            points.add(new int[]{r.nextInt(IMG.getWidth()), r.nextInt(IMG.getHeight())});
        }
    }



    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();

        list.addOption("#stars", num, 1, 1000, val -> num = val);
        list.addOption("factor", factor, 10., 100., 0.1, val -> factor = val);
        list.addOption("connect", connect, val -> connect = val);

        return list;
    }
}
