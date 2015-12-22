package algorithms.randomizer;

import java.awt.*;

public class RandomFadingPixels extends Randomizer {
    @Override
    public void step(Graphics g, int width, int height) {
        for(int row=0;row<height;row++){
            for(int column=0;column<width;column++){
                int rate=width-column;
                bools[row][column]=r.nextInt(width*2)<rate;
                if(bools[row][column]){
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
        return "Fading Pixels";
    }
}
