package algorithms.randomizer;

import java.awt.*;

public class RandomVerticalPreset extends Randomizer {
    @Override
    public void step(Graphics g, int width, int height) {
        for(int column=0;column<width;column+=2){
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            g.drawLine(column, 0, column, height);
        }
        for(int row=0;row<height;row++){
            for(int column=1;column<width;column+=2){
                if(r.nextBoolean()){
                    if(color){
                        g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
                    }
                    g.drawLine(column, row, column, row);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Vertical Preset";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
