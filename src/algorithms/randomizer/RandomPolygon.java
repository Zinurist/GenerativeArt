package algorithms.randomizer;

import javax.swing.*;
import java.awt.*;

public class RandomPolygon extends Randomizer {

    JSpinner num = new JSpinner(new SpinnerNumberModel(30, 0, 100, 15));

    @Override
    public void step(Graphics g, int width, int height) {
        int num1 = (Integer) num.getValue();
        num1 = num1==0? r.nextInt(40)+10 : num1;
        int[] xPoints = new int[num1];
        int[] yPoints = new int [num1];
        for(int i=0; i<num1; i++){
            xPoints[i]=r.nextInt(width);
            yPoints[i]=r.nextInt(height);
        }
        if(color){
            g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            g.fillPolygon(xPoints, yPoints, num1);
            g.setColor(Color.BLACK);
        }
        g.drawPolygon(xPoints, yPoints, num1);
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();
        list.add(num);
        return list;
    }

    @Override
    public String toString() {
        return "Polygon";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
