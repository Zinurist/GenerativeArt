package algorithms.randomizer;

import java.awt.*;

public class RandomPixelBlocks extends Randomizer {

    boolean[][] done;

    @Override
    public void step(Graphics g, int width, int height) {
        for(int row=0;row<height;row++){
            for(int column=0;column<width;column++){
                if(!done[row][column]){
                    int sizeW=r.nextInt(width/4);
                    int sizeH=r.nextInt(height/4);
                    if(sizeW+column>width){
                        sizeW=width-column;
                    }
                    if(sizeH+row>height){
                        sizeH=height-row;
                    }
                    boolean col=r.nextBoolean();
                    if(color){
                        g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
                    }
                    for(int h=0; h<sizeH;h++){
                        for(int w=0; w<sizeW;w++){
                            if(!done[row+h][column+w]){
                                if(col){

                                    g.drawLine(column+w, row+h, column+w, row+h);
                                }
                                done[row+h][column+w]=true;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Pixel Blocks";
    }

    @Override
    public void reset() {
        resetBools();
        done = new boolean[IMG.getHeight()][IMG.getWidth()];
    }
}
