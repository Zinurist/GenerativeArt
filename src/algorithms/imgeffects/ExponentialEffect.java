package algorithms.imgeffects;

import image.Color;

public class ExponentialEffect extends ImageEffect {

    @Override
    public String toString(){
        return "Exp effect";
    }

    @Override
    public void step() {
        Color c;

        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                c = new Color(IMG.getRGB(x, y));
                IMG.setColor(new Color((float)(Math.exp(c.getRed()/255.0) / Math.exp(2)), (float)(Math.exp(c.getGreen()/255.0) / Math.exp(2)), (float)(Math.exp(c.getBlue()/255.0) / Math.exp(2)) ));
                IMG.drawLine(x, y, x, y);
            }
        }
    }

    @Override
    public void reset() {

    }


}
