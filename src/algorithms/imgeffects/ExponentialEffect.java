package algorithms.imgeffects;

import java.awt.*;

public class ExponentialEffect extends ImageEffect {

    @Override
    public String toString(){
        return "Exp effect";
    }

    @Override
    public void step(Graphics g) {
        Color c;

        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                c = new Color(IMG.getRGB(x, y));
                g.setColor(new Color((float)(Math.exp(c.getRed()/255.0) / Math.exp(2)), (float)(Math.exp(c.getGreen()/255.0) / Math.exp(2)), (float)(Math.exp(c.getBlue()/255.0) / Math.exp(2)) ));
                g.drawLine(x, y, x, y);
            }
        }
    }

    @Override
    public void reset() {

    }


}
