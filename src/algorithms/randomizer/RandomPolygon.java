package algorithms.randomizer;

import javax.swing.*;
import java.awt.*;
import image.Color;

public class RandomPolygon extends Randomizer {

    private int num = 30;

    @Override
    public void step(int width, int height) {
        int num1 = num;
        num1 = num1==0? r.nextInt(40)+10 : num1;
        int[] xPoints = new int[num1];
        int[] yPoints = new int [num1];
        for(int i=0; i<num1; i++){
            xPoints[i]=r.nextInt(width);
            yPoints[i]=r.nextInt(height);
        }
        if(color){
            IMG.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            IMG.fillPolygon(xPoints, yPoints, num1);
            IMG.setColor(Color.BLACK);
        }
        IMG.drawPolygon(xPoints, yPoints, num1);
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();
        JSpinner numSpinner = new JSpinner(new SpinnerNumberModel(num, 0, 100, 10));
        numSpinner.addChangeListener(l->this.num = (int)numSpinner.getValue());
        list.add(numSpinner);
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
