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

        //blur using averaging algorithm
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                g.setColor(new Color(0x00FFFFFF - IMG.getRGB(x,y)));
                g.drawLine(x, y, x, y);
            }
        }
    }


}
