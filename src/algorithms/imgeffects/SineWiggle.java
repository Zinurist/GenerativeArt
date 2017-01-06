package algorithms.imgeffects;

import image.Color;
import option.OptionList;

public class SineWiggle extends ImageEffect {

    private double a,b;
    private int t;//percent
    private boolean loop;

    public SineWiggle(){
        super();
        reset();
        a = 0;
        b = 10;
    }

    @Override
    public String toString(){
        return "Sine wiggle";
    }

    @Override
    public void step() {
        emptyIMG();

        Color c;
        double ad = a/10.0;
        double bd = b/(double)IMG.getHeight();
        double progress = Math.PI*2*(t/100.0);
        int offset;
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                c = new Color(original.getRGB(x, y));
                IMG.setColor(c);

                offset = (int)(ad*Math.sin(y*bd + progress)+0.5);

                IMG.drawLine(x+offset, y, x+offset, y);
            }
        }

        t++;
        if(t>99){
            if(loop){
                stop();
            }
            t = 0;
        }
    }

    @Override
    public void reset(){
        t = 0;
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("Amplitude a", a, 0, IMG.getWidth()*2./10., 0.1, val -> a = val);
        list.addOption("Factor b", b, 0, 200, 1., val -> b = val);
        list.addOption("loop perfectly", loop, val -> loop = val);
        list.addInfo("a * sin(x * b/height)");
        return list;
    }

}
