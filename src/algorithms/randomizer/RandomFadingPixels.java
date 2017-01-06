package algorithms.randomizer;

import image.Color;

public class RandomFadingPixels extends Randomizer {
    @Override
    public void step(int width, int height) {
        for(int row=0;row<height;row++){
            for(int column=0;column<width;column++){
                int rate=width-column;
                bools[row][column]=r.nextInt(width*2)<rate;
                if(bools[row][column]){
                    if(color){
                        IMG.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
                    }
                    IMG.drawLine(column, row, column, row);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Fading Pixels";
    }


    @Override
    public void reset() {
        resetBools();
    }
}
