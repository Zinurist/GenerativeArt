package algorithms.randomizer;

import image.Color;

public class RandomPixels extends Randomizer{

    @Override
    public void step(int width, int height) {
        for(int row=0;row<height;row++){
            if(color){
                IMG.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            for(int column=0;column<width;column++){
                if(r.nextBoolean()){
                    IMG.drawLine(column, row, column, row);
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
