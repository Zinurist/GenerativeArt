package algorithms.randomizer;

import image.Color;
import option.OptionList;

public class RandomBigCircles extends Randomizer{

    private int num = 10;

    @Override
    public void step(int width, int height) {

        IMG.setXORMode(true);

        int x, y, rad;
        int avg = width+height/2;

        for(int i=0; i<num; i++){
            rad = r.nextInt(avg/2)+avg/4;
            x = r.nextInt(width) - rad;
            y = r.nextInt(height) - rad;
            if(color){
                IMG.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            }
            IMG.fillOval(x, y, rad*2, rad*2);
        }

        IMG.setXORMode(false);
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("#circles", num, 1, 100, val -> num = val);
        return list;
    }

    @Override
    public String toString() {
        return "Big circles";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
