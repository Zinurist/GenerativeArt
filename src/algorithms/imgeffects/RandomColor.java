package algorithms.imgeffects;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RandomColor extends ImageEffect {

    private JComboBox<String> box;

    public RandomColor(){
        super();
        box = new JComboBox<>(new String[]{"Blue", "Red", "Green"});
    }

    @Override
    public String toString() {
        return "Random color";
    }

    @Override
    public void step() {
        Graphics g = IMG.getGraphics();
        Color c;
        double factor = new Random().nextDouble()*2;
        //blur using averaging algorithm
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                c = new Color(original.getRGB(x, y));
                switch(box.getSelectedIndex()){
                    case 0:
                        g.setColor(new Color(c.getRed(), c.getGreen(), (int)Math.min(Math.round(c.getBlue() * factor) , 255) ));
                        break;
                    case 1:
                        g.setColor(new Color((int)Math.min(Math.round(c.getRed() * factor) , 255), c.getGreen(), c.getBlue() ));
                        break;
                    case 2:
                        g.setColor(new Color(c.getRed(), (int)Math.min(Math.round(c.getGreen() * factor) , 255), c.getBlue() ));
                        break;
                    default:
                        System.out.println("shouldn't happen");
                        break;
                }
                g.drawLine(x, y, x, y);
            }
        }
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();
        list.add(box);
        return list;
    }

}
