package algorithms.imgeffects;

import javax.swing.*;
import java.awt.*;

public class LogEffect extends ImageEffect {

    private JCheckBox mirrored;

    public LogEffect(){
        super();
        mirrored = new JCheckBox("mirrored log");
    }

    @Override
    public String toString(){
        return "Log effect";
    }

    @Override
    public void step() {
        Graphics g = IMG.getGraphics();
        Color c;

        if(mirrored.isSelected()){//rotated by 180° in [1;2]
            for(int y=0; y<height; y++){
                for(int x=0; x<width; x++){
                    c = new Color(IMG.getRGB(x, y));
                    g.setColor(new Color(1.0f+(float)(-Math.log(2 - c.getRed()/255.0) / Math.log(2)), 1.0f+(float)(-Math.log(2 - c.getGreen()/255.0) / Math.log(2)), 1.0f+(float)(-Math.log(2 - c.getBlue()/255.0) / Math.log(2)) ));
                    g.drawLine(x, y, x, y);
                }
            }
        }else{
            for(int y=0; y<height; y++){
                for(int x=0; x<width; x++){
                    c = new Color(IMG.getRGB(x, y));
                    g.setColor(new Color((float)(Math.log(c.getRed()/255.0 + 1) / Math.log(2)), (float)(Math.log(c.getGreen() / 255.0 + 1) / Math.log(2)), (float)(Math.log(c.getBlue() / 255.0 + 1) / Math.log(2)) ));
                    g.drawLine(x, y, x, y);
                }
            }
        }
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();
        list.add(mirrored);
        return list;
    }

}
