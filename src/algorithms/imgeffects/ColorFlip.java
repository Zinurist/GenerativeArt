package algorithms.imgeffects;

import javax.swing.*;
import java.awt.*;
import image.Color;

public class ColorFlip extends ImageEffect {

    private int type = 0;

    @Override
    public String toString() {
        return "Color flip";
    }

    @Override
    public void step() {
        Color c1,c2;

        //blur using averaging algorithm
        int min = height > width ? width:height;
        for(int y=0; y<min; y++){
            for(int x=y; x<min; x++){
                c1 = new Color(IMG.getRGB(x, y));
                c2 = new Color(IMG.getRGB(y, x));
                if(type == 0){

                    IMG.setColor(new Color(c2.getRed(),c1.getGreen(),c1.getBlue()));
                    IMG.drawLine(x, y, x, y);
                    IMG.setColor(new Color(c1.getRed(),c2.getGreen(),c2.getBlue()));
                    IMG.drawLine(y, x, y, x);
                }else if(type == 1){

                    IMG.setColor(new Color(c1.getRed(),c2.getGreen(),c1.getBlue()));
                    IMG.drawLine(x, y, x, y);
                    IMG.setColor(new Color(c2.getRed(),c1.getGreen(),c2.getBlue()));
                    IMG.drawLine(y, x, y, x);
                }else{

                    IMG.setColor(new Color(c1.getRed(),c1.getGreen(),c2.getBlue()));
                    IMG.drawLine(x, y, x, y);
                    IMG.setColor(new Color(c2.getRed(),c2.getGreen(),c1.getBlue()));
                    IMG.drawLine(y, x, y, x);
                }

            }
        }
    }

    @Override
    public void reset() {

    }


    @Override
    public java.util.List<Component> getOptionList() {
        java.util.List<Component> list = super.getOptionList();

        JComboBox<String> cbColor = new JComboBox<>(new String[]{"Red", "Green", "Blue"});
        cbColor.setSelectedIndex(type);
        cbColor.addActionListener(l->type = cbColor.getSelectedIndex());

        list.add(cbColor);
        return list;
    }

}
