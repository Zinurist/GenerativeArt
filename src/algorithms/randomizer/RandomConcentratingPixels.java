package algorithms.randomizer;

import java.awt.*;

public class RandomConcentratingPixels extends Randomizer{

    @Override
    public void step(Graphics g, int width, int height) {
        for(int row=0;row<height;row++){
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            for(int column=0;column<width;column++){
                int rate=width;
                for(int column2=0;column2<column;column2++){
                    rate+=bools[row][column2]?5:-5;
                }
                bools[row][column]=r.nextInt(width*2)<rate;
                if(bools[row][column]){
                    g.drawLine(column, row, column, row);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Concentrating Pixels";
    }
}
