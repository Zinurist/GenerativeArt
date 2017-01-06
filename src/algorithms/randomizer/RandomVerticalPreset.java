package algorithms.randomizer;

import image.Color;

public class RandomVerticalPreset extends Randomizer {
    @Override
    public void step(int width, int height) {
        for(int column=0;column<width;column+=2){
            if(color){
                IMG.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            }
            IMG.drawLine(column, 0, column, height);
        }
        for(int row=0;row<height;row++){
            for(int column=1;column<width;column+=2){
                if(r.nextBoolean()){
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
        return "Vertical Preset";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
