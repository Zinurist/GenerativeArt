package algorithms;

import java.awt.*;

public class SimpleHLines extends Algorithm {
    @Override
    public String toString() {
        return "Simple HLines";
    }

    @Override
    public void step() {
        Graphics g = IMG.createGraphics();
        g.setColor(Color.BLACK);
        for(int i=0; i<IMG.getHeight();){
            g.fillRect(0,i,IMG.getWidth(),5);
            i+=10;
        }
    }

    @Override
    public void reset() {

    }
}
