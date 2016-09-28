package algorithms.imgeffects;

import javax.swing.*;
import java.awt.*;

public class SineEffect extends ImageEffect {

    private JCheckBox cosine;

    public SineEffect(){
        super();
        cosine = new JCheckBox("use cosine", false);
    }

    @Override
    public String toString(){
        return "Sine effect";
    }

    @Override
    public void step(Graphics g) {
        Color c;

        if(cosine.isSelected()){
            for(int y=0; y<height; y++){
                for(int x=0; x<width; x++){
                    c = new Color(IMG.getRGB(x, y));
                    g.setColor(new Color((float)(0.5*Math.cos(c.getRed()*2*Math.PI/255) + 0.5), (float)(0.5*Math.cos(c.getGreen()*2*Math.PI/255) +0.5), (float)(0.5*Math.cos(c.getBlue()*2*Math.PI/255) +0.5)));
                    g.drawLine(x, y, x, y);
                }
            }
        }else{
            for(int y=0; y<height; y++){
                for(int x=0; x<width; x++){
                    c = new Color(IMG.getRGB(x, y));
                    g.setColor(new Color((float)(0.5*Math.sin(c.getRed()*2*Math.PI/255) + 0.5), (float)(0.5*Math.sin(c.getGreen()*2*Math.PI/255) +0.5), (float)(0.5*Math.sin(c.getBlue()*2*Math.PI/255) +0.5)));
                    g.drawLine(x, y, x, y);
                }
            }
        }

    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();
        list.add(cosine);
        return list;
    }

}
