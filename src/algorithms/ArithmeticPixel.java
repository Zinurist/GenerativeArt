package algorithms;

import image.Color;
import option.OptionList;

/**
 * Created by Zinu on 21/08/2017.
 */
public class ArithmeticPixel extends Algorithm {

    private int mode;
    private boolean red, green, blue;

    public ArithmeticPixel(){
        super();
        red = green = blue = true;
        reset();
    }

    @Override
    public String toString() {
        return "Arithmetic Pixel";
    }

    private int abs(int x){
        return Math.abs(x);
    }

    private int log(int x, int y){
        int r =  (int) Math.round(Math.log(x) / Math.log(y));
        return r>0?r:255;
    }

    private int exp(int x, int y){
        int r = (int)Math.round(Math.exp(x+y));
        return r>0?r:255;
    }

    @Override
    public void step() {
        Color c;
        float height = IMG.getHeight();
        float width = IMG.getWidth();
        for(int y = 0; y < IMG.getHeight(); y++){
            for(int x = 0; x < IMG.getWidth(); x++){
                int x2 = x%256;
                int y2 = y%256;
                int r,g,b;
                switch(mode){
                    case 0:
                        r = log(x2,y2);
                        g = log(y2,x2);
                        b = exp(x2,y2);
                        break;
                    case 1:
                        r = abs(x-y);
                        g = abs(y-x);
                        b = x+y;
                        break;
                    case 2:
                        r = x/(y+1);
                        g = y/(x+1);
                        b = x*y;
                        break;
                    case 3:
                        r = x|y;
                        g = x&y;
                        b = x^y;
                        break;
                    default: r = g = b = 0;
                }

                c = new Color(red?r%256:0, green?g%256:0, blue?b%256:0);
                IMG.setColor(c);
                IMG.drawLine(x,y,x,y);
            }
        }

        mode++;
        if(mode > 3) mode = 0;
    }

    @Override
    public void reset() {
        mode = 0;
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("red", red, val -> {red = val;reset();});
        list.addOption("green", green, val -> {green = val;reset();});
        list.addOption("blue", blue, val -> {blue = val;reset();});
        return list;
    }
}
