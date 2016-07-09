package algorithms.randomizer;

import java.awt.*;

public class RandomPixelFog extends Randomizer {

    @Override
    public void step(Graphics g, int width, int height) {
        for(int row=0;row<height;row++){
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            for(int column=0;column<width;column++){
                int rate=5;//01234||56789
                if(row>4){
                    rate+=(bools[row-5][column]?2:-2);
                }
                if(column>4){
                    rate+=(bools[row][column-5]?2:-2);
                }
                bools[row][column]=r.nextInt(10)<rate;
                if(bools[row][column]){
                    g.drawLine(column, row, column, row);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Pixel Fog";
    }

    @Override
    public void reset() {
        resetBools();
    }
}
