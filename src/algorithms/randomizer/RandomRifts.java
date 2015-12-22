package algorithms.randomizer;

import java.awt.*;

public class RandomRifts extends Randomizer{

    @Override
    public void step(Graphics g, int width, int height) {
        for(int row=0;row<height;row++){
            for(int column=0;column<width;column++){
                int rate=5;//01234||56789
                if(row>0){
                    rate+=(bools[row-1][column]?2:-2);
                }
                if(column>0){
                    rate+=(bools[row][column-1]?2:-2);
                }
                if(row>4){
                    rate+=(bools[row-5][column]?1:-1);
                }
                if(column>4){
                    rate+=(bools[row][column-2]?1:-1);
                }
                bools[row][column]=r.nextInt(10)<rate;
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
        return "Rifts";
    }
}
