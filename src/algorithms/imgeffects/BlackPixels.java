package algorithms.imgeffects;

import java.awt.*;

public class BlackPixels extends ImageEffect {

    @Override
    public String toString() {
        return "Black pixels";
    }

    @Override
    public void step() {
        Graphics g = IMG.getGraphics();

        g.setColor(Color.BLACK);
        //blur using averaging algorithm
        for(int y=0; y<IMG.getHeight(); y+=2){
            for(int x=(y%2==0? 0:1); x<IMG.getWidth(); x+=2){
                g.drawLine(x, y, x, y);
            }
        }
    }

}