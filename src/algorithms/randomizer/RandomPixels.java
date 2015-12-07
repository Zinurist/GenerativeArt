package algorithms.randomizer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RandomPixels extends Randomizer{

    @Override
    public void step(Graphics g, int width, int height) {
        for(int row=0;row<height;row++){
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            for(int column=0;column<width;column++){
                if(r.nextBoolean()){
                    g.drawLine(column, row, column, row);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Random";
    }

    @Override
    public void reset() {
        //nuffin
    }

}
