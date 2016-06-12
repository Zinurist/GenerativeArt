package algorithms.imgeffects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Grey extends ImageEffect {

    @Override
    public String toString() {
        return "Grey image";
    }

    @Override
    public void step() {
        Graphics g = IMG.getGraphics();
        int val;
        Color c;

        //blur using averaging algorithm
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                c = new Color(IMG.getRGB(x, y));
                val = (c.getRed() + c.getBlue() + c.getGreen()) / 3;

                g.setColor(new Color(val,val,val));
                g.drawLine(x, y, x, y);
            }
        }

    }

}
