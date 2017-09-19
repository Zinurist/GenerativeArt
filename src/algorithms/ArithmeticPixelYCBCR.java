package algorithms;

import image.Color;
import option.OptionList;

/**
 * Created by Zinu on 21/08/2017.
 */
public class ArithmeticPixelYCBCR extends Algorithm {

    private int mode = 1;

    public ArithmeticPixelYCBCR(){
        super();
        reset();
    }

    @Override
    public String toString() {
        return "Arithmetic Pixel YCbCr";
    }

    private float abs(float x){
        return Math.abs(x);
    }

    private float log(int x, int y){
        float r = (float)(Math.log(x) / Math.log(y));
        return Float.isNaN(r)||Float.isInfinite(r) ? 1.0F : r;
    }

    private float exp(int x, int y){
        float r = (float)Math.exp(x+y);
        return Float.isNaN(r)||Float.isInfinite(r) ? 1.0F : r;
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
                float luma, cb, cr;
                switch(mode){
                    case 0:
                        cr = log(x2,y2);
                        cb = log(y2,x2);
                        luma = exp(x2,y2);
                        break;
                    case 1:
                        cr = abs(x-y);
                        cb = abs(y-x);
                        luma = x+y;
                        break;
                    case 2:
                        cr = x/(y+1.F);
                        cb = y/(x+1.F);
                        luma = x*y;
                        break;
                    case 3:
                        luma = x|y;
                        cb = x&y;
                        cr = x^y;
                        break;
                    default: luma = cr = cb = 0.F;
                }

                c = new Color();
                c.fromYCbCr(luma%1.0F, cb%1.0F - 0.5F, cr%1.0F - 0.5F);
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
        return list;
    }
}
