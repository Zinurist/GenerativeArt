package algorithms.randomizer;

import java.awt.*;

public class RandomVLines extends Randomizer {
    @Override
    public void step(Graphics g, int width, int height) {
        for(int column=0;column<width;column++){
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            if(r.nextBoolean()){
                g.drawLine(column, 0, column, height);
            }
        }
    }

    @Override
    public String toString() {
        return "VLines";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
