package algorithms.randomizer;

import javax.swing.*;
import java.awt.*;

public class RandomBigCircles extends Randomizer{

    private int num = 10;

    @Override
    public void step(Graphics g, int width, int height) {

        g.setXORMode(Color.WHITE);

        int x, y, rad;
        int avg = width+height/2;

        for(int i=0; i<num; i++){
            rad = r.nextInt(avg/2)+avg/4;
            x = r.nextInt(width) - rad;
            y = r.nextInt(height) - rad;
            if(color){
                g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            }
            g.fillOval(x, y, rad*2, rad*2);
        }
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();

        JLabel lblNum = new JLabel("#circles: "+num);

        JSlider slNum = new JSlider(1, 100, num);
        slNum.addChangeListener(l->{num = slNum.getValue(); lblNum.setText("#circles: "+num);});

        list.add(lblNum);
        list.add(slNum);
        return list;
    }

    @Override
    public String toString() {
        return "Big circles";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
