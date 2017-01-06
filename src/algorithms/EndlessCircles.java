package algorithms;

import image.Color;
import option.OptionList;

public class EndlessCircles extends Algorithm{


    private int r_step;

    public EndlessCircles(){
        r_step = 10;
    }


    @Override
    public String toString() {
        return "Circles";
    }

    @Override
    public void step() {
        emptyIMG();

        IMG.setColor(Color.BLACK);

        int x0 = IMG.getWidth()/2;
        int y0 = IMG.getHeight()/2;

        IMG.drawLine(0, y0, IMG.getWidth(), y0);
        IMG.drawLine(x0, 0, x0, IMG.getHeight());

        for(int r=r_step; r<IMG.getWidth(); r+=r_step){
            IMG.drawOval(x0,y0,r,r);
            IMG.drawOval(x0,y0-r,r,r);
            IMG.drawOval(x0-r,y0,r,r);
            IMG.drawOval(x0-r,y0-r,r,r);
        }
    }

    @Override
    public void reset() {
    }


    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("r step", r_step, 2, 300, val -> r_step = val);
        return list;
    }
}
