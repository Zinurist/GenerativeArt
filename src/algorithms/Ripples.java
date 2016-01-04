package algorithms;


import algorithms.Algorithm;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Ripples extends Algorithm {

    private Color c1, c2;
    private List<Integer[]> circles;
    private int thresholdEnd = IMG.getWidth()/4;
    private int thresholdStart = IMG.getWidth()/8;

    private static int MAX_CIRCLES = 5;

    public Ripples(){
        c1 = Color.BLACK;
        c2 = Color.WHITE;

        circles = new ArrayList<>(MAX_CIRCLES);
    }

    @Override
    public String toString() {
        return "Ripples";
    }

    @Override
    public void step() {
        Graphics g = IMG.createGraphics();
        g.setColor(c1);
        g.fillRect(0,0,IMG.getWidth(),IMG.getHeight());

        Integer[] c;
        if(circles.size() < MAX_CIRCLES){
            Random r = new Random();
            if(r.nextDouble() > 0.995){
                c =  new Integer[3];
                c[0] = r.nextInt(IMG.getWidth());
                c[1] = r.nextInt(IMG.getHeight());
                c[2] = 0;
                circles.add(c);
            }
        }

        for(int i=0; i<circles.size(); i++){
            c = circles.get(i);
            c[2]++;
            if(c[2] > thresholdEnd){
                circles.remove(i);
                i--;
            }else if(c[2] > thresholdStart){
                //fade out by changing color
                int original = c2.getBlue();
                int vec = c1.getBlue() - original;
                double progress = c[2]-thresholdStart;
                double diff = thresholdEnd-thresholdStart;
                vec = (int) (vec*(progress/diff));
                vec = original+vec;
                g.setColor(new Color(vec,vec,vec));
            }else{
                g.setColor(c2);
            }
            g.drawOval(c[0]-c[2],c[1]-c[2],c[2]+c[2],c[2]+c[2]);
        }
    }

    @Override
    public void reset() {
        circles = new ArrayList<>(MAX_CIRCLES);
    }

    @Override
    public java.util.List<Component> getOptionList(){

        JCheckBox color = new JCheckBox("negative");
        color.setSelected(false);
        color.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(color.isSelected()){
                    c1 = Color.WHITE;
                    c2 = Color.BLACK;
                }else{
                    c1 = Color.BLACK;
                    c2 = Color.WHITE;
                }
            }
        });

        java.util.List<Component> list = new LinkedList<Component>();
        list.add(color);
        return list;
    }
}
