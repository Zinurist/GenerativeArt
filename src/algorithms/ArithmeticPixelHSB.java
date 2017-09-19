package algorithms;

import image.Color;
import option.OptionList;

/**
 * Created by Zinu on 21/08/2017.
 */
public class ArithmeticPixelHSB extends Algorithm {

    private int mode = 1;

    public ArithmeticPixelHSB(){
        super();
        reset();
    }

    @Override
    public String toString() {
        return "Arithmetic Pixel HSB";
    }

    private float abs(float x){
        return Math.abs(x);
    }


    @Override
    public void step() {
        Color c;
        float height = IMG.getHeight();
        float width = IMG.getWidth();
        for(int y = 0; y < IMG.getHeight(); y++){
            for(int x = 0; x < IMG.getWidth(); x++){
                float x2 = 1.0F/x;
                float y2 = 1.0F/y;
                float x3 = x*1.0F/width;
                float y3 = y*1.0F/height;
                float h, s, b;
                switch(mode){
                    case 0:
                        h = (float) Math.pow(x3,y3);
                        s = (float) Math.pow(y3,x3);
                        b = 0.8F;
                        break;
                    case 1:
                        b = x3+y3;
                        h = x3+y3;
                        s = x3+y3;
                        break;
                    case 2:
                        s = x3;
                        b = y3;
                        h = x3+y3;
                        break;
                    case 3:
                        s = x3;
                        h = y3;
                        b = x3+y3;
                        break;
                    case 4:
                        s = abs(x3-y3);
                        h = abs(y3-x3);
                        b = x3+y3;
                        break;
                    case 5:
                        s = x/(y+1.F);
                        h = y/(x+1.F);
                        b = 0.5F;
                        break;
                    case 6:
                        s = 1-x2;
                        h = 1-y2;
                        b = 1 - 1.0F/(x2+y2);
                        break;
                    default: h = s = b = 0.F;
                }

                c = new Color();
                c.fromHSB(h, s%1.0F, b%1.0F);
                IMG.setColor(c);
                IMG.drawLine(x,y,x,y);
            }
        }

        mode++;
        if(mode > 6) mode = 0;
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
