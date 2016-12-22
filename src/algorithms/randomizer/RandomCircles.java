package algorithms.randomizer;

import javax.swing.*;
import java.awt.*;
import image.Color;

public class RandomCircles extends Randomizer{

    JSpinner num = new JSpinner(new SpinnerNumberModel(30, 0, 1000, 10));

    @Override
    public void step(int width, int height) {
        int numTmp = (Integer) num.getValue();
        int num1, num2, num3;
        for(int i=0; i<numTmp; i++){
            num1=r.nextInt(50)+50;
            num2=r.nextInt(width)-(num1/2);
            num3=r.nextInt(height)-(num1/2);
            if(color){
                IMG.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                IMG.fillOval(num2,num3, num1, num1);
                IMG.setColor(Color.BLACK);
            }
            IMG.drawOval(num2, num3, num1, num1);
        }
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();
        list.add(num);
        return list;
    }

    @Override
    public String toString() {
        return "Circles";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
