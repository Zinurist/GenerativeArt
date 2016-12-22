package algorithms;

import gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import image.Color;

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
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();

        JLabel lblNum = new JLabel("#stars: "+num);
        JLabel lblFac = new JLabel("factor: "+factor);

        JSlider slNum = new JSlider(1, 1000, num);
        slNum.addChangeListener(l -> {
            num = slNum.getValue();
            lblNum.setText("#stars: " + num);
        });

        JSlider slFac = new JSlider(10, 100, (int)(factor*10+0.5));
        slFac.addChangeListener(l -> {
            factor = slFac.getValue() / 10.0;
            lblFac.setText("factor: " + factor);
        });

        JCheckBox cbConnect = new JCheckBox("connect", connect);
        cbConnect.addChangeListener(l->connect = cbConnect.isSelected());

        list.add(lblNum);
        list.add(slNum);
        list.add(lblFac);
        list.add(slFac);
        list.add(cbConnect);
        return list;
    }
}
