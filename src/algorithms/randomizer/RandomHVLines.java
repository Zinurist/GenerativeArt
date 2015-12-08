package algorithms.randomizer;

import java.awt.*;

public class RandomHVLines extends Randomizer {
    @Override
    public void step(Graphics g, int width, int height) {
        for(int row=0;row<height;row++){
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            if(r.nextDouble() >0.7){
                g.drawLine(0, row, width, row);
            }
        }
        for(int column=0;column<width;column++){
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            if(r.nextDouble() >0.7){
                g.drawLine(column, 0, column, height);
            }
        }
    }

    @Override
    public String toString() {
        return "HVLines";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
