package algorithms.randomizer;

import image.Color;

public class RandomConnectedRifts extends Randomizer{

    @Override
    public void step( int width, int height) {
        for(int row=0;row<height;row++){
            if(color){
                IMG.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            for(int column=0;column<width;column++){
                int rate=5;//01234||56789
                if(row>0){
                    rate+=(bools[row-1][column]?2:-2);
                }
                if(column>0){
                    rate+=(bools[row][column-1]?2:-2);
                }
                bools[row][column]=r.nextInt(10)<rate;
                if(bools[row][column]){
                    IMG.drawLine(column, row, column, row);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Connected rifts";
    }

    @Override
    public void reset() {
        resetBools();
    }
}
