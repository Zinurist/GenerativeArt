package algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import image.Color;

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
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = new LinkedList<>();

        JLabel lblNum = new JLabel(numOfCircles + " circles");
        JSlider sliderNum = new JSlider(2,300,numOfCircles);
        sliderNum.addChangeListener(l -> {
            numOfCircles = sliderNum.getValue();
            lblNum.setText(numOfCircles + " circles");
        });
        list.add(sliderNum);
        list.add(lblNum);

        JLabel lblRadius = new JLabel(circleRadius + " px radius");
        JSlider sliderRadius = new JSlider(4,300,circleRadius);
        sliderRadius.addChangeListener(l -> {
            circleRadius = sliderRadius.getValue();
            lblRadius.setText(circleRadius + " px radius");
        });
        list.add(sliderRadius);
        list.add(lblRadius);

        JLabel lblSize = new JLabel((int)r + " px size");
        JSlider sliderSize = new JSlider(10,1000,(int)r);
        sliderSize.addChangeListener(l -> {
            r = sliderSize.getValue();
            lblSize.setText((int)r + " px size");
        });
        list.add(sliderSize);
        list.add(lblSize);


        JCheckBox cbLoad = new JCheckBox("load ani");
        cbLoad.addChangeListener(l -> {
            loadAnimation = cbLoad.isSelected();
        });
        list.add(cbLoad);
        cbLoad.setSelected(loadAnimation);

        return list;
    }

}
