package algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class EndlessCircles extends Algorithm{


    private JSlider r_step_slider;

    public EndlessCircles(){
        r_step_slider = new JSlider(2,300,10);
    }


    @Override
    public String toString() {
        return "Circles";
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

        for(int r=r_step; r<IMG.getWidth(); r+=r_step){
            g.drawOval(x0,y0,r,r);
            g.drawOval(x0,y0-r,r,r);
            g.drawOval(x0-r,y0,r,r);
            g.drawOval(x0-r,y0-r,r,r);
        }
    }

    @Override
    public void reset() {
    }


    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = new LinkedList<Component>();
        list.add(r_step_slider);
        list.add(new JLabel("r_step"));
        return list;
    }
}
