package algorithms.imgeffects;

import java.awt.*;

public class ImageInvert extends ImageEffect {


    @Override
    public String toString() {
        return "Image invert";
    }

    @Override
    public void step() {
        Graphics g = IMG.getGraphics();
        int avgr, avgb, avgg, count;
        Color c;

        //blur using averaging algorithm
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                g.setColor(new Color(0x00FFFFFF - original.getRGB(x,y)));
                g.fillRect(x, y, x, y);
            }
        }
    }


}
