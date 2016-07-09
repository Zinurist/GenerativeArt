package algorithms.randomizer;

import java.awt.*;

public class RandomThinRifts extends Randomizer {

    @Override
    public void step(Graphics g, int width, int height) {
        for(int row=0;row<height;row++){
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            for(int column=0;column<width;column++){
                int rate=width*height;
                int column2=column-1;
                int row2=row-1;
                boolean rturn=false;
                while(column2>0 && row2>0 && bools[row2][column2]){
                    rate+=width*height*10;
                    if(rturn){
                        row2--;
                    }else{
                        column2--;
                    }
                    rturn=!rturn;
                }

                bools[row][column]=r.nextInt(width*height*15)<rate;
                if(bools[row][column]){
                    //if(color){
                    //	g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
                    //}
                    g.drawLine(column, row, column, row);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Thin rifts";
    }

    @Override
    public void reset() {
        resetBools();
    }
}
