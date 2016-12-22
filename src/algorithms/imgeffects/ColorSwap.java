package algorithms.imgeffects;

import image.Color;

public class ColorSwap extends ImageEffect {

    @Override
    public String toString() {
        return "Color swap";
    }

    @Override
    public void step() {
        Color c;

        //blur using averaging algorithm
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                c = new Color(IMG.getRGB(x, y));
                IMG.setColor(new Color(c.getBlue(),c.getRed(),c.getGreen()));
                IMG.drawLine(x, y, x, y);
            }
        }
    }

    @Override
    public void reset() {

    }

}
