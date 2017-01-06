package algorithms.randomizer;

import image.Color;
import option.OptionList;

public class RandomLines extends Randomizer{

    private int lines = 100;

    @Override
    public void step(int width, int height) {
        for(int i=0; i<lines; i++){
            if(color){
                IMG.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            IMG.drawLine(r.nextInt(width),r.nextInt(height),r.nextInt(width),r.nextInt(height));
        }
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("lines", lines, 0, 1000, 10, (OptionList.IntegerOptionListener)val -> lines = val);
        return list;
    }

    @Override
    public String toString() {
        return "Lines";
    }


    @Override
    public void reset() {
        resetBools();
    }
}
