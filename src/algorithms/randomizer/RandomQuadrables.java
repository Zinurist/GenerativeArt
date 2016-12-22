package algorithms.randomizer;

import javax.swing.*;
import java.awt.*;
import image.Color;

public class RandomQuadrables extends Randomizer{

    private int num = 5;

    @Override
    public void step(int width, int height) {
        int[] xPoints=new int[4];
        int[] yPoints=new int[4];
        for(int i=0; i<num; i++){
            for(int n=0; n<4; n++){
                if(n<2){
                    xPoints[n]=(r.nextInt(50)+i*50);
                }else{
                    xPoints[n]=width-(r.nextInt(50)+i*50);
                }
                if(n%3==0){
                    yPoints[n]=(r.nextInt(50)+i*50);
                }else{
                    yPoints[n]=height-(r.nextInt(50)+i*50);
                }
            }
            if(color){
                IMG.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
                IMG.fillPolygon(xPoints, yPoints, 4);
                IMG.setColor(Color.BLACK);
            }
            IMG.drawPolygon(xPoints, yPoints, 4);
        }
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();
        JSpinner numSpinner = new JSpinner(new SpinnerNumberModel(num, 0, 100, 5));
        numSpinner.addChangeListener(l->this.num = (int)numSpinner.getValue());
        list.add(numSpinner);
        return list;
    }

    @Override
    public String toString() {
        return "Quadrables";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
