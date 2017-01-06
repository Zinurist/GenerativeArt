package algorithms.imgeffects;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import image.Color;
import option.OptionList;

public class TransitionMask extends ImageEffect {

    private int tick = 0;
    private List<int[]>[] pixels;
    private boolean boxReset;
    private int add;
    private boolean reset;

    public TransitionMask(){
        super();
        pixels = new List[256];

        for(int i=0; i<pixels.length; i++){
            pixels[i] = new LinkedList<>();
        }

        boxReset = false;
        add = 1;

        tick = 0;
        reset = false;
    }

    @Override
    public String toString() {
        return "Transition mask";
    }

    @Override
    public void step() {
        if(tick > 255){
            if(boxReset || reset) reset();
            else{
                reset = true;
                stop();
                return;
            }
        }

        /*
        Color c;

        for(int y=0; y<mask.getHeight(); y++){
            for(int x=0; x<mask.getWidth(); x++){
                c = new Color(mask.getRGB(x, y));

                if(c.getBlue() < tick){
                    g.setColor(Color.BLACK);
                    g.drawLine(x, y, x, y);
                }

            }
        }
        */

        IMG.setColor(Color.BLACK);
        int upper = Math.min(tick+add, 255);
        for(int k=tick; k<upper; k++) {
            for (int[] p : pixels[k]) {
                IMG.drawLine(p[0], p[1], p[0], p[1]);
            }
        }

        //tick++;
        tick += add;
    }

    @Override
    public void reset() {
        tick = 0;
        reset = false;

        for(int i=0; i<pixels.length; i++){
            pixels[i] = new LinkedList<>();
        }

        Color c;
        for(int y=0; y<mask.getHeight(); y++) {
            for (int x = 0; x < mask.getWidth(); x++) {
                c = new Color(mask.getRGB(x, y));
                pixels[c.getBlue()].add(new int[]{x,y});
            }
        }
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("step size", add, 1, 255, 1, (OptionList.IntegerOptionListener)val -> add = val);
        list.addOption("reset img", boxReset, val -> boxReset = val);
        return list;
    }

}
