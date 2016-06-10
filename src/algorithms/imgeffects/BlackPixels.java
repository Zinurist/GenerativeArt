package algorithms.imgeffects;

import java.awt.*;

public class BlackPixels extends ImageEffect {

    private boolean even = false;

    @Override
    public String toString() {
        return "Black pixels";
    }

    @Override
    public void step() {
        reset();
        Graphics g = IMG.getGraphics();

        int n = even?0:1;

        g.setColor(Color.BLACK);
        //blur using averaging algorithm
        for(int y=0; y<height; y++){
            for(int x=(y%2==n? 0:1); x<width; x+=2){
                g.drawLine(x, y, x, y);
            }
        }

        even = !even;
    }

}