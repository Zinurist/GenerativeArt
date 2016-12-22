package algorithms.imgeffects;

import image.Color;

public class SemiInvert extends ImageEffect {


    @Override
    public String toString() {
        return "Semi invert";
    }

    @Override
    public void step() {
        Color c;
        int red, green, blue;
        //blur using averaging algorithm
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                c =  new Color(IMG.getRGB(x,y));

                red = c.getRed();
                red = red >= 128 ? 255 - red : red ;
                red = red*2;
                green = c.getGreen();
                green = green >= 128 ? 255 - green : green ;
                green = green*2;
                blue = c.getBlue();
                blue = blue >= 128 ? 255 - blue : blue ;
                blue = blue*2;

                IMG.setColor(new Color(red, green, blue));
                IMG.drawLine(x, y, x, y);
            }
        }
    }

    @Override
    public void reset() {

    }


}
