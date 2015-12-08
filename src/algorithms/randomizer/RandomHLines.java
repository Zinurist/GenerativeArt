package algorithms.randomizer;

import java.awt.*;

public class RandomHLines extends Randomizer {
    @Override
    public void step(Graphics g, int width, int height) {
        for(int row=0;row<height;row++){
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            if(r.nextBoolean()){
                g.drawLine(0, row, width, row);
            }
        }
    }

    @Override
    public String toString() {
        return "HLines";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
