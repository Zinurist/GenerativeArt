package algorithms.randomizer;

import javax.swing.*;
import java.awt.*;

public class RandomCircles extends Randomizer{

    JSpinner num = new JSpinner(new SpinnerNumberModel(30, 0, 100, 10));

    @Override
    public void step(Graphics g, int width, int height) {
        int num1 = (Integer) num.getValue();
        num1 = num1==0? r.nextInt(90)+10 : num1;
        int num2, num3;
        for(int i=0; i<num1; i++){
            num1=r.nextInt(50)+50;
            num2=r.nextInt(width)-(num1/2);
            num3=r.nextInt(height)-(num1/2);
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
                g.fillOval(num2,num3, num1, num1);
                g.setColor(Color.BLACK);
            }
            g.drawOval(num2, num3, num1, num1);
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
