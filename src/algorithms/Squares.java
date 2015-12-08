package algorithms;

import java.awt.*;

public class Squares extends Algorithm {
    @Override
    public String toString() {
        return "Squares";
    }

    @Override
    public void step() {
        int width=1;
        int drawn=0;
        int xStart = IMG.getWidth()/2;
        int yStart = IMG.getHeight()/2;
        Graphics g = IMG.createGraphics();
        g.setColor(Color.BLACK);
        while(drawn<IMG.getWidth()){
            for(int i=0; i<width && drawn<IMG.getWidth(); i++){
                g.drawRect(xStart-drawn, yStart-drawn,drawn*2,drawn*2);
                drawn++;
            }
            width++;
            drawn+=width;
            width++;
        }
    }

    @Override
    public void reset() {
        //nuffin
    }
}
