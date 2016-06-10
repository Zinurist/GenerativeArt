package algorithms.imgeffects;

import java.awt.*;

public class ExponentialEffect extends ImageEffect {

    @Override
    public String toString(){
        return "Exp effect";
    }

    @Override
    public void step() {
        Graphics g = IMG.getGraphics();
        Color c;

        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                c = new Color(IMG.getRGB(x, y));
                g.setColor(new Color((float)((Math.exp(c.getRed())-1)/Math.exp(255)), (float)(Math.exp((c.getGreen())-1)/Math.exp(255)), (float)(Math.exp((c.getBlue())-1)/Math.exp(255))));
                g.drawLine(x, y, x, y);
            }
        }
    }


}
