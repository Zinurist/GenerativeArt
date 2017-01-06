package algorithms.imgeffects;

import image.Color;
import option.OptionList;

public class SineEffect extends ImageEffect {

    private boolean cosine;

    public SineEffect(){
        super();
        cosine = false;
    }

    @Override
    public String toString(){
        return "Sine effect";
    }

    @Override
    public void step() {
        Color c;

        if(cosine){
            for(int y=0; y<height; y++){
                for(int x=0; x<width; x++){
                    c = new Color(IMG.getRGB(x, y));
                    IMG.setColor(new Color((float)(0.5*Math.cos(c.getRed()*2*Math.PI/255) + 0.5), (float)(0.5*Math.cos(c.getGreen()*2*Math.PI/255) +0.5), (float)(0.5*Math.cos(c.getBlue()*2*Math.PI/255) +0.5)));
                    IMG.drawLine(x, y, x, y);
                }
            }
        }else{
            for(int y=0; y<height; y++){
                for(int x=0; x<width; x++){
                    c = new Color(IMG.getRGB(x, y));
                    IMG.setColor(new Color((float)(0.5*Math.sin(c.getRed()*2*Math.PI/255) + 0.5), (float)(0.5*Math.sin(c.getGreen()*2*Math.PI/255) +0.5), (float)(0.5*Math.sin(c.getBlue()*2*Math.PI/255) +0.5)));
                    IMG.drawLine(x, y, x, y);
                }
            }
        }

    }

    @Override
    public void reset() {

    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("use cosine", cosine, val -> cosine = val);
        return list;
    }

}
