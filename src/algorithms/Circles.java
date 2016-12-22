package algorithms;


import javax.swing.*;
import java.awt.*;
import image.Color;

public class Circles extends Algorithm{

    private boolean circle;
    private int radX, radY;

    public Circles() {
        circle = true;
        radX = 5;
        radY = circle ? radX : 5;
        reset();
    }

    @Override
    public String toString() {
        return "Circles";
    }

    @Override
    public void step() {
        if(isRunning()){
            emptyIMG();
        }

        IMG.setColor(Color.BLACK);

        boolean shifted = false;
        int width = radX*2;
        int height = radY*2;
        int deltaY  = (int)(Math.sqrt(3) * radY + 0.5);
        //first approach for circles:
        //sqrt(rad*rad + deltaY*deltaY) = 2*rad
        //rad*rad + deltaY*deltaY = 4*rad*rad
        //deltaY*deltaY = 3*rad*rad
        //deltaY = sqrt(3)*rad

        //second approach for both ovals and circles:
        //the angle from the middle points of the ovals stays the same, which is 60°
        //->radY*sin(60°) is the change in y to the colliding point
        //->radY*sin(60°) * 2 is the change in y between both middle points
        //radY*sin(60°)*2 = radY*sqrt(3)/2*2 = radY*sqrt(3)

        for(int y = 0; y<IMG.getHeight()+deltaY; y+=deltaY){
            for(int x = shifted ? 0 : radX; x<IMG.getWidth()+width; x+=width){
                IMG.drawOval(x - radX, y - radY, width, height);
            }
            shifted = !shifted;
        }


    }

    @Override
    public void reset() {
        //reset variables
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = new java.util.LinkedList<>();


        JLabel lblWidth = new JLabel("Radius x: "+radX);
        JLabel lblHeight = new JLabel("Radius y: "+radY);

        JSlider slHeight = new JSlider(2, 500, radY);
        slHeight.addChangeListener(l-> {
            radY = slHeight.getValue();
            lblHeight.setText("Radius y: " + radY);
        });
        slHeight.setEnabled(!circle);
        JSlider slWidth = new JSlider(2, 500, radX);
        slWidth.addChangeListener(l-> {
            radX = slWidth.getValue();
            if(circle){
                radY = radX;
                slHeight.setValue(radY);
            }
            lblWidth.setText("Radius x: " + radX);
        });



        JRadioButton rbCircle = new JRadioButton("circle", circle);
        rbCircle.addActionListener(l -> {
            circle = true;
            slHeight.setEnabled(false);
        });
        JRadioButton rbOval = new JRadioButton("oval", !circle);
        rbOval.addActionListener(l -> {
            circle = false;
            slHeight.setEnabled(true);
        });
        ButtonGroup g = new ButtonGroup();
        g.add(rbCircle);
        g.add(rbOval);

        list.add(rbCircle);
        list.add(rbOval);
        list.add(lblWidth);
        list.add(lblHeight);
        list.add(slWidth);
        list.add(slHeight);

        return list;
    }
}
