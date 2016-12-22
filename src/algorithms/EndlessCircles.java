package algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import image.Color;

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
        IMG.setColor(Color.BLACK);

        int x0 = IMG.getWidth()/2;
        int y0 = IMG.getHeight()/2;

        IMG.drawLine(0, y0, IMG.getWidth(), y0);
        IMG.drawLine(x0, 0, x0, IMG.getHeight());

        for(int r=r_step; r<IMG.getWidth(); r+=r_step){
            IMG.drawOval(x0,y0,r,r);
            IMG.drawOval(x0,y0-r,r,r);
            IMG.drawOval(x0-r,y0,r,r);
            IMG.drawOval(x0-r,y0-r,r,r);
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
