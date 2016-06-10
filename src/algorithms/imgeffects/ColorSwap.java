package algorithms.imgeffects;

import java.awt.*;

public class ColorSwap extends ImageEffect {

    @Override
    public String toString() {
        return "Color swap";
    }

    @Override
    public void step() {
        Graphics g = IMG.getGraphics();
        Color c;

        //blur using averaging algorithm
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                c = new Color(IMG.getRGB(x, y));
                g.setColor(new Color(c.getBlue(),c.getRed(),c.getGreen()));
                g.drawLine(x, y, x, y);
            }
        }
    }

}
