package algorithms;

import image.Color;
import option.OptionList;

public class Polar extends Algorithm{

    private static final int POINT_SIZE = 8;

    private int t, max_r, r_step;

    public Polar(){
        r_step = 30;
        reset();
    }

    @Override
    public String toString() {
        return "Polar";
    }

    @Override
    public void step() {
        emptyIMG();
        IMG.setColor(Color.BLACK);

        int x0 = IMG.getWidth()/2;
        int y0 = IMG.getHeight()/2;

        IMG.drawLine(0, y0, IMG.getWidth(), y0);
        IMG.drawLine(x0, 0, x0, IMG.getHeight());

        for(int r=r_step; r<max_r; r+=r_step){
            IMG.setColor(Color.BLACK);
            IMG.drawOval(x0 - r, y0 - r, 2*r, 2*r);
            IMG.setColor(Color.BLUE);
            IMG.fillOval(x0+(int)Math.round(r*Math.cos(t * r /2000.0)) - POINT_SIZE/2,y0+(int)Math.round(r*Math.sin(t * r /2000.0)) - POINT_SIZE/2,POINT_SIZE,POINT_SIZE);
        }

        t++;
    }

    @Override
    public void reset() {
        t = 0;
        max_r = (int)Math.round(Math.sqrt(IMG.getHeight()*IMG.getHeight() + IMG.getWidth()*IMG.getWidth()));
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("r step", r_step, 2, 100, val -> r_step = val);
        return list;
    }
}
