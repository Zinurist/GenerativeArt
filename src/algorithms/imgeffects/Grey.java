package algorithms.imgeffects;

import image.Color;

public class Grey extends ImageEffect {

    @Override
    public String toString() {
        return "Grey image";
    }

    @Override
    public void step() {
        int val;
        Color c;

        //blur using averaging algorithm
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                c = new Color(IMG.getRGB(x, y));
                val = (c.getRed() + c.getBlue() + c.getGreen()) / 3;

                IMG.setColor(new Color(val,val,val));
                IMG.drawLine(x, y, x, y);
            }
        }

    }

    @Override
    public void reset() {

    }

}
