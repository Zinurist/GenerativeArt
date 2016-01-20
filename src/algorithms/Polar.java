package algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Polar extends Algorithm{

    private static int POINT_SIZE = 8;

    private JSlider r_step_slider;
    private int t, max_r;

    public Polar(){
        r_step_slider = new JSlider(2,100,30);
        reset();
    }

    @Override
    public String toString() {
        return "Polar";
    }

    @Override
    public void step() {
        emptyIMG();

        int r_step = r_step_slider.getValue();
        Graphics g = IMG.createGraphics();
        g.setColor(Color.BLACK);

        int x0 = IMG.getWidth()/2;
        int y0 = IMG.getHeight()/2;

        g.drawLine(0, y0, IMG.getWidth(), y0);
        g.drawLine(x0, 0, x0, IMG.getHeight());

        for(int r=r_step; r<max_r; r+=r_step){
            g.setColor(Color.BLACK);
            g.drawOval(x0 - r, y0 - r, 2*r, 2*r);
            g.setColor(Color.BLUE);
            g.fillOval(x0+(int)Math.round(r*Math.cos(t * r /2000.0)) - POINT_SIZE/2,y0+(int)Math.round(r*Math.sin(t * r /2000.0)) - POINT_SIZE/2,POINT_SIZE,POINT_SIZE);
        }

        t++;
    }

    @Override
    public void reset() {
        t = 0;
        max_r = (int)Math.round(Math.sqrt(IMG.getHeight()*IMG.getHeight() + IMG.getWidth()*IMG.getWidth()));
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = new LinkedList<Component>();
        list.add(r_step_slider);
        list.add(new JLabel("r_step"));
        return list;
    }
}
