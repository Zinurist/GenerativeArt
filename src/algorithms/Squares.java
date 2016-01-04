package algorithms;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.awt.*;

public class Squares extends Algorithm {


    JCheckBox circles;

    public Squares(){
        circles = new JCheckBox("circles");
        circles.setSelected(false);
    }

    @Override
    public String toString() {
        return "Squares";
    }

    @Override
    public java.util.List<Component> getOptionList(){
        List<Component> list = new LinkedList<Component>();
        list.add(circles);
        return list;
    }

    @Override
    public void step() {
        int width=1;
        int drawn=0;
        int xStart = IMG.getWidth()/2;
        int yStart = IMG.getHeight()/2;
        Graphics g = IMG.createGraphics();
        g.setColor(Color.BLACK);
        while(drawn<IMG.getWidth()){
            if(circles.isSelected()) {
                for (int i = 0; i < width && drawn < IMG.getWidth(); i++) {
                    g.drawOval(xStart - drawn, yStart - drawn, drawn * 2, drawn * 2);
                    drawn++;
                }
            }else{
                for (int i = 0; i < width && drawn < IMG.getWidth(); i++) {
                    g.drawRect(xStart - drawn, yStart - drawn, drawn * 2, drawn * 2);
                    drawn++;
                }
            }
            width++;
            drawn+=width;
            width++;
        }
    }

    @Override
    public void reset() {
        //nuffin
    }
}
